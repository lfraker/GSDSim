package game.components;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import game.swingFramework.FrontEndPane;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import game.org.openstreetmap.gui.jmapviewer.Coordinate;
import game.org.openstreetmap.gui.jmapviewer.JMapController;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import game.org.openstreetmap.gui.jmapviewer.OsmMercator;
import game.org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

/**
 * Default map controller which implements map moving by pressing the right
 * mouse button and zooming by double click or by mouse wheel.
 *
 * @author Jan Peter Stotz
 *
 */
public class GameMapController extends JMapController implements MouseListener, MouseMotionListener,
MouseWheelListener {

	private Button mapPause;
	private Button startSim;
	private Button saveScen;
	private Button interv;
    private static final int MOUSE_BUTTONS_MASK = MouseEvent.BUTTON3_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK
    | MouseEvent.BUTTON2_DOWN_MASK;
    
    FrontEndPane parentComp;

    private static final int MAC_MOUSE_BUTTON3_MASK = MouseEvent.CTRL_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
    public GameMapController(JMapViewer map, FrontEndPane fP, Button pause, Button sSim, Button sScen, Button inter) {
        super(map);
        this.parentComp = fP;
        this.mapPause = pause;
        this.startSim = sSim;
        this.saveScen = sScen;
        this.interv = inter;
    }

    private Point lastDragPoint;
    
    private AddSiteOption optionPane;
    private JFrame inquirPane;
    private SaveScenario savePane;
    private InterventionOption iPane;
    private Site currSiteQuer;
	private JTextArea siteResponse;
	private Random numberGen = new Random();



    private boolean isMoving = false;

    private boolean movementEnabled = true;

    private int movementMouseButton = MouseEvent.BUTTON3;
    private int adSiteMouseButton = MouseEvent.BUTTON1;
    private int movementMouseButtonMask = MouseEvent.BUTTON3_DOWN_MASK;

    private boolean wheelZoomEnabled = true;
    private boolean doubleClickZoomEnabled = true;

    public void mouseDragged(MouseEvent e) {
        if (!movementEnabled || !isMoving)
            return;
        // Is only the selected mouse button pressed?
        if ((e.getModifiersEx() & MOUSE_BUTTONS_MASK) == movementMouseButtonMask) {
            Point p = e.getPoint();
            if (lastDragPoint != null) {
                int diffx = lastDragPoint.x - p.x;
                int diffy = lastDragPoint.y - p.y;
                map.moveMap(diffx, diffy);
            }
            lastDragPoint = p;
        }
    }

    
    //UNTESTED
    /*
     * Only method in this class that is not a library method.
     */
    public void mouseClicked(MouseEvent e) {
        Point center = this.parentComp.getCenter();

     	int xi =e.getPoint().x + (center.x - (this.parentComp.getMapWidth() / 2));
        int yi =e.getPoint().y +  (center.y - (this.parentComp.getMapHeight() / 2));


     	double x = OsmMercator.XToLon(xi, this.parentComp.getZoom());
        double y = OsmMercator.YToLat(yi, this.parentComp.getZoom());

    	System.out.println("X : " + x);
    	System.out.println("Y: " + y);
        
        if (this.mapPause.clickedInside(e.getPoint()) && this.parentComp.canPause()) {
    		this.parentComp.pauseUnpause();
    		return;
    		
    	}
        if (this.startSim.clickedInside(e.getPoint()) && this.parentComp.canStartSim()) {
    		this.parentComp.startCustomSim();
    		return;
    		
    	}
        if (this.saveScen.clickedInside(e.getPoint()) && this.parentComp.canStartSim()) {
        	if (this.savePane != null) {
        		this.savePane.setVisible(false);
        		this.savePane.dispose();
        	}
        	this.savePane = new SaveScenario(this.parentComp.getWindow(), "Save Custom Scenario", this.parentComp);
        	this.savePane.setVisible(true);
        	return;
    	}
        
        if (this.interv.clickedInside(e.getPoint())) {
    		if (this.iPane != null) {
    			this.iPane.dispose();
    			this.iPane = null;
    		}
    		this.iPane = new InterventionOption();
    		return;
    		
    	}
//    	else if (doubleClickZoomEnabled && e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
//            map.zoomIn(e.getPoint());
//        }
        List<MapMarker> mDots = this.parentComp.getMapViewer().getMapMarkerList();

    	for ( Site site : this.parentComp.processSimulator.GetSites()) {
    		MapMarkerDot mDot = site.getMarker();
    		Coordinate c = mDot.getCoordinate();
    		int xT = OsmMercator.LonToX(c.getLon(), this.parentComp.getZoom());
            int yT = OsmMercator.LatToY(c.getLat(), this.parentComp.getZoom());

            xT -= center.x - (this.parentComp.getMapWidth() / 2);
            yT -= center.y - (this.parentComp.getMapHeight() / 2);

            if ((Math.abs(e.getX() - xT) <= 7) && (Math.abs(e.getY() - yT) <= 7)) {
            	if (!this.parentComp.isSimLoaded()) {
            		return;
            	}
            	setUpInquiry(site);
    			return;
    		}
    		
    	}
        
        if (e.getButton() == this.adSiteMouseButton && e.getClickCount() == 1 && !this.parentComp.isSimLoaded()) {
        	//Use JDialog?
        	if (this.optionPane != null) {
        		this.optionPane.setVisible(false);
        		this.optionPane.dispose();
        	}
        	this.optionPane = new AddSiteOption(this.parentComp, "Add Site");
        	this.optionPane.setVisible(true);

           String name = this.optionPane.getSiteName();
           int nEmp = this.optionPane.getNumberEmployees();
           int tZ = this.optionPane.getTimeZone();
           int cD = this.optionPane.getCostDev();
           int eD = this.optionPane.getEffortDev();
            if (!this.optionPane.getCancelled() && !(e.getPoint().x < 0 || e.getPoint().y < 0 || e.getPoint().x > this.parentComp.getMapWidth() || e.getPoint().y > this.parentComp.getMapHeight())) {
            	boolean isRA = false;
            	if (x > 31.99 && y < 77.76 && y > 46.55) {
            		isRA = true;
            	}
            	if (x < 150.82 && x > 32.69 && y < 46.55 && y > 28.92) {
            		isRA = true;
            	}
            	if (x < 123.39 && x > 39.37 && y < 28.92 && y > 12.55) {
            		isRA = true;
            	}
            	if (x < 164.88 && x > 72.42 && y < 12.55 && y > -10.48) {
            		isRA = true;
            	}
            	if (x < 164.88 && x > 72.42 && y < 12.55 && y > -10.48) {
            		isRA = true;
            	}
            	if (x < 169.10 && y < 71.85 && y > 61.77) {
            		isRA = true;
            	}
            	
            	System.out.println("RA : " + isRA);
            	MapMarkerDot mDot = new MapMarkerDot(null, name, y, x);
            	Site toAdd = new Site(name, nEmp, mDot, tZ, cD, eD, isRA);

                toAdd.SetCoordinates(y,x);
            	this.parentComp.addSiteToCombo(toAdd);
            	this.parentComp.processSimulator.AddSite(toAdd);
            	this.parentComp.getMapViewer().addMapMarker(mDot);
            }
            this.optionPane.dispose();
            this.optionPane = null;
           // System.exit(0);


        }
    	
    }

    public void mousePressed(MouseEvent e) {

        if (e.getButton() == movementMouseButton || isPlatformOsx() && e.getModifiersEx() == MAC_MOUSE_BUTTON3_MASK) {
            lastDragPoint = null;
            isMoving = true;
        }
    }

    public void mouseReleased(MouseEvent e) {

        if (e.getButton() == movementMouseButton || isPlatformOsx() && e.getButton() == MouseEvent.BUTTON1) {
            lastDragPoint = null;
            isMoving = false;
        }
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        if (wheelZoomEnabled) {
            int newZoom = map.getZoom() - e.getWheelRotation();
            if (newZoom >= 2) {
                map.setZoom(newZoom, e.getPoint());
                if (newZoom == 2) {
                    map.setDisplayPositionByLatLon(20, 10, 2);
                }
            }
        }
    }

    public boolean isMovementEnabled() {
        return movementEnabled;
    }

    /**
     * Enables or disables that the map pane can be moved using the mouse.
     *
     * @param movementEnabled
     */
    public void setMovementEnabled(boolean movementEnabled) {
        this.movementEnabled = movementEnabled;
    }

    public int getMovementMouseButton() {
        return movementMouseButton;
    }

    /**
     * Sets the mouse button that is used for moving the map. Possible values
     * are:
     * <ul>
     * <li>{@link MouseEvent#BUTTON1} (left mouse button)</li>
     * <li>{@link MouseEvent#BUTTON2} (middle mouse button)</li>
     * <li>{@link MouseEvent#BUTTON3} (right mouse button)</li>
     * </ul>
     *
     * @param movementMouseButton
     */
    public void setMovementMouseButton(int movementMouseButton) {
        this.movementMouseButton = movementMouseButton;
        switch (movementMouseButton) {
            case MouseEvent.BUTTON1:
                movementMouseButtonMask = MouseEvent.BUTTON1_DOWN_MASK;
                break;
            case MouseEvent.BUTTON2:
                movementMouseButtonMask = MouseEvent.BUTTON2_DOWN_MASK;
                break;
            case MouseEvent.BUTTON3:
                movementMouseButtonMask = MouseEvent.BUTTON3_DOWN_MASK;
                break;
            default:
                throw new RuntimeException("Unsupported button");
        }
    }

    public boolean isWheelZoomEnabled() {
        return wheelZoomEnabled;
    }

    public void setWheelZoomEnabled(boolean wheelZoomEnabled) {
        this.wheelZoomEnabled = wheelZoomEnabled;
    }

    public boolean isDoubleClickZoomEnabled() {
        return doubleClickZoomEnabled;
    }

    public void setDoubleClickZoomEnabled(boolean doubleClickZoomEnabled) {
        this.doubleClickZoomEnabled = doubleClickZoomEnabled;
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        // Mac OSX simulates with  ctrl + mouse 1  the second mouse button hence no dragging events get fired.
        //
//    	 for ( Site site : this.parentComp.getSMController().getSites()) {
//     		MapMarkerDot mDot = site.getMarker();
//     		Coordinate c = mDot.getCoordinate();
//     		int xT = OsmMercator.LonToX(c.getLon(), this.parentComp.getZoom());
//             int yT = OsmMercator.LatToY(c.getLat(), this.parentComp.getZoom());
//
//             xT -= this.parentComp.getCenter().x - (this.parentComp.getMapWidth() / 2);
//             yT -= this.parentComp.getCenter().y - (this.parentComp.getMapHeight() / 2);
//
//             if ((Math.abs(e.getX() - xT) <= 100) && (Math.abs(e.getY() - yT) <= 100)) {
//            	
//            	 mDot.showClick();
//             }
//             else {
//
//            	// mDot.writeClick(false);
//             }
//     		
//     	}
        if (isPlatformOsx()) {
            if (!movementEnabled || !isMoving)
                return;
            // Is only the selected mouse button pressed?
            if (e.getModifiersEx() == MouseEvent.CTRL_DOWN_MASK) {
                Point p = e.getPoint();
                if (lastDragPoint != null) {
                    int diffx = lastDragPoint.x - p.x;
                    int diffy = lastDragPoint.y - p.y;
                    map.moveMap(diffx, diffy);
                }
                lastDragPoint = p;
            }

        }
       

    }

    /**
     * Replies true if we are currently running on OSX
     *
     * @return true if we are currently running on OSX
     */
    public static boolean isPlatformOsx() {
        String os = System.getProperty("os.name");
        return os != null && os.toLowerCase().startsWith("mac os x");
    }
    
    
    //UNTESTED
    public void setUpInquiry(Site s) {
    	if (this.inquirPane != null) {
    		this.inquirPane.dispose();
    		this.inquirPane = null;
    		currSiteQuer = null;
    		//this.siteResponse.disable();
    		this.siteResponse = null;
    	}
    	currSiteQuer = s;
    	this.inquirPane = new JFrame("Inquiry at Site: " + s.getName());
    	this.inquirPane.setLayout(new GridLayout(0,2));
    	this.inquirPane.setMinimumSize(new Dimension(600, 700));
		this.inquirPane.setPreferredSize(new Dimension(600, 700));
		this.inquirPane.setMaximumSize(new Dimension(600, 700));
		

		
    	JButton onSched = new JButton("Send Email");
    	JButton repStat = new JButton("Send Email");
    	JButton completTasks = new JButton("Send Email");
    	JButton vidConf = new JButton("Start Conference");
    	JButton makeVis = new JButton("Book Visit");
    	this.siteResponse = new JTextArea();
    	this.siteResponse.setEditable(false);
    	JScrollPane scrollRes = new JScrollPane(this.siteResponse);
    	onSched.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currSiteQuer.getModules().size() == 0) {
					siteResponse.setText("No Modules currently at this site");
					return;
				}
				Module mod = currSiteQuer.getModules().get(0);
				if (currSiteQuer.isRussAsian) {
					siteResponse.setText("yes");
				}
				else {
					if (mod.IsOnSchedule()) {
						siteResponse.setText("yes");
					}
					else {
						siteResponse.setText("no");
					}
				}
				
			}
    		
    	});
    	
    	repStat.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currSiteQuer.getModules().size() == 0) {
					siteResponse.setText("No Modules currently at this site");
					return;
				}
				float mon = Float.parseFloat(parentComp.getGlobalParam("UsrMoney"));
				int devday = currSiteQuer.costDeveloperDay;
				float newMon = mon - ( 0.1f * devday);
				parentComp.updateGlobalParam("UsrMoney", newMon+"");
				String rep = "";
				if (currSiteQuer.isRussAsian) {
					for (Module m : currSiteQuer.getModules()) {
						rep += "Module " + m.getName() + ": On Schedule\n";
					}
				}
				else {
					for (Module m : currSiteQuer.getModules()) {
						if (m.IsOnSchedule()) {
							rep += m.getName() + ": On Schedule\n";
						}
						else {
							rep += m.getName() + ": Behind Schedule\n";
						}
					}
				}
				siteResponse.setText(rep);

			}
    		
    	});
    	
    	completTasks.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currSiteQuer.getModules().size() == 0) {
					siteResponse.setText("No Modules currently at this site");
					return;
				}
				float mon = Float.parseFloat(parentComp.getGlobalParam("UsrMoney"));
				int devday = currSiteQuer.costDeveloperDay;
				float newMon = mon - ( 0.5f * devday);
				parentComp.updateGlobalParam("UsrMoney", newMon+"");
				String rep = "";
				
				for (Module m: currSiteQuer.getModules()) {
					rep +=  "Module: " + m.getName()+ "\n";
					for (int i = 0; i < m.sectionsCompleted(); i++) {
						switch (i) {
							case 0: rep += "\tDesign: Completed\n";
								break;
							case 1: rep += "\tImplementation: Completed\n";
								break;
							case 2: rep += "\tUnit test: Completed\n";
								break;
							case 3: rep += "\tIntegration: Completed\n";
								break;
							case 4: rep += "\tSystem test: Completed\n";
								break;
							case 5: rep += "\tDeployment: Completed\n";
								break;
							case 6: rep += "\tAcceptance test: Completed\n";
								break;
						}	
					}
				}
				siteResponse.setText(rep);
				
			}
    		
    	});
    	
    	vidConf.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currSiteQuer.getModules().size() == 0) {
					siteResponse.setText("No Modules currently at this site");
					return;
				}
				float mon = Float.parseFloat(parentComp.getGlobalParam("UsrMoney"));
				int devday = currSiteQuer.costDeveloperDay;
				float newMon = mon - ( 2.0f * devday);
				parentComp.updateGlobalParam("UsrMoney", newMon+"");
				String rep = "";
				
				for (Module m: currSiteQuer.getModules()) {
					rep +=  "Module: " + m.getName()+ "\n";
					for (int i = 0; i < m.sectionsCompleted(); i++) {
						switch (i) {
							case 0: rep += "\tDesign: Completed\n";
								break;
							case 1: rep += "\tImplementation: Completed\n";
								break;
							case 2: rep += "\tUnit test: Completed\n";
								break;
							case 3: rep += "\tIntegration: Completed\n";
								break;
							case 4: rep += "\tSystem test: Completed\n";
								break;
							case 5: rep += "\tDeployment: Completed\n";
								break;
							case 6: rep += "\tAcceptance test: Completed\n";
								break;
						}	
					}
					for (int i = m.sectionsCompleted(); i < 7; i++) {
						switch (i) {
							case 0: rep += "\tDesign: ";
								break;
							case 1: rep += "\tImplementation: ";
								break;
							case 2: rep += "\tUnit test: ";
								break;
							case 3: rep += "\tIntegration: ";
								break;
							case 4: rep += "\tSystem test: ";
								break;
							case 5: rep += "\tDeployment: ";
								break;
							case 6: rep += "\tAcceptance test: ";
								break;
						}
						String truth = "";
						if (m.IsOnSchedule()) {
							truth = "On Schedule";
						}
						else {
							truth = "Behind Schedule";
						}
						if (currSiteQuer.isRussAsian) {
							int tem = numberGen.nextInt(2);
							if (tem == 0) {
								rep += "On Schedule\n";
							}
							else {
								rep += truth + "\n";
							}
						}
						else {
							rep += truth + "\n";
						}
					}
				}
				siteResponse.setText(rep);

			}
    		
    	});
    	
    	makeVis.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(currSiteQuer.getModules().size() == 0) {
					siteResponse.setText("No Modules currently at this site");
					return;
				}
				float mon = Float.parseFloat(parentComp.getGlobalParam("UsrMoney"));
				int devday = currSiteQuer.costDeveloperDay;
				float newMon = mon - ( 7.0f * devday);
				parentComp.updateGlobalParam("UsrMoney", newMon+"");
				String rep = "";
				
				for (Module m: currSiteQuer.getModules()) {
					rep +=  "Module: " + m.getName()+ "\n";
					for (int i = 0; i < m.sectionsCompleted(); i++) {
						switch (i) {
							case 0: rep += "\tDesign: Completed\n";
								break;
							case 1: rep += "\tImplementation: Completed\n";
								break;
							case 2: rep += "\tUnit test: Completed\n";
								break;
							case 3: rep += "\tIntegration: Completed\n";
								break;
							case 4: rep += "\tSystem test: Completed\n";
								break;
							case 5: rep += "\tDeployment: Completed\n";
								break;
							case 6: rep += "\tAcceptance test: Completed\n";
								break;
						}	
					}
					for (int i = m.sectionsCompleted(); i < 7; i++) {
						switch (i) {
							case 0: rep += "\tDesign: ";
								break;
							case 1: rep += "\tImplementation: ";
								break;
							case 2: rep += "\tUnit test: ";
								break;
							case 3: rep += "\tIntegration: ";
								break;
							case 4: rep += "\tSystem test: ";
								break;
							case 5: rep += "\tDeployment: ";
								break;
							case 6: rep += "\tAcceptance test: ";
								break;
						}
						String truth = "";
						if (m.IsOnSchedule()) {
							truth = "On Schedule";
						}
						else {
							truth = "Behind Schedule";
						}
						rep += truth + "\n";
					}
				}
				siteResponse.setText(rep);

			}
    		
    	});
    	




    	this.inquirPane.add(new JLabel("<html>Send 'are you on schedule' email. (Cost: 0 dev-days)</html>"));
    	this.inquirPane.add(onSched);
    	this.inquirPane.add(new JLabel("<html>Request status of modules email. (Cost: .1 dev-days)</html>"));
    	this.inquirPane.add(repStat);
    	this.inquirPane.add(new JLabel("<html>Request completed tasks list. (Cost: .5 dev-days)</html>"));
    	this.inquirPane.add(completTasks);
    	this.inquirPane.add(new JLabel("<html>Hold video conference. (Cost: 2.0 dev-days)</html>"));
    	this.inquirPane.add(vidConf);
    	this.inquirPane.add(new JLabel("<html>Visit site. (Cost: 7.0 dev-days)</html>"));
    	this.inquirPane.add(makeVis);
    	this.inquirPane.add(new JLabel("<html>Site " + s.getName() + "'s response:</html>"));
    	this.inquirPane.add(scrollRes);


    	this.inquirPane.pack();
    	this.inquirPane.setVisible(true);
    }
}
