package game.swingFramework;

import game.gamePanes.Pane;

import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.org.openstreetmap.gui.jmapviewer.Layer;
import game.org.openstreetmap.gui.jmapviewer.LayerGroup;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import game.components.Button;
import game.components.Difficulty;
import game.components.FinalReport;
import game.components.GameState;
import game.components.Module;
import game.components.ProcessSimulator;
import game.components.Site;

import game.backend.VectorI;

/*
 * Parts of this class were originally written by Luke Fraker, but inspired by Zach Davis's
 * support code, written for the Brown class, CS195n 2D GameEngines. Permission to use Zach's code in full
 * has been requested and granted by Zach, so long as he is credited and it is not used for revenue in anyway.
 */
public class FrontEndPane {
	
	private JFrame window;
	public JTabbedPane frames;
	public static final VectorI DEFAULT_WINDOW_SIZE = new VectorI(1100, 700);
	public static final VectorI MINIMUM_WINDOW_SIZE = new VectorI(1100, 700);
	private static final int DEFAULT_DELAY_MILLIS = 1000 / 50;
	public static final float revPredict = 500000000.0f;
	public static final float budgStart = 400000000.0f;

	private long lastTickNanos;
	public Timer timer;
	public SettingsPane settings;
	public static SetModulesPane modules;
	long currTime;
	public int dayCount;
	int hourCount;
	public Difficulty difficulty = Difficulty.HARD;
	public long tem = (550000000 * 100);
	public long dayTime = tem;
	boolean canPause = false;
	boolean canStartSim = false;
	public boolean paused = false;
	public boolean gameEnded = false;
	public static JMapViewer siteStatus;
	private VectorI windSize;
	public Map<String,String> globalParams = new HashMap<>();
	public float expectedTotalBudge = 0.0f;
	public static ProcessSimulator processSimulator = new ProcessSimulator();
	
	//public static SiteModuleController modSiteController = new SiteModuleController(processSimulator);

	private int zoom;
	public boolean timeStart = false;
	private boolean loadedSim = false;
	private FinalReport finReport;
	
	
	
	public void setupFrame() {
		this.setWindow(new JFrame("GSD Sim"));
		this.frames = new JTabbedPane(JTabbedPane.TOP + JTabbedPane.HORIZONTAL);
		this.frames.setMinimumSize(new Dimension(MINIMUM_WINDOW_SIZE.x, MINIMUM_WINDOW_SIZE.y));
		this.frames.setPreferredSize(new Dimension(DEFAULT_WINDOW_SIZE.x, DEFAULT_WINDOW_SIZE.y));
		this.getWindow().setMinimumSize(new Dimension(DEFAULT_WINDOW_SIZE.x - 100, DEFAULT_WINDOW_SIZE.y - 100));
		this.getWindow().setPreferredSize(new Dimension(DEFAULT_WINDOW_SIZE.x, DEFAULT_WINDOW_SIZE.y));
		this.settings = new SettingsPane(this);
		this.modules = new SetModulesPane(this);
		this.siteStatus = new JMapViewer(this);
		this.siteStatus.setDisplayPositionByLatLon(20, 10, 2);
//        LayerGroup germanyGroup = new LayerGroup("Germany");
//        
//        Layer germanyWestLayer = germanyGroup.addLayer("Germany West");
//
//        MapMarkerDot ebersheim = new MapMarkerDot(null, "Ebersheim", 49.91, 8.24);
//
//		this.siteStatus.addMapMarker(ebersheim);
		this.frames.add("Settings", this.settings);

		this.frames.add("Sites", this.siteStatus);
		this.settings.setupSwingPane();

		this.frames.add("Modules", this.modules);

		this.modules.setupSwingPane();
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
		try {
			this.expectedTotalBudge = Float.parseFloat(this.globalParams.get("UsrMoney"));
		}
		catch (NumberFormatException e) {
			this.expectedTotalBudge = this.budgStart;
			this.globalParams.put("UsrMoney", this.budgStart+"");
		}
		disableSites();
		disableModules();
		processSimulator.setFP(this);
		doStart();

	}
	
	public JTabbedPane getFrame() {
		return this.frames;
	}
	
