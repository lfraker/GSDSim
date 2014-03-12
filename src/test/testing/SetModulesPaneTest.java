package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.components.Site;
import game.gamePanes.SetModulesPane;
import game.swingFramework.FrontEndPane;


public class SetModulesPaneTest {
	SetModulesPane toTest = new SetModulesPane(new FrontEndPane());

	@Test
	public void testAddSiteToCombo() {
		Site s = new Site("test", 10, null, 0);
		toTest.addSiteToCombo(s);
		assertTrue(toTest.dropDown.getItemCount() == 1);
		toTest.addSiteToCombo(s);
		assertTrue(toTest.dropDown.getItemCount() == 2);
	}

	@Test
	public void testClearSites() {
		Site s = new Site("test", 10, null, 0);
		toTest.addSiteToCombo(s);
		toTest.clearSites();
		assertTrue(toTest.dropDown.getItemCount() == 0);
	}

	@Test
	public void testCallOnResize() {
		try {
			toTest.callOnResize(10, 20);
		} catch (Exception e) {
		}
		assertTrue(toTest.windSize.x == 10);
		assertTrue(toTest.windSize.y == 20);
	}

}
