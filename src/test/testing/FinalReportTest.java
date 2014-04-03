package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.components.FinalReport;
import game.components.InterventionOption;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.paneScreens.Screen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;


public class FinalReportTest {
	FinalReport toTest;

	//Will be extended soon
	@Test
	public void FinalReportTest() {
		toTest = new FinalReport("test");
		assertTrue(toTest instanceof FinalReport);
		assertTrue(toTest.isVisible());
		assertTrue(toTest.getComponentCount() > 0);
	}
	
}