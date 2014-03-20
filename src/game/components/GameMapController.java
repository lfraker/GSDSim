package game.components;

//License: GPL. Copyright 2008 by Jan Peter Stotz

import game.swingFramework.FrontEndPane;


import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
    private static final int MOUSE_BUTTONS_MASK = MouseEvent.BUTTON3_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK
    | MouseEvent.BUTTON2_DOWN_MASK;
    
    FrontEndPane parentComp;

    private static final int MAC_MOUSE_BUTTON3_MASK = MouseEvent.CTRL_DOWN_MASK | MouseEvent.BUTTON1_DOWN_MASK;
    public GameMapController(JMapViewer map, FrontEndPane fP, Button pause, Button sSim) {
        super(map);
        this.parentComp = fP;
        this.mapPause = pause;
        this.startSim = sSim;
    }

    private Point lastDragPoint;
    
    private AddSiteOption optionPane;

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

    public void mouseClicked(MouseEvent e) {
        Point center = this.parentComp.getCenter();

     	int xi =e.getPoint().x + (center.x - (this.parentComp.getMapWidth() / 2));
        int yi =e.getPoint().y +  (center.y - (this.parentComp.getMapHeight() / 2));


     	double x = OsmMercator.XToLon(xi, this.parentComp.getZoom());
        double y = OsmMercator.YToLat(yi, this.parentComp.getZoom());

        if (this.mapPause.clickedInside(e.getPoint()) && this.parentComp.canPause()) {
    		this.parentComp.pauseUnpause();
    		return;
    		
    	}
        if (this.startSim.clickedInside(e.getPoint()) && this.parentComp.canStartSim()) {
    		this.parentComp.startCustomSim();
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
    			JFrame f = new SiteInfoPane(mDot.getName() + " Site Info");
    			return;
    		}
    		
    	}
        
        if (e.getButton() == this.adSiteMouseButton && e.getClickCount() == 1 && !this.parentComp.isSimLoaded()) {
        	//Use JDialog?
        	if (this.optionPane != null) {
        		this.optionPane.setVisible(false);
        		this.optionPane.dispose();
        	}
        	this.optionPane = new AddSiteOption(this.parentComp.getWindow(), "Add Site");
        	this.optionPane.setVisible(true);
        	//addSite.get
//            Point center = this.parentComp.getCenter();
////        	System.out.println("BEFORE : " + e.getPoint().x + " x : y " + e.getPoint().y);
//
//        	int xi =e.getPoint().x + (center.x - (this.parentComp.getMapWidth() / 2));
//            int yi =e.getPoint().y +  (center.y - (this.parentComp.getMapHeight() / 2));
//
////            System.out.println("AFTER : " + xi + " x : y " + yi);
//
//        	//-x = -e.x - center.x - getwidth / 2
////        	x -= center.x - getWidth() / 2;
////            y -= center.y - getHeight() / 2;
//
//        	double x = OsmMercator.XToLon(xi, this.parentComp.getZoom());
//            double y = OsmMercator.YToLat(yi, this.parentComp.getZoom());
//        	System.out.println("AFTER : " + x + " x : y " + y);
//        	System.out.println("ZOOM HERE " + this.parentComp.getZoom());
//            System.out.println("HERIE Center x " + center.x + " Center y " + center.y);
//            System.out.println("HERIE Width : " + (this.parentComp.getMapWidth() / 2));
//            System.out.println("HERIE Height : " + (this.parentComp.getMapHeight() / 2));
//            System.exit(0);
//            x += center.x + this.parentComp.getMapWidth() * 2;
//            y += center.y + this.parentComp.getMapHeight() * 2;
           String name = this.optionPane.getSiteName();
           int nEmp = this.optionPane.getNumberEmployees();
           int tZ = this.optionPane.getTimeZone();
           int cD = this.optionPane.getCostDev();
           int eD = this.optionPane.getEffortDev();
            if (!this.optionPane.getCancelled() && !(e.getPoint().x < 0 || e.getPoint().y < 0 || e.getPoint().x > this.parentComp.getMapWidth() || e.getPoint().y > this.parentComp.getMapHeight())) {
            	MapMarkerDot mDot = new MapMarkerDot(null, name, y, x);
            	Site toAdd = new Site(name, nEmp, mDot, tZ, cD, eD);
//            	System.out.println(x +" X : Y " + y);
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
}
