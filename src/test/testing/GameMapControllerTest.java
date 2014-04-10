package test.testing;

import static org.junit.Assert.*;


import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

import game.backend.VectorI;
import game.components.AddSiteOption;
import game.components.Button;
import game.components.FinalReport;
import game.components.GameMapController;
import game.components.InterventionOption;
import game.components.Site;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import game.paneScreens.Screen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;


public class GameMapControllerTest {
	FrontEndPane fPane = new FrontEndPane();
	GameMapController toTest;
	
	
	@Test
	public void mouseClickedTest() {
		fPane.setupFrame();
		JMapViewer jMap = fPane.siteStatus;
		Button pause = new Button(new VectorI(0,0), new VectorI(5,5), "Pause");
		Button interv = new Button(new VectorI(0,0), new VectorI(0,0), "Intervention");
		Button startSim = new Button(new VectorI(0,0), new VectorI(0,0), "Start Sim");
		Button saveSim = new Button(new VectorI(0,0), new VectorI(0,0), "Save Sim");

		toTest = new GameMapController(jMap, fPane, pause, interv, startSim, saveSim);
		
		assertFalse(fPane.isPaused());
		toTest.mouseClicked(new MouseEvent(jMap, 0, 0, 0, 1, 1, 1, false, MouseEvent.BUTTON1));
		assertTrue(fPane.isPaused());
	}
	
	@Test
	public void setUpInquiryTest() {
		fPane.setupFrame();
		JMapViewer jMap = fPane.siteStatus;
		Button pause = new Button(new VectorI(0,0), new VectorI(5,5), "Pause");
		Button interv = new Button(new VectorI(0,0), new VectorI(0,0), "Intervention");
		Button startSim = new Button(new VectorI(0,0), new VectorI(0,0), "Start Sim");
		Button saveSim = new Button(new VectorI(0,0), new VectorI(0,0), "Save Sim");

		toTest = new GameMapController(jMap, fPane, pause, interv, startSim, saveSim);
		assertTrue(toTest.currSiteQuer == null);
		assertTrue(toTest.inquirPane == null);
		Site sN = new Site("test site", 2, new MapMarkerDot(5, 5), 0, 0, 0, false);
		toTest.setUpInquiry(sN);
		assertFalse(toTest.currSiteQuer == null);
		assertFalse(toTest.inquirPane == null);

	}
	
	
}