package gamePanes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import components.Difficulty;

import backend.VectorI;


import paneScreens.Screen;
import swingFramework.FrontEndPane;

@SuppressWarnings("serial")
public abstract class Pane extends JComponent implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener, ComponentListener, KeyEventDispatcher {
	Screen viewScreen;
	VectorI windSize;
	boolean resizeCalled = false;
	FrontEndPane parentComp;
	JTabbedPane frameWork;
	private static final int MILLIS_TO_WAIT_FOR_REPEAT = 5;

	
	
	public Pane(FrontEndPane fP) {
		this.parentComp = fP;
		this.frameWork = this.parentComp.getFrame();
	}
	
	public void setCanPause() {
		this.parentComp.setCanPause();
	}
	public void setCantPause() {
		this.parentComp.setCantPause();
	}
	
	public void pauseUnpause() {
		this.parentComp.pauseUnpause();
	}
	public boolean isPaused() {
		return this.parentComp.isPaused();
	}
	
	public void setTimePerDay(float time) {
		this.parentComp.setTimePerDay(time);
	}
	public void setDifficulty(Difficulty diff) {
		this.parentComp.setDifficulty(diff);
	}
	
	
	public float getTime () {
		return this.parentComp.getTime();
	}
	
	public float getTotalTime () {
		return this.parentComp.getDayTime();
	}
	public String getDayTimer() {
		return TimeUnit.MINUTES.convert(this.parentComp.getTime(), TimeUnit.NANOSECONDS) + ":" + (TimeUnit.SECONDS.convert(this.parentComp.getTime(), TimeUnit.NANOSECONDS) % 60);
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
		this.resizeCalled = true;
		VectorI newSize = new VectorI(width, height);
		this.windSize = newSize;

//		try {
		this.viewScreen.onResize(newSize);
//		} catch (Throwable t) {
//			throwableGenerated("onResize", t);
//		}
	}
	
	public void doDraw(Graphics2D g) {
		this.viewScreen.onDraw(g);
		g.setFont(new Font("serif", Font.BOLD, (this.windSize.x / 95)));
		g.setColor(Color.BLACK);
		float x = (this.windSize.x * (6.5f/8.0f));
		String toWrite1 = "Current Time: " + this.getDayTimer() + " minutes";
		String toWrite2	= "Length of Day: " + this.getTotalTime() + " minutes";
		String toWrite3 = "Day Count: " + this.getDays();
		String toWrite4 = "";
		switch (this.parentComp.getDifficulty()) {
			case EASY:	toWrite4 = "Difficulty: EASY (pause enabled)";
				break;
			case MEDIUM:	toWrite4 = "Difficulty: MEDIUM (pause disabled)";
				break;
			case HARD:	toWrite4 = "Difficulty: HARD (pause disabled)";
				break;
		}
		g.drawString(toWrite1, x, 30.0f);
		g.drawString(toWrite2, x, 45.0f);
		g.drawString(toWrite3, x, 60.0f);
		g.drawString(toWrite4, x, 75.0f);
		if (this.parentComp.isPaused()) {
			g.drawString("PAUSED", x, 90.0f);
		}

	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		Dimension d = e.getComponent().getSize();
		callOnResize(d.width, d.height);
	}
	
	public abstract void doTick(long nanos);
	
	public abstract void setupSwingPane();


	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_P) {

			if (!this.frameWork.getTitleAt(this.frameWork.getSelectedIndex()).equals("Settings")) { 
				this.parentComp.pauseUnpause();
			}
		}
		
	}
	java.util.List<RealReleaseWaiter> waiters = new ArrayList<RealReleaseWaiter>();
	
	private void queueKeyReleased(KeyEvent e) {
		waiters.add(new RealReleaseWaiter(e));
	}
	
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (!e.isConsumed()) {
			switch (e.getID()) {
			case KeyEvent.KEY_PRESSED:
				keyPressed(e);
				return true;
			case KeyEvent.KEY_RELEASED:
				queueKeyReleased(e);
				return true;
			case KeyEvent.KEY_TYPED:
				keyTyped(e);
				return true;
			}
		}
		return false;
	}
	
	
	private class RealReleaseWaiter implements ActionListener {
		private boolean cancelled = false;
		private KeyEvent evt;
		private Timer t;
		
		public RealReleaseWaiter(KeyEvent evt) {

			this.evt = evt;
			t = new Timer(MILLIS_TO_WAIT_FOR_REPEAT, this);
			t.start();
		}
		
		public int code() {
			return evt.getKeyCode();
		}
		
		public void cancel() {
			cancelled = true;
			t.stop();
			waiters.remove(this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (cancelled)
				return;
			cancel();
			keyReleased(evt);
		}
		
	}
}
	
	
