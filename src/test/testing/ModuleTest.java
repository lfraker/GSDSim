package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import game.components.Module;
import game.components.Site;
import game.components.ProcessSimulator;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class ModuleTest {
	List<Site> sites = new ArrayList<Site>();
	{
		MapMarkerDot marker = new MapMarkerDot(0, 0);
		Site s = new Site("test", 10, marker, 0);
		sites.add(s);
	}
	Module toTest = new Module(1000, "test", sites);

	@Test
	public void testGetName() {
		assertTrue("test".equals(toTest.getName()));
	}

	@Test
	public void testAddSub() {
		float result = toTest.addSub(0, 100f); 
		assertTrue(result >= 100 && result <= 125f);
		result = toTest.addSub(1, 100f); 
		assertTrue(result <= 100f && result >= 75f);
	}

	@Test
	public void testComplete() {
		toTest.complete();
		assertTrue(toTest.isComplete());
	}

	@Test
	public void testWorkRemaining() {
		float remain = toTest.workRemaining();
		float done = toTest.workDone();
		float required = toTest.totalWorkRequired();
		assertTrue(done + remain <= required + 0.1 /*fudge*/);
	}

	@Test
	public void testWorkers() {
		toTest.setNumberWorkers(10);
		assertTrue(toTest.getNumberWorkers() == 10);
	}

	@Test
	public void testWork() {
		 TODO: more than just agile
		toTest.doWork();
		assertTrue(toTest.workDone() > 0f);
	}

}

