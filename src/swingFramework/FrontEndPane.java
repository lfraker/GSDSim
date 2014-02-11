package swingFramework;

import gamePanes.Pane;
import gamePanes.SetModulesPane;
import gamePanes.SettingsPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import components.Difficulty;
import components.ProcessSimulator;

import backend.VectorI;

/*
 * Parts of this class were originally written by Luke Fraker, but inspired by Zach Davis's
 * support code, written for the Brown class, CS195n 2D GameEngines. Permission to use Zach's code in full
 * has been requested and granted by Zach, so long as he is credited and it is not used for revenue in anyway.
 */
public class FrontEndPane {
	
	private JFrame window;
	private ProcessSimulator pSim = new ProcessSimulator();
	private JTabbedPane frames;
	static final VectorI DEFAULT_WINDOW_SIZE = new VectorI(960, 540);
	static final VectorI MINIMUM_WINDOW_SIZE = new VectorI(960, 540);
	private static final int DEFAULT_DELAY_MILLIS = 1000 / 75;
	private long lastTickNanos;
	private Timer timer;
	private SettingsPane settings;
	private SetModulesPane modules;
	long currTime;
	int dayCount;
	Difficulty difficulty;
	float dayTime;
	boolean canPause = false;
	boolean paused = false;
	
	
	
	
	public void setupFrame() {
		this.window = new JFrame("GSD Sim");
		this.frames = new JTabbedPane(JTabbedPane.LEFT);
		this.frames.setMinimumSize(new Dimension(MINIMUM_WINDOW_SIZE.x, MINIMUM_WINDOW_SIZE.y));
		this.frames.setPreferredSize(new Dimension(DEFAULT_WINDOW_SIZE.x, DEFAULT_WINDOW_SIZE.y));
		this.settings = new SettingsPane(this);
		this.modules = new SetModulesPane(this);
		this.settings.setupSwingPane();
		this.frames.add("Settings", this.settings);
		this.modules.setupSwingPane();
		this.frames.add("Modules", this.modules);
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
	
	public JTabbedPane getFrame() {
		return this.frames;
		
	}
	public long getTime() {
		return this.currTime;
	}
	public float getDayTime() {
		return this.dayTime;
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
	
	public Difficulty getDifficulty() {
		return this.difficulty;
	}
	
	public void testDayEnd() {
		this.currTime = TimeUnit.NANOSECONDS.convert((((long)this.dayTime) + 1), TimeUnit.MINUTES);
		

	}
	
	final void doTick() {
		String fTime = TimeUnit.MINUTES.convert(this.currTime, TimeUnit.NANOSECONDS) + "." + (TimeUnit.SECONDS.convert(this.currTime, TimeUnit.NANOSECONDS) % 60);
		//System.out.println(fTime);
		if (Float.parseFloat(fTime) >= this.dayTime) {
			//System.out.println(this.dayTime);
			this.dayCount++;
			this.currTime = 0;
			this.getpSim().endOfDaySim();
			return;
		}
		
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
		//System.out.println("THIS PAUSED : " + this.paused);
		//System.out.println("THIS CAN PAUSE : " + this.canPause);
		int currID = this.frames.getSelectedIndex();
		String currTab = this.frames.getTitleAt(currID);
		if (!currTab.equals("Settings")) {
			if (!this.paused) {
				//System.out.println(this.paused);
				this.currTime += delta;
			}
		}
		((Pane)this.frames.getComponent(currID)).doTick(delta);
		this.frames.getComponent(currID).repaint();
		lastTickNanos = currentNanos;
	}

	public void setTimePerDay(float time) {
		this.dayTime = time;
		
	}
	
	public void setCanPause() {
		this.canPause = true;
	}
	public void setCantPause() {
		this.canPause = false;
	}
	
	public void pauseUnpause() {
		if (this.canPause) {

			this.paused = !this.paused;
		}
	}
	public boolean isPaused() {
		return this.paused;
	}

	public void setDifficulty(Difficulty diff) {
		switch (diff) {
			case EASY: this.canPause = true;
				break;
			case MEDIUM: this.canPause = false;
				break;
			case HARD: this.canPause = false;
				break;
		}
		this.difficulty = diff;
		
	}

	public ProcessSimulator getpSim() {
		return pSim;
	}

	public void setpSim(ProcessSimulator pSim) {
		this.pSim = pSim;
	}
	
	
	
	
}