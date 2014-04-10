package test.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import game.components.Module;
import game.components.Site;
import game.components.ProcessSimulator;
import game.components.DevelopmentMethod;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class ModuleTest {

	Module toTest = new Module(1000, "test");

	
	@Test
	public void testGetName() 
	{
		assertTrue("test".equals(toTest.getName()));
	}

	@Test
	public void testAddSub() 
	{
		float result = toTest.addSub(0, 100f); 
		assertTrue(result >= 100 && result <= 125f);
		result = toTest.addSub(1, 100f); 
		assertTrue(result <= 100f && result >= 75f);
	}
	/*
	@Test
	public void testWorkRemaining() 
	{
		float remain = toTest.workRemaining();
		float done = toTest.workDone();
		float required = toTest.totalWorkRequired();
		assertTrue(done + remain <= required + 0.1);
	}

	@Test
	public void testWorkers() 
	{
		toTest.setNumberWorkers(10);
		assertTrue(toTest.getNumberWorkers() == 10);
	}

	@Test
	public void testGetEstimate()
	{
		assertTrue(toTest.getEstimate() == 1000);
	}

	@Test
	public void testWork() 
	{
		//TODO: more than just agile
		toTest.doWork();
		assertTrue(toTest.workDone() > 0f);
	}

	@Test void testDevelopmentMethod()
	{
		toTest.setDevelopmentMethod(DevelopmentMethod.WATERFALL);
		assertTrue(toTest.getDevelopmentMethod() == DevelopmentMethod.WATERFALL);
	}

	
	@Test void testPerformanceModifier()
	{
		toTest.setPerformanceModifier((float)1.2);
		assertTrue(toTest.performanceLevel == 1.2);
	}*/




	/*
	* 	Functions for testing

	public float ActualWorkDone()

	public float getCompletionLevel()

	public int sectionsCompleted()

	public boolean IsOnSchedule()

	public void RestartFromStage(int stage)

	*/
	/*
	@Test
	public void testComplete() 
	{
		toTest.complete();
		assertTrue(toTest.isComplete());
	}*/



}

