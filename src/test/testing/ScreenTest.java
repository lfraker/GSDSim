package test.testing;

import static org.junit.Assert.*;


import game.components.Button;
import game.components.Difficulty;
import game.components.GameState;
import game.components.Site;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.paneScreens.Screen;
import game.paneScreens.SetModulesScreen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;
import game.backend.VectorI;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.junit.Test;



public class ScreenTest {
	FrontEndPane fPane = new FrontEndPane();
	SettingsPane sPane;
	Screen toTest;
	
	
	@Test
	public void ScreenTest() {
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		assertTrue(toTest instanceof Screen);
		assertTrue(toTest.parentPane == sPane);
	}
	
	@Test
	public void getTimeTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		assertTrue(toTest.getTime() == 0);
		
		
	}
	
	@Test
	public void getDaysTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		assertTrue(toTest.getDays() == 0);
		fPane.dayCount ++;
		assertTrue(toTest.getDays() == 1);

	}
	
	@Test
	public void enableSettingsTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		toTest.enableSettings();	
		int tempInd = fPane.frames.indexOfTab("Settings");
		assertTrue(fPane.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void disableSettingsTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		toTest.disableSettings();
		int tempInd = fPane.frames.indexOfTab("Settings");
		assertFalse(fPane.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void enableModulesTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		toTest.enableModules();	
		int tempInd = fPane.frames.indexOfTab("Modules");
		assertTrue(fPane.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void disableModulesTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		toTest.disableModules();
		int tempInd = fPane.frames.indexOfTab("Modules");
		assertFalse(fPane.frames.isEnabledAt(tempInd));

	}
	
	@Test
	public void enableSitesTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		toTest.enableSites();	
		int tempInd = fPane.frames.indexOfTab("Sites");
		assertTrue(fPane.frames.isEnabledAt(tempInd));
	}
	
	@Test
	public void disableSitesTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		
		toTest.disableSites();
		int tempInd = fPane.frames.indexOfTab("Sites");
		assertFalse(fPane.frames.isEnabledAt(tempInd));

	}

	@Test
	public void onTickTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onDrawTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onResizeTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onMouseReleasedTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onKeyTypedTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onKeyReleasedTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onKeyPressedTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onMouseDraggedTest() {
		//Method not implemented in Screen.java
		
	}
	
	@Test
	public void onMousePressedTest() {
		//Method not implemented in Screen.java
		
	}

	
}