package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.components.Site;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.swingFramework.FrontEndPane;


public class SetModulesPaneTest {
	FrontEndPane fPane = new FrontEndPane();
	SetModulesPane toTest;

	
	@Test
	public void SetModulesPaneTest() {
		toTest = new SetModulesPane(fPane);
		assertTrue(toTest instanceof SetModulesPane);
		assertTrue(toTest.parentComp == fPane);
	}

	
	@Test
	public void addSiteToComboTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		Site s = new Site("test", 10, null, 0);
		toTest.addSiteToCombo(s);
		assertTrue(toTest.dropDown.getItemCount() == 2);
		toTest.addSiteToCombo(s);
		assertTrue(toTest.dropDown.getItemCount() == 3);
	}

	@Test
	public void clearSitesTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		Site s = new Site("test", 10, null, 0);
		toTest.addSiteToCombo(s);
		toTest.clearSites();
		assertTrue(toTest.dropDown.getItemCount() == 1);
	}
	
	
	//Will be extended soon
	@Test
	public void setupSwingPaneTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		assertTrue(toTest.getComponents().length == 0);
		toTest.setupSwingPane();
		assertTrue(toTest.isVisible());
		assertTrue(toTest.getComponentCount() > 5);
	}

	@Test
	public void doTickTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}
	
	@Test
	public void mouseDraggedTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}
	
	@Test
	public void mousePressedTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}
	
	@Test
	public void mouseReleasedTest() {
		fPane.setupFrame();
		toTest = new SetModulesPane(fPane);
		toTest.setupSwingPane();
		assertFalse(toTest.viewScreen == null);
	}

}
