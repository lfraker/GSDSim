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

import game.components.DevelopmentMethod;
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
	public JComboBox<String> devDropDown = new JComboBox<>();
	List<Site> modSites = new ArrayList<>();
//	private JButton medium;
//	private JButton hard;
//	viewScreen = new SettingsScreen();
	
	public SetModulesPane(FrontEndPane fP) {
		super(fP);
		this.dropDown.addItem(new Site("Select a Site", 0, null, 0));
		// TODO Auto-generated constructor stub
		this.devDropDown.addItem("Select a Development Method");
		this.devDropDown.addItem("Follow The Sun");
		this.devDropDown.addItem("Waterfall");
		this.devDropDown.addItem("Agile");

	}
	
	public void addSiteToCombo(Site toAdd) {
		this.dropDown.addItem(toAdd);
		
	}

	public void clearSites() {
		this.dropDown.removeAllItems();
		this.dropDown.addItem(new Site("Select a Site", 0, null, 0));
	}
	
//	public List<Site> getSiteList() {
//		return modSites;
//	}

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
		JPanel temp6 = new JPanel();
		JPanel temp7 = new JPanel();
		JPanel temp8 = new JPanel();
		JPanel temp9 = new JPanel();
		JPanel temp10 = new JPanel();
		JPanel temp11 = new JPanel();
		JPanel temp12 = new JPanel();
		temp1.setLayout(new GridLayout(4,0));
		temp1.add(new JLabel());
		temp1.add(new JLabel());
		temp1.add(moduleName);
		
		temp2.setLayout(new GridLayout(4,0));
		temp2.add(new JLabel());
		temp2.add(new JLabel());
		temp2.add(hoursEstimate);
		temp1.setOpaque(false);
		temp2.setOpaque(false);
		temp3.setOpaque(false);
		temp4.setOpaque(false);
		temp5.setOpaque(false);
//		JButton addSite = new JButton("Add Site To List");
//		addSite.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				Site addTo = dropDown.getItemAt(dropDown.getSelectedIndex());
//				if (!modSites.contains(addTo) && dropDown.getSelectedIndex() != 0) {
//					modSites.add(addTo);
//				}
//			}
//			
//			
//		});
//		
//		JButton clearSites = new JButton("Clear Sites");
//		clearSites.addActionListener(new ActionListener(){
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				modSites.clear();
//			}
//			
//			
//		});
		temp3.setLayout(new GridLayout(4,0));
		temp3.add(new JLabel());
		temp3.add(new JLabel());
		//temp3.add(addSite);
		
		JButton submitModule = new JButton("Add Module To Site");
		submitModule.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Site addTo = dropDown.getItemAt(dropDown.getSelectedIndex());
				String name = moduleName.getText();
				long hoursEst = 25;
				if (name.length() == 0) {
					showMessage("Please enter a module name. Default value of 'core' set.");
					moduleName.setText("core");
					return;
				}
				try {
					hoursEst = Long.parseLong(hoursEstimate.getText());
					
				} catch (NumberFormatException n) {
					showMessage("Please enter only an integer. Default value of 25 set.");
					hoursEstimate.setText(hoursEst + "");
					return;
				}
				
				if (dropDown.getSelectedIndex() == 0) {
					showMessage("Please Choose a Site");
					return;
				}
				if (devDropDown.getSelectedIndex() == 0) {
					showMessage("Please Choose a Development Method");
					return;
				}
				if (!modSites.contains(addTo)) {
					modSites.add(addTo);
				}
				
				Module newMod = new Module(hoursEst, name);
				switch (devDropDown.getSelectedIndex()) {
					case 1: newMod.setDevelopmentMethod(DevelopmentMethod.FOLLOWTHESUN);
							break;
					case 2: newMod.setDevelopmentMethod(DevelopmentMethod.WATERFALL);
							break;
					case 3: newMod.setDevelopmentMethod(DevelopmentMethod.AGILE);
							break;
				}
				addTo.addModule(newMod);
				devDropDown.setSelectedIndex(0);
				dropDown.setSelectedIndex(0);
				showMessage("Module: " + name + " has been added to Site: " + addTo.getName());
				moduleName.setText("");
				hoursEstimate.setText("");
			}
			
		});
		
		temp4.setLayout(new GridLayout(4,0));
		temp4.add(new JLabel());
		temp4.add(new JLabel());
		temp4.add(submitModule);
		
		temp5.setLayout(new GridLayout(4,0));
		temp5.add(new JLabel());
		temp5.add(new JLabel());
		//temp5.add(clearSites);
//		JButton simEndDay = new JButton("Test End of Day Sim");
//		simEndDay.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				parentComp.testDayEnd();
//			}
//			
//		});
		temp6.setLayout(new GridLayout(4,0));
		temp6.add(new JLabel());
		temp6.add(new JLabel());
//		temp6.add(simEndDay);
		temp6.setOpaque(false);
		
		temp7.setLayout(new GridLayout(4,0));
		temp7.add(new JLabel());
		temp7.add(new JLabel());
		temp7.add(dropDown);
		temp7.setOpaque(false);

		temp8.setLayout(new GridLayout(4,0));
		temp8.add(new JLabel());
		temp8.add(new JLabel());
		temp8.add(new JLabel("Select Site:"));
		temp8.setOpaque(false);
		
		temp9.setLayout(new GridLayout(4,0));
		temp9.add(new JLabel());
		temp9.add(new JLabel());
		temp9.add(new JLabel("Module Name:"));
		temp9.setOpaque(false);
		temp10.setLayout(new GridLayout(4,0));
		temp10.add(new JLabel());
		temp10.add(new JLabel());
		temp10.add(new JLabel("Hours Estimate:"));
		temp10.setOpaque(false);
		
		temp11.setLayout(new GridLayout(4,0));
		temp11.add(new JLabel());
		temp11.add(new JLabel());
		temp11.add(new JLabel("Development Method:"));
		temp11.setOpaque(false);
		
		temp12.setLayout(new GridLayout(4,0));
		temp12.add(new JLabel());
		temp12.add(new JLabel());
		temp12.add(devDropDown);
		temp12.setOpaque(false);



		this.add(new JLabel());
		this.add(temp8);
		this.add(new JLabel());
		this.add(temp7);
		this.add(new JLabel());
		
		this.add(new JLabel());
		this.add(temp11);
		this.add(new JLabel());
		this.add(temp12);
		this.add(new JLabel());
		this.add(new JLabel());
		//this.add(temp5);	
		//this.add(new JLabel());	
		//this.add(temp3);
		//this.add(new JLabel());	
		//this.add(new JLabel());
		this.add(temp9);
		this.add(new JLabel());
		this.add(temp1);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(temp10);
		this.add(new JLabel());
		this.add(temp2);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());

		this.add(temp4);
		this.add(new JLabel());
		this.add(temp6);
	
	
		this.startListening();
		this.viewScreen = new SetModulesScreen(this);
		
	}
	

	
	@Override
	public void doTick(long nanos) {
		
		this.viewScreen.onTick(nanos);
		
	}





	@Override
	public void mouseDragged(MouseEvent e) {
		this.viewScreen.onMouseDragged(e);
		
		
	}




	@Override
	public void mousePressed(MouseEvent e) {
		this.viewScreen.onMousePressed(e);
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.viewScreen.onMouseReleased(e);
	}






	
	
	
	
}
