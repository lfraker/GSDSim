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



public class SettingsScreenTest {
	FrontEndPane fPane = new FrontEndPane();
	SettingsPane sPane;
	SettingsScreen toTest;
	
	
	@Test
	public void SettingsScreenTest() {
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		assertTrue(toTest instanceof SettingsScreen);
		assertTrue(toTest.parentPane == sPane);
	}
	
	@Test
	public void onTickTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(50,50));
		int x = toTest.slide.getPos().x;

		toTest.timePerDay += 100;
		

		toTest.onTick(50);
		assertTrue(x < toTest.slide.getPos().x);
		x = toTest.slide.getPos().x;
		toTest.timePerDay -= 70;
		toTest.onTick(50);
		assertTrue(x > toTest.slide.getPos().x);
			
	}
	
	@Test
	public void onResizeTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		assertTrue(toTest.screenSize == null);
		toTest.onResize(new VectorI(50,75));
		assertTrue(toTest.screenSize.x == 50 && toTest.screenSize.y == 75);
	}
	
	@Test
	public void onMouseReleasedTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(150,175));

		VectorI tL = toTest.hard.topLeft;
		Point in = new Point((tL.x + 2), (tL.y + 2));
		Point out = new Point((tL.x - 2), (tL.y - 2));
		assertTrue(toTest.hard.clickedInside(in));
		assertFalse(toTest.hard.clickedInside(out));


	}
	
	@Test
	public void readSettingsTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(150,175));

		assertTrue(toTest.difficulty == null);
		assertTrue(toTest.timePerDay == 0);
		toTest.readSettings();
		assertTrue(toTest.difficulty == Difficulty.EASY || toTest.difficulty == Difficulty.HARD);
		assertTrue(toTest.timePerDay > 0);
	}
	
	@Test
	public void onMouseDraggedTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(150,175));
		
		int x = toTest.slide.getPos().x;

		toTest.dragging = true;
		toTest.onMouseDragged(new MouseEvent(toTest.parentPane, 0, 0, 0, (x + 5), 5, 1, false));
		assertTrue(toTest.slide.getPos().x > x);
	}
	
	@Test
	public void onMousePressedTest() {
		fPane.setupFrame();
		sPane = new SettingsPane(fPane);
		toTest = new SettingsScreen(sPane);
		toTest.onResize(new VectorI(150,175));
		
		int x = toTest.slide.getPos().x;
		int y = toTest.slide.getPos().y;
		assertFalse(toTest.dragging);
		toTest.onMousePressed(new MouseEvent(toTest.parentPane, 0, 0, 0, (x + 1), (y + 1), 1, false));
		assertTrue(toTest.dragging);
	}
	
}