package test.testing;

import static org.junit.Assert.*;


import game.components.Button;
import game.components.Difficulty;
import game.components.GameState;
import game.components.Site;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.swingFramework.FrontEndPane;
import game.backend.VectorI;

import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.junit.Test;



public class FrontEndPaneTest {
	FrontEndPane toTest = new FrontEndPane();
	
	@Test
	public void setupFrameTest() {
		toTest.setupFrame();
		assertTrue(toTest.getWindow() instanceof JFrame);
		assertTrue(toTest.siteStatus instanceof JMapViewer);
		assertTrue(toTest.modules instanceof SetModulesPane);
		assertTrue(toTest.settings instanceof SettingsPane);
		assertTrue(toTest.frames instanceof JTabbedPane);
	}
	
	@Test
	public void getFrameTest() {
		toTest.setupFrame();
		assertTrue(toTest.getWindow() instanceof JFrame);
	}
	
	@Test
	public void enableSettingsTest() {
		toTest.setupFrame();
		toTest.disableSettings();
		int tempInd = toTest.frames.indexOfTab("Settings");
		assertFalse(toTest.frames.isEnabledAt(tempInd));
		toTest.enableSettings();
		assertTrue(toTest.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void disableSettingsTest() {
		toTest.setupFrame();
		toTest.enableSettings();
		int tempInd = toTest.frames.indexOfTab("Settings");
		assertTrue(toTest.frames.isEnabledAt(tempInd));
		toTest.disableSettings();
		assertFalse(toTest.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void enableModulesTest() {
		toTest.setupFrame();
		toTest.disableModules();
		int tempInd = toTest.frames.indexOfTab("Modules");
		assertFalse(toTest.frames.isEnabledAt(tempInd));
		toTest.enableModules();
		assertTrue(toTest.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void disableModulesTest() {
		toTest.setupFrame();
		toTest.enableModules();
		int tempInd = toTest.frames.indexOfTab("Modules");
		assertTrue(toTest.frames.isEnabledAt(tempInd));
		toTest.disableModules();
		assertFalse(toTest.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void enableSitesTest() {
		toTest.setupFrame();
		toTest.disableSites();
		int tempInd = toTest.frames.indexOfTab("Sites");
		assertFalse(toTest.frames.isEnabledAt(tempInd));
		toTest.enableSites();
		assertTrue(toTest.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void disableSitesTest() {
		toTest.setupFrame();
		toTest.enableSites();
		int tempInd = toTest.frames.indexOfTab("Sites");
		assertTrue(toTest.frames.isEnabledAt(tempInd));
		toTest.disableSites();
		assertFalse(toTest.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void getTimeTest() {
		toTest.setupFrame();
		assertTrue(toTest.getTime() == 0);
	}
	
	@Test
	public void getDayLengthTest() {
		toTest.setupFrame();
		assertTrue((toTest.getDayLength()/100) > 50000000 && (toTest.getDayLength()/1000) < 300000000);
	}
	
	@Test
	public void getDaysTest() {
		toTest.setupFrame();
		assertTrue(toTest.getDays() == 0);
		toTest.dayCount++;
		assertTrue(toTest.getDays() == 1);
	}
	
	@Test
	public void doCloseTest() {
		toTest.setupFrame();
		toTest.doClose();
		assertTrue(toTest.getWindow() == null);
	}
	
	@Test
	public void doStartTest() {
		toTest.setupFrame();
		toTest.doStart();
		assertTrue(toTest.timer.isRunning());
	}
	
	@Test
	public void getDifficultyTest() {
		toTest.setupFrame();
		toTest.difficulty = Difficulty.MEDIUM;
		assertTrue(toTest.getDifficulty() == Difficulty.MEDIUM);
	}
	
	@Test
	public void doTickTest() {
		toTest.setupFrame();
		toTest.settings.callOnResize(100, 100);
		toTest.timeStart = true;
		toTest.doStart();
		toTest.doTick();		
		assertTrue(toTest.getTime() > 0);
	}
	
	@Test
	public void setTimePerDayTest() {
		toTest.setupFrame();
		toTest.setTimePerDay(50);
		assertTrue(toTest.getDayLength() == 50);
	}
	
	@Test
	public void setCanPauseTest() {
		toTest.setupFrame();
		toTest.setCanPause();
		assertTrue(toTest.canPause());
	}
	
	@Test
	public void setCantPauseTest() {
		toTest.setupFrame();
		toTest.setCantPause();
		assertFalse(toTest.canPause());
	}
	
	@Test
	public void pauseUnpauseTest() {
		toTest.setupFrame();
		toTest.paused = false;
		toTest.setCanPause();
		toTest.pauseUnpause();
		assertTrue(toTest.isPaused());
		toTest.pauseUnpause();
		assertFalse(toTest.isPaused());
		
	}
	
	@Test
	public void isPausedTest() {
		toTest.setupFrame();
		toTest.paused = false;
		toTest.setCanPause();
		assertFalse(toTest.isPaused());
		toTest.pauseUnpause();
		assertTrue(toTest.isPaused());
	}
	
	@Test
	public void updateGlobalParamTest() {
		toTest.setupFrame();
		toTest.updateGlobalParam("test1", "val1");
		assertTrue(toTest.globalParams.containsKey("test1"));
		assertTrue(toTest.globalParams.containsValue("val1"));
	}
	
	@Test
	public void getGlobalParamTest() {
		toTest.setupFrame();
		toTest.updateGlobalParam("test2", "val2");
		assertTrue(toTest.getGlobalParam("test2").equals("val2"));
	}
	
	@Test
	public void setDifficultyTest() {
		toTest.setupFrame();
		toTest.difficulty = Difficulty.EASY;
		assertTrue(toTest.getDifficulty() == Difficulty.EASY);
		toTest.setDifficulty(Difficulty.HARD);
		assertTrue(toTest.getDifficulty() == Difficulty.HARD);
	}
	
	@Test
	public void getWindSizeTest() {
		toTest.setupFrame();
		toTest.setWindSize(50, 50);
		assertTrue(toTest.getWindSize() instanceof VectorI);
		assertTrue(toTest.getWindSize().x > 0 && toTest.getWindSize().y > 0);
	}
	
	@Test
	public void setWindSizeTest() {
		toTest.setupFrame();
		toTest.setWindSize(50, 50);
		assertTrue(toTest.getWindSize() instanceof VectorI);
		assertTrue(toTest.getWindSize().x == 50 && toTest.getWindSize().y == 50);
	}
	
	@Test
	public void getWindowTest() {
		toTest.setupFrame();
		assertTrue(toTest.getWindow() instanceof JFrame);
	}
	
	@Test
	public void setWindowTest() {
		toTest.setupFrame();
		JFrame winNew = new JFrame();
		toTest.setWindow(winNew);
		assertTrue(toTest.getWindow() == winNew);
	}
	
	@Test
	public void getZoomTest() {
		toTest.setupFrame();
		assertTrue(toTest.getZoom() > 0);
	}
	
	@Test
	public void getCenterTest() {
		toTest.setupFrame();
		assertTrue(toTest.getCenter() instanceof Point);
		assertTrue(toTest.getCenter().x != 0);
	}
	
	@Test
	public void getMapWidthTest() {
		toTest.setupFrame();
		assertTrue(toTest.getMapWidth() > 0);
	}
	
	@Test
	public void getMapHeightTest() {
		toTest.setupFrame();
		assertTrue(toTest.getMapHeight() > 0);
	}
	
	@Test
	public void getMapViewerTest() {
		toTest.setupFrame();
		assertTrue(toTest.getMapViewer() instanceof JMapViewer);
	}

	@Test
	public void getPauseButtonTest() {
		toTest.setupFrame();
		assertTrue(toTest.getPauseButton() instanceof Button);
	}
	
	@Test
	public void canPauseTest() {
		toTest.setupFrame();
		this.setCanPauseTest();
		assertTrue(toTest.canPause());
		this.setCantPauseTest();
		assertFalse(toTest.canPause());
	}
	
	@Test
	public void startLoadedSimTest() {
		toTest.setupFrame();
		assertFalse(toTest.timeStart);	
		int tempInd = toTest.frames.indexOfTab("Settings");
		assertTrue(toTest.frames.isEnabledAt(tempInd));
		toTest.startLoadedSim();
		assertTrue(toTest.timeStart);
		assertFalse(toTest.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void loadedSimTest() {
		toTest.setupFrame();
		toTest.loadedSim();
		assertTrue(toTest.isSimLoaded());
	}
	
	@Test
	public void isSimLoadedTest() {
		toTest.setupFrame();
		assertFalse(toTest.isSimLoaded());
		toTest.loadedSim();
		assertTrue(toTest.isSimLoaded());
	}
	
	@Test
	public void pickSitesTest() {
		toTest.setupFrame();
		int tempInd = toTest.frames.indexOfTab("Settings");
		int tempInd2 = toTest.frames.indexOfTab("Sites");
		int tempInd3 = toTest.frames.indexOfTab("Modules");
		assertTrue(toTest.frames.isEnabledAt(tempInd));
		assertFalse(toTest.frames.isEnabledAt(tempInd2));
		assertFalse(toTest.frames.isEnabledAt(tempInd3));
		assertFalse(toTest.canStartSim());
		toTest.pickSites();
		assertFalse(toTest.frames.isEnabledAt(tempInd));
		assertTrue(toTest.frames.isEnabledAt(tempInd2));
		assertTrue(toTest.frames.isEnabledAt(tempInd3));
		assertTrue(toTest.canStartSim());
		toTest.pickSites();
		
	}

	@Test
	public void canStartSimTest() {
		toTest.setupFrame();
		assertFalse(toTest.canStartSim());
		toTest.pickSites();
		assertTrue(toTest.canStartSim());
	}
	
	@Test
	public void endGameTest() {
		toTest.setupFrame();
		toTest.endGame();
		assertTrue(toTest.getWindow().isVisible());
	}
	
	
	@Test
	public void startCustomSimTest() {
		System.out.println("TEST: startCustomSimTest is not yet implemented in FrontEndPaneTest.java");
	}
	
	
	@Test
	public void saveCustomGameScenTest() {
		System.out.println("TEST: saveCustomGameScenTest is not yet implemented in FrontEndPaneTest.java");
	}
}