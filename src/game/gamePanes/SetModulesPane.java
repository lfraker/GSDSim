package game.gamePanes;

import java.awt.Dimension;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.KeyEventDispatcher;
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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;

import java.util.ArrayList;

import game.components.Module;
import game.components.Site;

import game.paneScreens.SetModulesScreen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;

@SuppressWarnings("serial")
public class SetModulesPane extends Pane {
	public JTextArea numberOfModules = new JTextArea();
//	private JButton easy;
//	private JButton medium;
//	private JButton hard;
//	viewScreen = new SettingsScreen();
	
	public SetModulesPane(FrontEndPane fP) {
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

		this.numberOfModules = new JTextArea();
		this.numberOfModules.setEditable(true);
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
		this.numberOfModules.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		newPanel.add(new JLabel("Laber Cost Per Location:"));
		this.numberOfModules.setPreferredSize(new Dimension(50,25));
		newPanel.setOpaque(false);
		newPanel.add(this.numberOfModules);
		//newPanel.setPreferredSize(new Dimension(50,50));
		this.add(newPanel);
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
	
	@Override
	public void testAddModule() {
		String text = this.numberOfModules.getText();
		try {
			float val = Float.parseFloat(text);
			Module m = new Module(val);
			ArrayList<Module> modules = new ArrayList<Module>();
			modules.add(m);
			m.addSite(new Site(modules, 1));
			this.parentComp.getpSim().addModule(m, "Site1");
		}
		catch (NumberFormatException e) {
			System.out.println("ONLY ENTER NUMBER VALUES");
		}
	}




	
	
	
	
}
