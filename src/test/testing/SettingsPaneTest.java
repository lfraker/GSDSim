package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import game.gamePanes.SetModulesPane;
import game.swingFramework.FrontEndPane;


public class SettingsPaneTest {
	SetModulesPane toTest = new SetModulesPane(new FrontEndPane());

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
