package game.swingFramework;

import game.gamePanes.Pane;


import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.org.openstreetmap.gui.jmapviewer.Layer;
import game.org.openstreetmap.gui.jmapviewer.LayerGroup;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import game.components.Button;
import game.components.Difficulty;
import game.components.Module;
import game.components.ProcessSimulator;
import game.components.Site;
import game.components.SiteModuleController;

import game.backend.VectorI;

/*
 * Parts of this class were originally written by Luke Fraker, but inspired by Zach Davis's
 * support code, written for the Brown class, CS195n 2D GameEngines. Permission to use Zach's code in full
 * has been requested and granted by Zach, so long as he is credited and it is not used for revenue in anyway.
 */
public class FrontEndPane {
	
	private JFrame window;
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
	private JMapViewer siteStatus;
	private VectorI windSize;
	private SiteModuleController modSiteController = new SiteModuleController();
	private int zoom;
	
	
	
	public void setupFrame() {
		this.setWindow(new JFrame("GSD Sim"));
		this.frames = new JTabbedPane(JTabbedPane.LEFT);
		this.frames.setMinimumSize(new Dimension(MINIMUM_WINDOW_SIZE.x, MINIMUM_WINDOW_SIZE.y));
		this.frames.setPreferredSize(new Dimension(DEFAULT_WINDOW_SIZE.x, DEFAULT_WINDOW_SIZE.y));
		this.settings = new SettingsPane(this);
		this.modules = new SetModulesPane(this);
		this.siteStatus = new JMapViewer(this);
//        LayerGroup germanyGroup = new LayerGroup("Germany");
//        
//        Layer germanyWestLayer = germanyGroup.addLayer("Germany West");
//
//        MapMarkerDot ebersheim = new MapMarkerDot(null, "Ebersheim", 49.91, 8.24);
//
//		this.siteStatus.addMapMarker(ebersheim);
		this.settings.setupSwingPane();
		this.frames.add("Settings", this.settings);
		this.modules.setupSwingPane();
		this.frames.add("Modules", this.modules);
		this.frames.add("Sites", this.siteStatus);
		this.getWindow().add(this.frames);

		
		
		this.timer = new Timer(DEFAULT_DELAY_MILLIS, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				doTick();
			}
		});
		this.getWindow().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		this.getWindow().pack();
		this.getWindow().setVisible(true);
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
		this.getWindow().remove(this.frames);
		this.getWindow().dispose();
		this.setWindow(null);
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
//		for (Site s : this.modSiteController.getSites()) {
//			System.out.println("SITE : " + s.getName());
//			for (Module m: s.getModules()) {
//				System.out.println("MODULE : " + m.getName());
//			}
//		}

		String fTime = TimeUnit.MINUTES.convert(this.currTime, TimeUnit.NANOSECONDS) + "." + (TimeUnit.SECONDS.convert(this.currTime, TimeUnit.NANOSECONDS) % 60);
		//System.out.println(fTime);
		if (Float.parseFloat(fTime) >= this.dayTime) {
			//System.out.println(this.dayTime);
			this.dayCount++;
			this.currTime = 0;
			//this.modSiteController.endDay();
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
		//if (!currTab.equals("Settings")) {
		if (!this.paused) {
				//System.out.println(this.paused);
			this.currTime += delta;
		}
	//	}
		if (!currTab.equals("Sites")) {
			((Pane)this.frames.getComponent(currID)).doTick(delta);
		}
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
						this.paused = false;
				break;
			case HARD: this.canPause = false;
						this.paused = false;
				break;
		}
		this.difficulty = diff;
		
	}

//	public ProcessSimulator getpSim() {
//		return pSim;
//	}

//	public void setpSim(ProcessSimulator pSim) {
//		this.pSim = pSim;
//	}

	public VectorI getWindSize() {
		// TODO Auto-generated method stub
		return this.windSize;
	}

	public void setWindSize(int width, int height) {
		// TODO Auto-generated method stub
		this.windSize = new VectorI(width, height);
	}

	public SiteModuleController getSMController() {
		// TODO Auto-generated method stub
		return this.modSiteController;
	}

	public JFrame getWindow() {
		return window;
	}

	public void setWindow(JFrame window) {
		this.window = window;
	}
	
	public int getZoom () {
		return this.siteStatus.getZoom();
	}
	
	public Point getCenter() {
		return this.siteStatus.getCenter();
	}
	
	public int getMapWidth() {
		return this.siteStatus.getWidth();
	}
	
	public int getMapHeight() {
		return this.siteStatus.getHeight();
	}
	
	public JMapViewer getMapViewer() {
		return this.siteStatus;
	}
	
	public void addSiteToCombo(Site toAdd) {
		this.modules.addSite(toAdd);
	}
	
	public Button getPauseButton() {
		return this.siteStatus.getPauseButton();
	}
	
	public boolean canPause() {
		return this.canPause;
	}
	
	
}