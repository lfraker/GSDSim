package game.components;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import game.backend.VectorI;

public class Slider {
	VectorI topLeft;
	VectorI dimens;
	boolean pressed = false;
	Font fontText = new Font("serif", Font.BOLD, 25);

	
	public Slider (VectorI tL, VectorI dim) {
		this.topLeft = tL;
		this.dimens = dim;
	}
	
	//UNTESTED
	public void onDraw(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fillRoundRect((this.topLeft.x - 8), (this.topLeft.y - 8), this.dimens.x, this.dimens.y, 15, 15);
		g.setColor(Color.BLACK);
		g.drawRoundRect((this.topLeft.x - 8), (this.topLeft.y - 8), this.dimens.x, this.dimens.y, 15, 15);
		
	}
	
	public VectorI getPos() {
		return this.topLeft;
	}
	
	
	public boolean clickedInside(Point p) {
		if(p.x >= (this.topLeft.x - 3) && p.x <= ((this.topLeft.x + this.dimens.x) + 3)) {
			if(p.y >= (this.topLeft.y - 3) && p.y <= ((this.topLeft.y + this.dimens.y) + 3)) {
				return true;
			}
		}
		return false;
	}
	
	public void onResize(VectorI tL, VectorI dim) {
		this.topLeft = tL;
		this.dimens = dim;
	}
	
	public void updateX(int x) {
		this.topLeft = new VectorI(x, this.topLeft.y);
	}
	
}