package game.backend;

import game.swingFramework.FrontEndPane;


public class Startup {
	public FrontEndPane game;
	
	
	public void doStartup() {
		this.game = new FrontEndPane();
		this.game.setupFrame();
	}
	
	
	
}