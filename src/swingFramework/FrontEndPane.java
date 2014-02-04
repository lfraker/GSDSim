package swingFramework;

import gamePanes.Pane;
import gamePanes.SettingsPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import backend.VectorI;


public class FrontEndPane {
	
	private JFrame window;
	private JTabbedPane frames;
	static final VectorI DEFAULT_WINDOW_SIZE = new VectorI(960, 540);
	static final VectorI MINIMUM_WINDOW_SIZE = new VectorI(960, 540);
	private static final int DEFAULT_DELAY_MILLIS = 1000 / 75;
	private long lastTickNanos;
	private Timer timer;
	private SettingsPane settings;
	float currTime;
	int dayCount;
	int difficulty;
	float dayTime;
	
	
	
	
	public void setupFrame() {
		this.window = new JFrame("GSD Sim");
		this.frames = new JTabbedPane(JTabbedPane.LEFT);
		this.frames.setMinimumSize(new Dimension(MINIMUM_WINDOW_SIZE.x, MINIMUM_WINDOW_SIZE.y));
		this.frames.setPreferredSize(new Dimension(DEFAULT_WINDOW_SIZE.x, DEFAULT_WINDOW_SIZE.y));
		this.settings = new SettingsPane(this);
		this.settings.setupSwingPane();
		this.frames.add("Settings", this.settings);
		this.window.add(this.frames);
		
		
		this.timer = new Timer(DEFAULT_DELAY_MILLIS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doTick();
			}
		});
		this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		this.window.pack();
		this.window.setVisible(true);
		doStart();
	}
	
	public float getTime() {
		return this.currTime;
	}
	public int getDays() {
		return this.dayCount;
	}
	public void doClose() {
		timer.stop();
		//this.stopListening();
		this.window.remove(this.frames);
		this.window.dispose();
		this.window = null;
	}
	
	public void doStart() {
		timer.start();
		lastTickNanos = System.nanoTime();
	}
	
	final void doTick() {
		long currentNanos = System.nanoTime();
		long delta = currentNanos - lastTickNanos;
		
//		tickTimes[tickTimesIndex = (tickTimesIndex + 1) % NUM_FRAMES_TO_AVERAGE] = delta;
//		if (debug) {
//			updateTitle();
//		}
		
//		try {
//			doTick(delta);
//		} catch (Throwable t) {
//			throwableGenerated("onTick", t);
//		}
		int currID = this.frames.getSelectedIndex();
		((Pane)this.frames.getComponent(currID)).doTick(delta);
		this.frames.getComponent(currID).repaint();
		lastTickNanos = currentNanos;
	}

	public void setTimePerDay(float time) {
		this.dayTime = time;
		
	}

	public void setDifficulty(int diff) {
		this.difficulty = diff;
		
	}
	
	
	
	
}