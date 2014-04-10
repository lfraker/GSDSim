package test.testing;

import static org.junit.Assert.*;


import game.components.Button;
import game.components.Difficulty;
import game.components.GameState;
import game.components.Site;
import game.gamePanes.Pane;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.paneScreens.SetModulesScreen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;
import game.backend.VectorI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.junit.Test;



public class PaneTest {
	FrontEndPane fPane = new FrontEndPane();
	Pane toTest;
	
	@Test
	public void PaneTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		assertFalse(toTest.frameWork == null);
		assertTrue(toTest.parentComp == fPane);
	}
	
	@Test
	public void setCanPauseTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.setCantPause();
		assertFalse(fPane.canPause());
		toTest.setCanPause();
		assertTrue(fPane.canPause());
	}
	
	@Test
	public void setCantPauseTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.setCanPause();
		assertTrue(fPane.canPause());
		toTest.setCantPause();
		assertFalse(fPane.canPause());
	}
	
	@Test
	public void pauseUnpauseTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		fPane.paused = false;
		assertFalse(fPane.isPaused());
		fPane.setCanPause();
		toTest.pauseUnpause();
		assertTrue(fPane.isPaused());
	}
	
	@Test
	public void isPausedTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		fPane.paused = false;
		assertFalse(toTest.isPaused());
		fPane.setCanPause();
		toTest.pauseUnpause();
		assertTrue(toTest.isPaused());
	}
	
	@Test
	public void enableSettingsTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		
		toTest.enableSettings();	
		int tempInd = fPane.frames.indexOfTab("Settings");
		assertTrue(fPane.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void disableSettingsTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);

		
		toTest.disableSettings();
		int tempInd = fPane.frames.indexOfTab("Settings");
		assertFalse(fPane.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void enableModulesTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);

		
		toTest.enableModules();	
		int tempInd = fPane.frames.indexOfTab("Modules");
		assertTrue(fPane.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void disableModulesTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);

		
		toTest.disableModules();
		int tempInd = fPane.frames.indexOfTab("Modules");
		assertFalse(fPane.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void enableSitesTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);

		
		toTest.enableSites();	
		int tempInd = fPane.frames.indexOfTab("Sites");
		assertTrue(fPane.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void disableSitesTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);

		
		toTest.disableSites();
		int tempInd = fPane.frames.indexOfTab("Sites");
		assertFalse(fPane.frames.isEnabledAt(tempInd));

	}

	@Test
	public void setTimePerDayTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		fPane.dayTime = 50;
		assertTrue(fPane.getDayLength() == 50);
		toTest.setTimePerDay(70);
		assertTrue(fPane.getDayLength() == 70);

	}
	
	@Test
	public void setDifficultyTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		FrontEndPane.difficulty = Difficulty.HARD;
		assertTrue(fPane.getDifficulty() == Difficulty.HARD);
		toTest.setDifficulty(Difficulty.EASY);
		assertTrue(fPane.getDifficulty() == Difficulty.EASY);
	}
	
	
	@Test
	public void updateGlobalParamTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.updateGlobalParam("test1", "val1");
		assertTrue(fPane.globalParams.containsKey("test1"));
		assertTrue(fPane.globalParams.containsValue("val1"));
	}
	
	@Test
	public void getGlobalParamTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.updateGlobalParam("test2", "val2");
		assertTrue(toTest.getGlobalParam("test2").equals("val2"));
	}
	
	@Test
	public void getTimeTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		assertTrue(toTest.getTime() == 0);
	}
	
	@Test
	public void getTotalTimeTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		fPane.dayTime = 50;
		assertTrue(toTest.getTotalTime() == 50);
	}
	
	@Test
	public void getDayTimerTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		String test = toTest.getDayTimer();
		assertTrue(test.contains(":"));
		assertTrue(test.contains("0"));
		assertTrue(test.length() > 3);
	}
	
	@Test
	public void getDaysTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		assertTrue(toTest.getDays() == 0);
		fPane.dayCount ++;
		assertTrue(toTest.getDays() == 1);
	}
	
	@Test
	public void callOnResizeTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.viewScreen = new SettingsScreen(toTest);
		toTest.callOnResize(50, 50);
		VectorI first = toTest.windSize;
		toTest.callOnResize(75, 25);
		VectorI second = toTest.windSize;
		assertTrue(first.y > second.y);
		assertTrue(second.x > first.x);
	}
	
	
	@Test
	public void mouseClickedTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.viewScreen = new SettingsScreen(toTest);
		toTest.callOnResize(150, 150);
		fPane.paused = false;
		VectorI tL = toTest.pause.topLeft;
		int xIn = (tL.x + 1);
		int yIn = (tL.x + 1);
		int xOut = (tL.x - 1);
		int yOut = (tL.x - 1);
		toTest.mouseClicked(new MouseEvent(toTest, 0, 0, 0, xIn, yIn, 1, false));
		assertTrue(fPane.isPaused());
		fPane.paused = false;
		toTest.mouseClicked(new MouseEvent(toTest, 0, 0, 0, xOut, yOut, 1, false));
		assertFalse(fPane.isPaused());

	}
	
	@Test
	public void mouseEnteredTest() {
		//Method not implemented in Pane.java
		
	}

	@Test
	public void mouseExitedTest() {
		//Method not implemented in Pane.java

	}	
	
	@Test
	public void mouseMovedTest() {
		//Method not implemented in Pane.java

	}
	
	@Test
	public void componentMovedTest() {
		//Method not implemented in Pane.java

	}

	@Test
	public void componentShownTest() {
		//Method not implemented in Pane.java

	}

	@Test
	public void componentHiddenTest() {
		//Method not implemented in Pane.java
		
	}

	@Test
	public void keyTyped() {
		//Method not implemented in Pane.java

	}

	@Test
	public void keyPressedTest() {
		//Method not implemented in Pane.java

	}
	
	@Test
	public void mouseWheelMoved() {
		//Method not implemented in Pane.java
		
	}


	
	
	@Test
	public void keyReleasedTest() {
		//Method not implemented in Pane.java
		
	}
	
}
