package gamePanes;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

import javax.swing.JComponent;

import backend.VectorI;


import paneScreens.Screen;
import swingFramework.FrontEndPane;

@SuppressWarnings("serial")
public abstract class Pane extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, ComponentListener, KeyEventDispatcher {
	Screen viewScreen;
	VectorI windSize;
	boolean resizeCalled = false;
	FrontEndPane parentComp;
	
	public Pane(FrontEndPane fP) {
		this.parentComp = fP;
	}
	
	
	public float getTime () {
		return this.parentComp.getTime();
	}
	
	public int getDays() {
		return this.parentComp.getDays();
	}
	@Override
	public void paint(Graphics g) {
		Rectangle r = g.getClipBounds();
		g.clearRect(r.x, r.y, r.width, r.height);
		if (!resizeCalled) {
			callOnResize(r.width, r.height);
		}
		doDraw((Graphics2D) g);
		super.paint(g);

	}
	
	void startListening() {
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addComponentListener(this);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this);
	}
	
	void stopListening() {
		removeMouseListener(this);
		removeMouseMotionListener(this);
		removeMouseWheelListener(this);
		removeComponentListener(this);
		KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
	}
	
	public void callOnResize(int width, int height) {
		resizeCalled = true;
		VectorI newSize = new VectorI(width, height);
		windSize = newSize;

//		try {
		this.viewScreen.onResize(newSize);
//		} catch (Throwable t) {
//			throwableGenerated("onResize", t);
//		}
	}
	
	public void doDraw(Graphics2D g) {
		this.viewScreen.onDraw(g);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		Dimension d = e.getComponent().getSize();
		callOnResize(d.width, d.height);
	}
	
	public abstract void doTick(long nanos);
	
	public abstract void setupSwingPane();
	
	
}