package components;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;

import backend.VectorI;

public class Button {
	
	VectorI topLeft;
	VectorI dimens;
	boolean pressed = false;
	String text;
	Font fontText = new Font("serif", Font.BOLD, 25);

	
	public Button (VectorI tL, VectorI dim, String tex) {
		this.topLeft = tL;
		this.dimens = dim;
		this.text = tex;
	}
	
	public void onDraw(Graphics2D g) {
		g.setColor(Color.WHITE);

		g.fillRoundRect(this.topLeft.x, this.topLeft.y, this.dimens.x, this.dimens.y, 15, 15);
		if (this.pressed) {
			g.setColor(Color.GRAY);
			g.fillRoundRect((this.topLeft.x + 4), (this.topLeft.y + 4), (this.dimens.x - 8), (this.dimens.y - 8), 15, 15);

		}
		g.setColor(Color.BLACK);
		g.drawRoundRect(this.topLeft.x, this.topLeft.y, this.dimens.x, this.dimens.y, 15, 15);
		g.drawRoundRect((this.topLeft.x + 4), (this.topLeft.y + 4), (this.dimens.x - 8), (this.dimens.y - 8), 15, 15);
		g.setFont(fontText);
		FontMetrics text = g.getFontMetrics();
		int width = text.stringWidth(this.text);
		int height = text.getHeight();
		g.drawString(this.text, (this.topLeft.x + ((this.dimens.x/2.0f) - width/2.0f)), (this.topLeft.y + ((this.dimens.y/2.0f) + height/3.0f)));
		
		
	}
	
	public void setPressed() {
		this.pressed = true;
	}
	
	public void release() {
		this.pressed = false;
	}
	
	public boolean clickedInside(Point p) {
		if(p.x >= this.topLeft.x && p.x <= (this.topLeft.x + this.dimens.x)) {
			if(p.y >= this.topLeft.y && p.y <= (this.topLeft.y + this.dimens.y)) {
				return true;
			}
		}
		return false;
	}
	
	public void onResize(VectorI tL, VectorI dim) {
		this.topLeft = tL;
		this.dimens = dim;
	}
	
	
}