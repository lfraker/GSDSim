package test.testing;

import static org.junit.Assert.*;


import game.components.Button;
import game.backend.VectorI;

import java.awt.Point;

import org.junit.Test;



public class ButtonTest {
	Button toTest = new Button(new VectorI(0,0), new VectorI(10,10), "Test");
	Button b;
	
	@Test
	public void ButtonTest() {
		assertTrue(b == null);
		b = new Button(new VectorI(-5,9), new VectorI(10,10), "Test");
		assertTrue(b.topLeft.x == -5);
		assertTrue(b.topLeft.y == 9);
	}
	
	@Test
	public void setPressedTest() {
		this.toTest.setPressed();
		assertTrue(this.toTest.isPressed());
	}

	@Test
	public void releaseTest() {
		this.toTest.release();
		assertTrue(!this.toTest.isPressed());
	}

	@Test
	public void clickedInsideTest() {
		assertTrue(true==(this.toTest.clickedInside(new Point(1,1))));
		assertTrue(false==(this.toTest.clickedInside(new Point(11,9))));

	}

	@Test
	public void onResizeTest() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		assertTrue(true==(this.toTest.clickedInside(new Point(11,11))));
		assertTrue(false==(this.toTest.clickedInside(new Point(0,0))));

	}
	
	@Test
	public void isPressed() {
		toTest.setPressed();
		assertTrue(toTest.isPressed());
		toTest.release();
		assertFalse(toTest.isPressed());
	}

}
