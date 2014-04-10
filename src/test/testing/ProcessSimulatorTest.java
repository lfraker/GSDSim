package test.testing;
//
import static org.junit.Assert.*;
//
import org.junit.Test;
//
import java.util.ArrayList;
import java.util.List;
//
import game.components.Module;
import game.components.Site;
import game.components.ProcessSimulator;
//
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;
//
public class ProcessSimulatorTest 
{
	ProcessSimulator toTest = new ProcessSimulator();

	@Test
	public void testUpdateTime() 
	{
		toTest.UpdateTime(20);
		assertTrue(toTest.currentTime == 20);
	}

	@Test
	public void testSetDayLength() 
	{
		toTest.SetDayLength(0);
		assertTrue(toTest.dayLength != 0);
		toTest.SetDayLength(12);
		assertTrue(toTest.dayLength == 12);
	}

	@Test
	// this test simulates a module falling behind
	public void testProcessSites() {
		List<Site> sites = new ArrayList<Site>();
		MapMarkerDot marker = new MapMarkerDot(0, 0);
		Site s = new Site("test", 10, marker, 0);
		Module m = new Module(1000, "test");
		m.origEstimate = 0;
		s.addModule(m);
		s.setTimezone(10);
		sites.add(s);
		toTest.setSiteList(sites);

		toTest.ProcessSites();
		assertTrue(marker.status == MapMarkerDot.Status.BEHIND);
	}

	@Test
	public void testAddGetRemoveSites() 
	{
		List<Site> sites = new ArrayList<Site>();
		MapMarkerDot marker = new MapMarkerDot(0, 0);
		Site s = new Site("test", 10, marker, 0);
		
		toTest.AddSite(s);

		assertTrue(toTest.GetSites().size() > 0);

		toTest.RemoveSites();

		assertTrue(toTest.GetSites().size() == 0);
	}


	@Test
	public void testNominalSchedCalc() 
	{
		List<Site> sites = new ArrayList<Site>();
		MapMarkerDot marker = new MapMarkerDot(0, 0);
		Site s = new Site("test", 10, marker, 0);
		Module m = new Module(1000, "test");
		s.addModule(m);
		s.setTimezone(10);
		sites.add(s);
		toTest.setSiteList(sites);
		assertTrue(toTest.NominalScheduleCalc() > 0);
	}


}
