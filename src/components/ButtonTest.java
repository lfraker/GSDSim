package components;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import backend.VectorI;

public class ButtonTest {
	Button toTest = new Button(new VectorI(0,0), new VectorI(10,10), "Test");
	
	@Test
	public void testSetPressed() {
		this.toTest.setPressed();
		assertTrue(this.toTest.pressed);
	}

	@Test
	public void testRelease() {
		this.toTest.release();
		assertTrue(!this.toTest.pressed);
	}

	@Test
	public void testClickedInside() {
		assertTrue(true==(this.toTest.clickedInside(new Point(1,1))));
		assertTrue(false==(this.toTest.clickedInside(new Point(11,9))));

	}

	@Test
	public void testOnResize() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		assertTrue(true==(this.toTest.clickedInside(new Point(11,11))));
		assertTrue(false==(this.toTest.clickedInside(new Point(0,0))));

	}

}
