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



public class StartupTest {
	Startup toTest = new Startup();
	
	@Test
	public void doStartupTest() {
		assertTrue(toTest.game == null);
		toTest.doStartup();
		assertTrue(toTest.game instanceof FrontEndPane);
	}
	
}