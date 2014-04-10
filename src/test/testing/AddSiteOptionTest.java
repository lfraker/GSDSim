package test.testing;

import static org.junit.Assert.*;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.junit.Test;

import game.components.AddSiteOption;
import game.components.FinalReport;
import game.components.InterventionOption;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.paneScreens.Screen;
import game.paneScreens.SettingsScreen;
import game.swingFramework.FrontEndPane;


public class AddSiteOptionTest {
	FrontEndPane fPane = new FrontEndPane();
	AddSiteOption toTest;

	@Test
	public void AddSiteOptionTest() {
		fPane.setupFrame();
		assertTrue(toTest == null);
		toTest = new AddSiteOption(fPane, "test");
		assertTrue(toTest.getComponentCount() > 0);
	}
		
	
	@Test
	public void getChecks() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.getChecks();
		for (boolean b : toTest.getChecks()) {
			assertFalse(b);
		}
	}
	
	@Test
	public void noButtonTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.noButton();
		assertFalse(toTest.isVisible());
	}
	
	@Test
	public void okButtonTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.okButton();
		assertFalse(toTest.isVisible());
		assertTrue(toTest.cancelled);

	}
	
	@Test
	public void getNumberEmployeesTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.numEmployees = 5;
		assertTrue(toTest.getNumberEmployees() == 5);

	}
	
	@Test
	public void getSiteNameTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.siteName = "test1";
		assertTrue(toTest.getSiteName().equals("test1"));

	}
	
	@Test
	public void getCancelledTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.cancelled = true;
		assertTrue(toTest.getCancelled());
		
	}
	
	@Test
	public void getCostDevTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.costD = 105;
		assertTrue(toTest.getCostDev() == 105);
	}
	
	@Test
	public void getEffortDevTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.effD = 1.0f;
		assertTrue(toTest.getEffortDev() == 1.0);
	}
	
	@Test
	public void getTimeZoneTest() {
		fPane.setupFrame();
		toTest = new AddSiteOption(fPane, "test");
		toTest.timeZoneDiff = -2;
		assertTrue(toTest.getTimeZone() == -2);
	}
}