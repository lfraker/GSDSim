package test.testing;

import static org.junit.Assert.*;


import java.awt.Point;

import org.junit.Test;

import game.components.Module;
import game.components.ModuleWrapper;
import game.components.Slider;

import game.backend.VectorI;

public class ModuleWrapperTest {
	ModuleWrapper toTest;
	
	@Test
	public void ModuleWrapperTest() {
		toTest = new ModuleWrapper(new Module(50, "test"), "test");
		assertTrue(toTest.getNumber() == 0);
		toTest = new ModuleWrapper(new Module(50, "test"), "test", 5);
		assertTrue(toTest.getNumber() == 5);
	}

	@Test
	public void decrementNumTest() {
		toTest = new ModuleWrapper(new Module(50, "test"), "test", 9);
		assertTrue(toTest.getNumber() == 9);
		toTest.decrementNum();
		assertTrue(toTest.getNumber() == 8);

	}
	
	@Test
	public void getNumberTest() {
		toTest = new ModuleWrapper(new Module(50, "test"), "test", 7);
		assertTrue(toTest.getNumber() == 7);
	}
}