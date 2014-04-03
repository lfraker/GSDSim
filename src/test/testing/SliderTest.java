package test.testing;

import static org.junit.Assert.*;


import java.awt.Point;

import org.junit.Test;

import game.components.Slider;

import game.backend.VectorI;

public class SliderTest {
	Slider toTest = new Slider(new VectorI(0,0), new VectorI(5,5));
	Slider s;

	@Test
	public void SliderTest() {
		assertTrue(s == null);
		s = new Slider(new VectorI(-1,2), new VectorI(5,5));
		assertTrue(s.getPos().x == -1);
		assertTrue(s.getPos().y == 2);
	}
	

	@Test
	public void clickedInsideTest() {
		assertTrue(true==(this.toTest.clickedInside(new Point(1,1))));
		assertTrue(false==(this.toTest.clickedInside(new Point(10,4))));

	}

	@Test
	public void onResizeTest() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		assertTrue(true==(this.toTest.clickedInside(new Point(11,11))));
		assertTrue(false==(this.toTest.clickedInside(new Point(20,0))));

	}

	@Test
	public void updateXTest() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		this.toTest.updateX(5);
		assertTrue(true==(this.toTest.clickedInside(new Point(15,5))));
		assertTrue(false==(this.toTest.clickedInside(new Point(1,5))));

	}
	
	@Test
	public void getPosTest() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		this.toTest.updateX(5);
		assertTrue(this.toTest.getPos().x==5);
		assertTrue(this.toTest.getPos().y==1);

	}

}
