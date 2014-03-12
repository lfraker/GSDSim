package game.paneScreens;

import game.gamePanes.Pane;


import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import game.backend.VectorI;



/**
 * Interface implemented by every screens in each game.
 * @author lfraker
 *
 */
public abstract class Screen {
	Pane parentPane;
	Font fontText;
	VectorI screenSize;
	int timePerDay;

	
	public Screen(Pane pP) {
		this.parentPane = pP;
	}
	
	public float getTime() {
		return this.parentPane.getTime();
	}
	
	public int getDays() {
		return this.parentPane.getDays();
	}
	
	public void enableSettings() {
		this.parentPane.enableSettings();	
	}
	public void disableSettings() {
		this.parentPane.disableSettings();
	}
	public void enableModules() {
		this.parentPane.enableModules();	
	}
	public void disableModules() {
		this.parentPane.disableModules();	
	}
	public void enableSites() {
		this.parentPane.enableSites();	
	}
	public void disableSites() {
		this.parentPane.disableSites();	
	}

	
	/**
	 * Method defining what to do on each tick recieved.
	 * @param nanosSincePreviousTick - time elapsed since last tick recieved.
	 */
	public abstract void onTick(float nanosSincePreviousTick);
	
	/**
	 * Method defining what to do on each draw recieved.
	 * @param g - brush to draw with.
	 */
	public abstract void onDraw(Graphics2D g);
	
	
	/**
	 * Method defining what to do on each resize.
	 * @param newSize - the new screen Size.
	 */
	public abstract void onResize(VectorI newSize);
	
	/**
	 * Method defining what to do on each mouse click.
	 * @param e - mouse event fired from clicking of mouse.
	 */
	public abstract void onMouseReleased(MouseEvent e);
	
	/**
	 * Method defining what to do for each key typed.
	 * @param e - keyboard event fired from typing key.
	 */
	public abstract void onKeyTyped(KeyEvent e);
	
	/**
	 * Method defining what to do for each key released.
	 * @param e - keyboard event fired from releasing key.
	 */
	public abstract void onKeyReleased(KeyEvent e);
	
	/**
	 * Method defining what to do for each key pressed.
	 * @param e - keyboard event fired from releasing key.
	 */
	public abstract void onKeyPressed(KeyEvent e);
	
	public abstract void onMouseDragged(MouseEvent e);
	
	public abstract void onMousePressed(MouseEvent e);
	
}
