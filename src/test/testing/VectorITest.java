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
import game.backend.Startup;
import game.backend.VectorI;

import java.awt.Point;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.junit.Test;



public class VectorITest {
	VectorI toTest;
	
	@Test
	public void VectorITest() {
		assertTrue(toTest == null);
		toTest = new VectorI(7,5);
		assertTrue(toTest.x == 7);
		assertTrue(toTest.y == 5);

	}
	
}