package components;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Test;

import backend.VectorI;

public class SliderTest {
	Slider toTest = new Slider(new VectorI(0,0), new VectorI(5,5));;
	


	@Test
	public void testClickedInside() {
		assertTrue(true==(this.toTest.clickedInside(new Point(1,1))));
		assertTrue(false==(this.toTest.clickedInside(new Point(7,4))));

	}

	@Test
	public void testOnResize() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		assertTrue(true==(this.toTest.clickedInside(new Point(11,11))));
		assertTrue(false==(this.toTest.clickedInside(new Point(0,0))));

	}

	@Test
	public void testUpdateX() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		this.toTest.updateX(5);
		assertTrue(true==(this.toTest.clickedInside(new Point(15,5))));
		assertTrue(false==(this.toTest.clickedInside(new Point(4,5))));

	}
	
	@Test
	public void testGetPos() {
		this.toTest.onResize(new VectorI(1,1), new VectorI(10,10));
		this.toTest.updateX(5);
		assertTrue(this.toTest.getPos().x==5);
		assertTrue(this.toTest.getPos().y==1);

	}

}
