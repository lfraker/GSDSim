package game.backend;

import game.swingFramework.FrontEndPane;


public class Startup {
	FrontEndPane game;
	
	
	public void doStartup() {
		this.game = new FrontEndPane();
		this.game.setupFrame();
	}
	
	
	
}