package game.gamePanes;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import java.util.ArrayList;
import java.util.List;

import game.components.Module;
import game.components.Site;

import game.paneScreens.SetModulesScreen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;

@SuppressWarnings("serial")
public class SetModulesPane extends Pane {
	//public JTextArea moduleName = new JTextArea();
//	public JTextArea hoursEstimate = new JTextArea();
	public JComboBox<Site> dropDown = new JComboBox<>();
	List<Site> modSites = new ArrayList<>();
//	private JButton medium;
//	private JButton hard;
//	viewScreen = new SettingsScreen();
	
	public SetModulesPane(FrontEndPane fP) {
		super(fP);
		// TODO Auto-generated constructor stub
	}
	
	public void addSite(Site toAdd) {
		this.dropDown.addItem(toAdd);
		
	}
	
	public List<Site> getSiteList() {
		return modSites;
	}

	@Override
	public void setupSwingPane() {
		this.setLayout(new GridLayout(0,5));
		final JTextField moduleName = new JTextField();
		final JTextField hoursEstimate = new JTextField();
		moduleName.setVisible(true);
		moduleName.setEditable(true);
		hoursEstimate.setVisible(true);
		moduleName.setEditable(true);
		moduleName.setBorder(new BevelBorder(BevelBorder.LOWERED));
		hoursEstimate.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel temp1 = new JPanel();
		JPanel temp2 = new JPanel();
		JPanel temp3 = new JPanel();
		JPanel temp4 = new JPanel();
		JPanel temp5 = new JPanel();
		temp1.setLayout(new GridLayout(6,0));
		temp1.add(new JLabel());
		temp1.add(new JLabel());
		temp1.add(new JLabel());
		temp1.add(moduleName);
		
		temp2.setLayout(new GridLayout(6,0));
		temp2.add(new JLabel());
		temp2.add(new JLabel());
		temp2.add(new JLabel());
		temp2.add(hoursEstimate);
		temp1.setOpaque(false);
		temp2.setOpaque(false);
		temp3.setOpaque(false);
		temp4.setOpaque(false);
		temp5.setOpaque(false);
		JButton addSite = new JButton("Add Site To List");
		addSite.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Site addTo = dropDown.getItemAt(dropDown.getSelectedIndex());
				if (!modSites.contains(addTo)) {
					modSites.add(addTo);
				}
			}
			
			
		});
		
		JButton clearSites = new JButton("Clear Sites");
		clearSites.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				modSites.clear();
			}
			
			
		});
		temp3.setLayout(new GridLayout(4,0));
		temp3.add(new JLabel());
		temp3.add(new JLabel());
		temp3.add(addSite);
		
		JButton submitModule = new JButton("Add Module");
		submitModule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Site addTo = dropDown.getItemAt(dropDown.getSelectedIndex());
				String name = moduleName.getText();
				float hoursEst = 25;
				try {
					hoursEst = Float.parseFloat(hoursEstimate.getText());
					
				} catch (NumberFormatException n) {
					showMessage("Please enter only an integer. Default value of 25 set.");
					hoursEstimate.setText(hoursEst + "");
					return;
				}
				if (!modSites.contains(addTo)) {
					modSites.add(addTo);
				}
				Module newMod = new Module(hoursEst, name, modSites);
				for (Site s: modSites) {
					s.addModule(newMod);
				}
				moduleName.setText("");
				hoursEstimate.setText("");
				modSites.clear();
				
			}
			
		});
		
		temp4.setLayout(new GridLayout(4,0));
		temp4.add(new JLabel());
		temp4.add(new JLabel());
		temp4.add(submitModule);
		
		temp5.setLayout(new GridLayout(4,0));
		temp5.add(new JLabel());
		temp5.add(new JLabel());
		temp5.add(clearSites);
		
		this.add(new JLabel());
		this.add(new JLabel("Select Site:"));
		this.add(new JLabel());
		this.add(this.dropDown);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(temp5);	
		this.add(new JLabel());	
		this.add(temp3);
		this.add(new JLabel());	
		this.add(new JLabel());
		this.add(new JLabel("Module Name:"));
		this.add(new JLabel());
		this.add(temp1);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel("Hours Estimate:"));
		this.add(new JLabel());
		this.add(temp2);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(temp4);
		JButton simEndDay = new JButton("Test End of Day Sim");
		simEndDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				parentComp.testDayEnd();
			}
			
		});
		this.add(simEndDay);
	
	
		this.startListening();

		this.viewScreen = new SetModulesScreen(this);
		
	}
	

	
	@Override
	public void doTick(long nanos) {
		// TODO Auto-generated method stub
		
		this.viewScreen.onTick(nanos);
		
	}
	
//	@Override
//	public boolean dispatchKeyEvent(KeyEvent e) {
//		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
	
//	@Override
//	public void testAddModule() {
//	//	String text = this.numberOfModules.getText();
//		try {
//			float val = Float.parseFloat(text);
//			Module m = new Module(val);
//			ArrayList<Module> modules = new ArrayList<Module>();
//			modules.add(m);
//			//m.addSite(new Site(modules, 1));
//			this.parentComp.getSMController().addModule(m, "Site1");
//		}
//		catch (NumberFormatException e) {
//			System.out.println("ONLY ENTER NUMBER VALUES");
//		}
//	}




	
	
	
	
}
