package game.gamePanes;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JFrame;

import game.paneScreens.*;
import game.components.Scenario;
import game.components.Scenarios;
import game.components.ScenarioLoader;
import game.swingFramework.FrontEndPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

@SuppressWarnings("serial")
public class SettingsPane extends Pane {
	public JTextArea laborCost;
	public JTextArea followTheSun;
	public JButton loadDefaultSites;
	public ChooseDefaultPane loadDefaultSitesP;
//	private JButton easy;
//	private JButton medium;
//	private JButton hard;
//	viewScreen = new SettingsScreen();
	
	public SettingsPane(FrontEndPane fP) {
		super(fP);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setupSwingPane() {
		this.setLayout(new GridLayout(0,3));
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.laborCost = new JTextArea();
		//this.laborCost.setEnabled(false);
//		this.laborCost.addMouseListener(new MouseAdapter()  
//			{  
//			@Override  
//			public void mouseClicked(MouseEvent event)  
//				{  
//				laborCost.requestFocus();  
//				}  
//			});  
//  
//		this.laborCost.addFocusListener(new FocusListener()  
//        	{  
//			@Override  
//			public void focusLost(FocusEvent arg0)  
//            	{  
//				laborCost.setEnabled(false);  
//            	}  
//  
//			@Override  
//			public void focusGained(FocusEvent arg0)  
//            	{  
//				//laborCost.setEnabled(true); 
//            	}  
//        	}); 
		this.laborCost.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		newPanel.add(new JLabel("Labor Cost Per Location:"));
		this.laborCost.setPreferredSize(new Dimension(50,25));
		newPanel.setOpaque(false);
		newPanel.add(this.laborCost);
		//newPanel.setPreferredSize(new Dimension(50,50));
		this.add(newPanel);
		this.add(new JLabel());
		this.add(new JLabel());

		this.followTheSun = new JTextArea();
		//this.followTheSun.setEnabled(false);
		this.followTheSun.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel newPanel2 = new JPanel();
		newPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		newPanel2.add(new JLabel("Follow The Sun Handoff Time:"));
		this.followTheSun.setPreferredSize(new Dimension(50,25));
		newPanel2.setOpaque(false);
		newPanel2.add(this.followTheSun);
		//newPanel.setPreferredSize(new Dimension(50,50));
		this.add(newPanel2);
		this.add(new JLabel());
		this.add(new JLabel());
		JPanel newPanel3 = new JPanel();
		newPanel3.setLayout(new GridLayout(3, 0));
		newPanel3.add(new JLabel());
		loadDefaultSites = new JButton("Choose Default Sites");
		loadDefaultSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDefaultSitesP = new ChooseDefaultPane();
				loadDefaultSitesP.setVisible(true);
			}
		});
		newPanel3.add(loadDefaultSites);
		newPanel3.setOpaque(false);
		this.add(newPanel3);
		this.startListening();
		this.viewScreen = new SettingsScreen(this);
		((SettingsScreen)this.viewScreen).readSettings();
		
	}
	

	
	@Override
	public void doTick(long nanos) {
		// TODO Auto-generated method stub
		
		this.viewScreen.onTick(nanos);
		
	}
	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent e) {
//		return false;
//	}

//	@Override
//	public void componentResized(ComponentEvent e) {
//		// TODO Auto-generated method stub
//		
//	}

	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		super.keyReleased(e);
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.viewScreen.onMouseDragged(e);
		
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.viewScreen.onMousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.viewScreen.onMouseReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


class ChooseDefaultPane extends JFrame {
  JRadioButton rB;
  ButtonGroup bG;
	JPanel okPanel;
	JButton ok;
  String cities;
	String[] cities1;
	Scenarios s;
  public ChooseDefaultPane() {
		bG = new ButtonGroup();
		this.setSize(new Dimension(795, 496));
		this.setLocationRelativeTo(null);
    this.setLayout(new GridLayout(0,3));
    this.add(new JLabel());
    s = ScenarioLoader.load("./gameFiles/scenario1.json");
		cities1 = new String[s.scenarios.length];
    for (int i = 0; i < s.scenarios.length; i++) {
      for (int j = 0; j < s.scenarios[i].sites.length; j++) {
        if (j == 0) {
          cities = s.scenarios[i].sites[j].name;
        } else {
          cities += ", " + s.scenarios[i].sites[j].name;
        }
      }
			cities1[i] = cities;
      rB = new JRadioButton(cities);
      bG.add(rB);
      add(rB);
      this.add(new JLabel());
      this.add(new JLabel());
    }
		this.add(new JLabel());
		okPanel = new JPanel();
		okPanel.setLayout(new GridLayout(4, 0));
		okPanel.add(new JLabel());
		okPanel.add(new JLabel());
		okPanel.add(new JLabel());
		ok = new JButton("Ok");
		okPanel.add(ok);
		this.add(okPanel);
		ok.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
				for (Enumeration<AbstractButton> buttons = bG.getElements(); buttons.hasMoreElements();) {
        	AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
							for (int k = 0; k < cities1.length; k++) {
                if (button.getText().equals(cities1[k])) {
									game.swingFramework.FrontEndPane.modules.clearSites();
								  game.swingFramework.FrontEndPane.siteStatus.removeAllMapMarkers();

    							/* Load in new stuff */
    							for (int l = 0; l < s.scenarios[k].sites.length; l++) {
     								game.swingFramework.FrontEndPane.addSiteToCombo(s.scenarios[k].process().get(l));
     								game.swingFramework.FrontEndPane.modSiteController.addSite(s.scenarios[k].process().get(l));
     								game.swingFramework.FrontEndPane.siteStatus.addMapMarker(s.scenarios[k].process().get(l).getMarker());
   								}
								}
							}
	          }
       		}
					dispose();
      	}
    });

		
  }
}
	
	
	
	
}
