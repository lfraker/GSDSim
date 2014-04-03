package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.components.InterventionOption;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.paneScreens.Screen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;


public class InterventionOptionTest {
	InterventionOption toTest;

	//Will be extended soon
	@Test
	public void InterventionOptionTest() {
		toTest = new InterventionOption();
		assertTrue(toTest instanceof InterventionOption);
		assertTrue(toTest.isVisible());
		assertTrue(toTest.getComponentCount() > 0);
	}
	
}