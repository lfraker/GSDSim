package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import game.components.Module;
import game.components.Site;
import game.components.ProcessSimulator;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class ProcessSimulatorTest {
	ProcessSimulator toTest = new ProcessSimulator();

	@Test
	public void testUpdateTime() {
		toTest.UpdateTime(20);
		assertTrue(toTest.currentTime == 20);
	}

	@Test
	// this test simulates a module falling behind
	public void testProcessSites() {
		List<Site> sites = new ArrayList<Site>();
		MapMarkerDot marker = new MapMarkerDot(0, 0);
		Site s = new Site("test", 10, marker, 0);
		Module m = new Module(1000, "test", null);
		m.origEstimate = 0;
		s.addModule(m);
		sites.add(s);
		toTest.setSiteList(sites);

		toTest.ProcessSites();
		assertTrue(marker.status == MapMarkerDot.Status.BEHIND);
	}

}
