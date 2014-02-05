package gamePanes;

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

import paneScreens.SettingsScreen;
import swingFramework.FrontEndPane;

@SuppressWarnings("serial")
public class SettingsPane extends Pane {
	public JTextArea laborCost;
	public JTextArea followTheSun;
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

		this.laborCost = new JTextArea();
		this.laborCost.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		newPanel.add(new JLabel("Laber Cost Per Location:"));
		this.laborCost.setPreferredSize(new Dimension(50,25));
		newPanel.setOpaque(false);
		newPanel.add(this.laborCost);
		//newPanel.setPreferredSize(new Dimension(50,50));
		this.add(newPanel);
		this.add(new JLabel());
		this.add(new JLabel());

		this.followTheSun = new JTextArea();
		this.followTheSun.setBorder(new BevelBorder(BevelBorder.LOWERED));
		JPanel newPanel2 = new JPanel();
		newPanel2.setLayout(new FlowLayout(FlowLayout.LEFT));
		newPanel2.add(new JLabel("Follow The Sun Handoff Time:"));
		this.followTheSun.setPreferredSize(new Dimension(50,25));
		newPanel2.setOpaque(false);
		newPanel2.add(this.followTheSun);
		//newPanel.setPreferredSize(new Dimension(50,50));
		this.add(newPanel2);
		this.startListening();
		this.viewScreen = new SettingsScreen(this);
		((SettingsScreen)this.viewScreen).readSettings();
		
	}
	
	public void setTimePerDay(float time) {
		this.parentComp.setTimePerDay(time);
	}
	public void setDifficulty(int diff) {
		this.parentComp.setDifficulty(diff);
	}
	
	@Override
	public void doTick(long nanos) {
		// TODO Auto-generated method stub
		
		this.viewScreen.onTick(nanos);
		
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		// TODO Auto-generated method stub
		return false;
	}

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




	
	
	
	
}