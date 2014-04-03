package test.testing;

import static org.junit.Assert.*;


import game.components.Button;
import game.components.Difficulty;
import game.components.GameState;
import game.components.Site;
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
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.Timer;

import org.junit.Test;



public class SetModulesScreenTest {
	FrontEndPane fPane = new FrontEndPane();
	SetModulesPane sMPane;
	SetModulesScreen toTest;
	
	
	@Test
	public void setModulesScreenTest() {
		sMPane = new SetModulesPane(fPane);
		toTest = new SetModulesScreen(sMPane);
		assertTrue(toTest instanceof SetModulesScreen);
		assertTrue(toTest.parentPane == sMPane);
	}
	
}