package gamePanes;

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

import paneScreens.SettingsScreen;
import swingFramework.FrontEndPane;

@SuppressWarnings("serial")
public class SettingsPane extends Pane {
	
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
		//this.setLayout(new GridLayout(3,3));
		//this.add(new JLabel("Difficulty:"));
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