	public void enableSettings() {
		int tempInd = this.frames.indexOfTab("Settings");
		this.frames.setEnabledAt(tempInd, true);	
	}
	public void disableSettings() {
		int tempInd = this.frames.indexOfTab("Settings");
		this.frames.setEnabledAt(tempInd, false);	
	}
	public void enableModules() {
		int tempInd = this.frames.indexOfTab("Modules");
		this.frames.setEnabledAt(tempInd, true);	
	}
	public void disableModules() {
		int tempInd = this.frames.indexOfTab("Modules");
		this.frames.setEnabledAt(tempInd, false);	
	}
	public void enableSites() {
		int tempInd = this.frames.indexOfTab("Sites");
		this.frames.setEnabledAt(tempInd, true);	
	}
	public void disableSites() {
		int tempInd = this.frames.indexOfTab("Sites");
		this.frames.setEnabledAt(tempInd, false);	
	}
	public long getTime() {
		return this.currTime;
	}
	public long getDayLength() {
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
	
	
	public final void doTick() {
		if (this.globalParams.get("UsrMoney").length() == 0) {
			this.updateGlobalParam("UsrMoney", this.budgStart+"");
		}
		if (this.globalParams.get("Rev6Month").length() == 0) {
			this.updateGlobalParam("Rev6Month", this.revPredict+"");
		}
		if (this.processSimulator.HasFailed) {
			JOptionPane.showMessageDialog(this.window, "A site has encountered too many problmes and failed.\n" +
					"It was not able to recover, and caused the project to fail.\n" +
					"The game has ended. Restart to start a new game.");
		}
		if (!this.gameEnded) {
			endGame();
		}
		else {
			if (this.finReport == null || !this.finReport.isVisible()) {
				System.exit(0);
			}

			return;
		}
		long hour = (long)(this.dayTime / 24);
		long currentNanos = System.nanoTime();
		long delta = currentNanos - lastTickNanos;

		if(!this.paused && this.timeStart) 
		{
			this.currTime += delta;
			this.processSimulator.UpdateTime(currTime);

			if ((this.currTime / hour) > hourCount) 
			{
				this.hourCount = (int)(this.currTime / hour);
				this.processSimulator.ProcessSites();

				if(hourCount >= 24 && hourCount % 24 == 0)
				{
					//New Day..
					this.dayCount++;
					
					for (Site s: this.processSimulator.GetSites()) {
						if (s.getModules().size() > 0) {
							float mon = Float.parseFloat(this.getGlobalParam("UsrMoney"));
							float newMon = (mon - (s.costDeveloperDay * s.GetNumberWorkers()));
							this.updateGlobalParam("UsrMoney", newMon+"");
						}
					}
				}

				//this.processSimulator.SaveState();
				//System.exit(0);

				return;
			}

		}
		
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
		
	//	}
		if (!currTab.equals("Sites")) {
			((Pane)this.frames.getComponent(currID)).doTick(delta);
		}
		this.frames.getComponent(currID).repaint();

		lastTickNanos = currentNanos;
		
	}

	public void setTimePerDay(long time) {
		this.dayTime = time;
		this.processSimulator.SetDayLength(time);
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
	
	public void updateGlobalParam(String param, String val) {
		this.globalParams.put(param, val);
	}
	
	public String getGlobalParam(String param) {
		return this.globalParams.get(param);
	}

	public void setDifficulty(Difficulty diff) {
		switch (diff) {
			case EASY: this.canPause = true;
				break;
//			case MEDIUM: this.canPause = false;
//						this.paused = false;
//				break;
			case HARD: this.canPause = false;
						this.paused = false;
				break;
		}
		this.difficulty = diff;
		
	}


	public VectorI getWindSize() {
		// TODO Auto-generated method stub
		return this.windSize;
	}

	public void setWindSize(int width, int height) {
		// TODO Auto-generated method stub
		this.windSize = new VectorI(width, height);
	}

	/*
	public SiteModuleController getSMController() {
		// TODO Auto-generated method stub
		return this.modSiteController;
	}
	*/
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
	
	//UNTESTED
	public static void addSiteToCombo(Site toAdd) {
		modules.addSiteToCombo(toAdd);
	}
	
	public Button getPauseButton() {
		return this.siteStatus.getPauseButton();
	}
	
	public boolean canPause() {
		return this.canPause;
	}

	public void startLoadedSim() {
		// TODO Auto-generated method stub
		this.timeStart = true;
		int tempInd = this.frames.indexOfTab("Sites");
		this.frames.setSelectedIndex(tempInd);
		this.disableSettings();
	}
	
	public void loadedSim() {
		this.loadedSim = true;
	}
	
	public boolean isSimLoaded() {
		return this.loadedSim;
	}
	
	public void pickSites() {
		int tempInd = this.frames.indexOfTab("Sites");
		this.frames.setSelectedIndex(tempInd);
		this.disableSettings();
		this.enableModules();
		this.enableSites();
		this.canStartSim = true;
	}

	//UNTESTED
	public void startCustomSim() {
		// TODO Auto-generated method stub
		if (this.processSimulator.GetSites().size() > 0) {
			for (Site s : this.processSimulator.GetSites()) {
				if (s.getModules().size() > 0) {
					this.canStartSim = false;
					this.timeStart = true;
					this.loadedSim = true;
					int tempInd = this.frames.indexOfTab("Sites");
					this.frames.setSelectedIndex(tempInd);
					this.disableModules();
		    		JOptionPane.showMessageDialog(this.window, "Sim is started.");
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(this.window, "Pick at least one site and at least one module to start");

		
	}

	public boolean canStartSim() {
		return this.canStartSim;
	}
	
	//UNTESTED
	public void saveCustomGameScen(String saveFileName) {
		if (this.processSimulator.GetSites().size() > 0) {
			for (Site s : this.processSimulator.GetSites()) {
				if (s.getModules().size() > 0) {
					GameState gs = new GameState(this.processSimulator.GetSites());
					File dir = new File("Scenarios");
				 
					if (!dir.exists()) {
						dir.mkdir();
					}	
				
					gs.SaveState("./Scenarios/" + saveFileName);
		    		JOptionPane.showMessageDialog(this.window, "Custom scenario was Saved.");
					return;
				}
			}
		}
		JOptionPane.showMessageDialog(this.window, "Pick at least one site and at least one module to save a custom scenario");
		
	}
	
	public void endGame() {
		if (!timeStart) {
			return;
		}
		for (Site s: this.processSimulator.GetSites()) {
			for (Module m: s.getModules()) {
				if (!m.isComplete()) {
					return;
				}
			}
		}
		this.gameEnded = true;
		this.getWindow().setVisible(false);
		String rep = "";
		float expEffTot = 0.0f;
		float actEffTot = 0.0f;
//		float expBudgTot = 0.0f;
//		float actBudgTot = 0.0f;
		float maxExpEff = 0.0f;
		float maxActEff = 0.0f;
		Module largestMod = null;
		int mWork = 0;
		for (Site s: this.processSimulator.GetSites()) {
//			float expBudg = 0.0f;
//			float actBudg = 0.0f;
			float temMaxExp = 0.0f;
			float temMaxAct = 0.0f;
			for (Module m: s.getModules()) {
				rep += "\nModule " + m.getName() + " (Development Method: " + m.getDevelopmentMethod()+")";
				for (int i = 0; i < 7; i++) {
					mWork = m.getNumberWorkers();
					expEffTot += m.origStepEstimates[i];
					//actEffTot += m.stepEstimates[i];
					temMaxExp += (m.origStepEstimates[i] / m.getNumberWorkers());
				//	temMaxAct += m.stepEstimates[i];
//					expBudg += (m.origStepEstimates[i] * (s.GetCostDeveloperDay() * s.GetNumberWorkers()));
//					actBudg += ((m.stepEstimates[i] * (1.0f - s.GetEffortPerDeveloperDay())) + (m.stepEstimates[i] * (s.GetCostDeveloperDay() * s.GetNumberWorkers())));
					switch (i) {
						case 0: rep += "\n\tDesign:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " man hours\t|\tActual: " + m.stepEstimates[i] + " man hours\n";
							break;
						case 1: rep += "\tImplementation:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " Man Hours\t|\tActual: " + m.stepEstimates[i] + " Man Hours\n";
							break;
						case 2: rep += "\tUnit test:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " Man Hours\t|\tActual: " + m.stepEstimates[i] + " Man Hours\n";
							break;
						case 3: rep += "\tIntegration:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " Man Hours\t|\tActual: " + m.stepEstimates[i] + " Man Hours\n";
							break;
						case 4: rep += "\tSystem test:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " Man Hours\t|\tActual: " + m.stepEstimates[i] + " Man Hours\n";
							break;
						case 5: rep += "\tDeployment:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " Man Hours\t|\tActual: " + m.stepEstimates[i] + " Man Hours\n";
							break;
						case 6: rep += "\tAcceptance test:\n" +
								"\t\tEstimate: " + m.origStepEstimates[i] + " Man Hours\t|\tActual: " + m.stepEstimates[i] + " Man Hours\n";
							break;
					}	
				}
				actEffTot += m.ActualWorkDone();
				temMaxAct += m.GetHoursElapsed();
			}

			maxExpEff = Math.max(maxExpEff, temMaxExp);
			maxActEff = Math.max(maxActEff, temMaxAct);
//			expBudgTot += expBudg;
//			actBudgTot += actBudg;
		}
		//actualworkdone
		rep+= "\n\nTotal Effort Statistics:\n" +
				"\n\tExpected Effort for Entire Project: " + expEffTot + " Man Hours\n\n" +
				"\tActual Effort for Entire Project: " + actEffTot  + " Man Hours\n\n";
		int expDay = 0;
		int actDay = 0;
		if ((maxExpEff % 9) > 0 && (maxExpEff % 9) < 9) {
			expDay = (((int)(maxExpEff / 9)) + 1);
		}
		else {
			expDay = ((int)(maxExpEff / 9));
		}
		
		if ((maxActEff % 9) > 0 && (maxActEff % 9) < 9) {
			actDay = (((int)(maxActEff / 9)) + 1);
		}
		else {
			actDay = ((int)(maxActEff / 9));
		}

		rep+= "\n\nTotal Day Statistics:\n" +
				"\n\tExpected Days for Entire Project: " + expDay + " Days\n\n" +
				"\tActual Days for Entire Project: " + actDay + " Days\n\n";
		float dayDiff = expDay - actDay;

		if (dayDiff < 0) {
			rep += "\n\n\tProject finished " + dayDiff + " days late.\n\n";
		}
		else {
			rep += "\n\n\tProject finished " + dayDiff + " days early.\n\n";
		}
		
		float currBudg = this.budgStart;
		try {
			currBudg = Float.parseFloat(this.globalParams.get("UsrMoney"));
		}
		catch (NumberFormatException e) {

		}
		float actBudgTot = (this.expectedTotalBudge - currBudg);
		float leftBudg = (this.expectedTotalBudge - actBudgTot);
		rep += "\n\nBudget Statistics:\n" +
				"\n\tExpected Budget Used for Entire Project: "  + this.expectedTotalBudge + " Euros\n\n" +
				"\tActual Budget Used for Entire Project: " + actBudgTot + " Euros\n\n";
		
		if (leftBudg < 0) {
			rep += "\n\n\tProject finished " + Math.abs(leftBudg) + " Euros over budget.\n\n";
		}
		else {
			rep += "\n\n\tProject finished with " + leftBudg + " Euros leftover budget.\n\n";
		}
		
		float expRev = this.revPredict;
		try {
			expRev = Float.parseFloat(this.globalParams.get("Rev6Month"));
		}
		catch (NumberFormatException e) {
			expRev = this.revPredict;
			this.globalParams.put("Rev6Month", this.revPredict+"");
		}
		float moneyPerDay = expRev / (30 * 6);
		float revDiff = (dayDiff * moneyPerDay);
		float actRev = (expRev + revDiff);
		
		
		
		rep += "\n\nRevenue Statistics:\n" +
				"\n\tExpected Revenue Six Months After Release: " + expRev + " Euros\n\n" +
				"\tActual Revenue Six Months After Release: " + actRev + " Euros\n\n";
		
		rep += "\n\nGame Score:\n" +
				"\n\tExpected Game Score: " + expRev + " Points\n\n" +
				"\tActual Game Score: " + (actRev + leftBudg)  + " Points\n\n";
		
		
		this.finReport = new FinalReport(rep, this);
//		if (Float.parseFloat(this.globalParams.get("UsrMoney")) <= 0) {
//			JOptionPane.showMessageDialog(this.getWindow(), "You have run out of money. Your developers refuse to work for you anymore. Your project is finished");
//		}
	}
}
