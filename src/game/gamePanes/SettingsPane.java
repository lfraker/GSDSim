package game.gamePanes;

import java.awt.Dimension;


import java.awt.BorderLayout;
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
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

import game.paneScreens.*;
import game.components.Difficulty;
import game.components.Site;
import game.components.Scenario;
import game.components.Scenarios;
import game.components.ScenarioLoader;
import game.swingFramework.FrontEndPane;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;

@SuppressWarnings("serial")
public class SettingsPane extends Pane {
	public JTextArea laborCost;
	public JTextArea followTheSun;
	public JButton loadDefaultSites;
	public ChooseDefaultPane loadDefaultSitesP;
	JFrame settingsConf;
	JTextArea newTex;

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
		//this.add(newPanel);
		JButton modifSett = new JButton("Modify Saved Global Settings File");
		modifSett.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (settingsConf != null) {
					settingsConf.dispose();
					settingsConf = null;
				}
				if (settingsConf == null) {
					settingsConf = new JFrame();
					settingsConf.setMinimumSize(new Dimension(300, 300));
					settingsConf.setPreferredSize(new Dimension(800, 500));
					settingsConf.setLayout(new BorderLayout());
					newTex = new JTextArea();
					JScrollPane texWrap = new JScrollPane(newTex);
					settingsConf.add(texWrap, BorderLayout.CENTER);
					JPanel buttons = new JPanel();
					buttons.setLayout(new FlowLayout());
					JButton save = new JButton("Save Settings");
					save.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							File dir = new File("gameFiles");
							 
							if (!dir.exists()) {
								dir.mkdir();
							}
							

							File file = new File("./gameFiles/settings.txt");
							if (!file.exists()) {
								try {
									file.createNewFile();
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file, false))){
									buffWrite.write(newTex.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							settingsConf.dispose();
							settingsConf = null;
							((SettingsScreen)viewScreen).readSettings();

						}
						
					});
					JButton cancel = new JButton("Cancel");
					cancel.addActionListener(new ActionListener(){

						@Override
						public void actionPerformed(ActionEvent e) {
							settingsConf.dispose();
							settingsConf = null;
							
						}
						
					});
					buttons.add(save);
					buttons.add(cancel);
					settingsConf.add(buttons, BorderLayout.SOUTH);
					
					try (BufferedReader buffRead = new BufferedReader(new FileReader("./gameFiles/settings.txt"))) {
						String setts = "";
						String currLine;
						while ((currLine = buffRead.readLine()) != null) {
							setts = setts + currLine + "\n";
						}
						newTex.setText(setts);
					}
					 catch (FileNotFoundException e1) {
						String message = "There was an error reading the settings file. Please make sure a settings file exists";
						showMessage(message);	
					
					 } catch (IOException e1) {
						 e1.printStackTrace();
					 }
					
					
					settingsConf.pack();
					settingsConf.setVisible(true);
				}
				
			}
			
		});
		
		this.add(modifSett);

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
		//this.add(newPanel2);
		this.add(new JLabel("<html>Make sure settings are correct now. Settings are not editable after loading presets, or starting custom game.</html>"));


		
		
		this.add(new JLabel());
	//	this.add(new JLabel());
		JPanel newPanel3 = new JPanel();
//		newPanel3.setLayout(new GridLayout(3, 0));
//		newPanel3.add(new JLabel());
		loadDefaultSites = new JButton("Load Default Sites and Modules");
		JButton selectSites = new JButton("Choose Custom Sites and Modules");
		selectSites.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parentComp.pickSites();
				
			}
			
		});
		loadDefaultSites.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadDefaultSitesP = new ChooseDefaultPane();
				loadDefaultSitesP.setVisible(true);
			}
		});
//		newPanel3.add(loadDefaultSites);
//		newPanel3.add(selectSites);
//		newPanel3.setOpaque(false);
		//this.add(newPanel3);
		this.add(loadDefaultSites);
		this.add(new JLabel());
		//this.add(new JLabel());
		this.add(selectSites);
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

}
