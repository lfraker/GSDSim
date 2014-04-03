package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.paneScreens.Screen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;


public class SettingsPaneTest {
	FrontEndPane fPane = new FrontEndPane();
	SettingsPane toTest;

	
	@Test
	public void SettingsPaneTest() {
		toTest = new SettingsPane(fPane);
		assertTrue(toTest instanceof SettingsPane);
		assertTrue(toTest.parentComp == fPane);
	}
	
	
	//Will be extended soon
	@Test
	public void setupSwingPaneTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		assertTrue(toTest.getComponents().length == 0);
		toTest.setupSwingPane();
		assertTrue(toTest.isVisible());
		assertTrue(toTest.getComponentCount() > 5);
	}
	
	@Test
	public void doTickTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}
	
	@Test
	public void mouseDraggedTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}
	
	@Test
	public void mousePressedTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}
	
	@Test
	public void mouseReleasedTest() {
		fPane.setupFrame();
		toTest = new SettingsPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}

}
