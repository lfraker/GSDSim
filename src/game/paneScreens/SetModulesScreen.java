package game.paneScreens;

import game.gamePanes.Pane;
import game.gamePanes.SetModulesPane;

import game.gamePanes.SettingsPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import game.components.Button;
import game.components.Difficulty;
import game.components.Site;
import game.components.Slider;

import game.backend.VectorI;


public class SetModulesScreen extends Screen {
//	Button triggerDayEnd = new Button(new VectorI(50,50), new VectorI(250,150), "Trigger end of day");
//	Button addModule = new Button(new VectorI(350,50), new VectorI(250,150), "Add module to list");


	public SetModulesScreen(Pane pP) {
		super(pP);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onTick(float nanosSincePreviousTick) {
	
	}

	@Override
	public void onDraw(Graphics2D g) {
		List<Site> temp = ((SetModulesPane)this.parentPane).getSiteList();
//		this.triggerDayEnd.onDraw(g);
//		this.addModule.onDraw(g);
		int height = this.parentPane.getHeight();
		Font newF = new Font("serif", Font.BOLD, (height/30));
		g.setFont(newF);
		int y = (2 *(height/15));
		//System.out.println(temp.size());
		for (Site s : temp) {
			int width = g.getFontMetrics().stringWidth(s.toString());
			int x = ((this.parentPane.getWidth()/2) - (width/2));
			g.drawString(s.toString(), x, y);
			y += (height/30);
		}

	}

	@Override
	public void onResize(VectorI newSize) {
		
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		
	}

	@Override
	public void onKeyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onKeyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void readSettings() {
		
		
	}
	
	public void writeSettings() {

	}

	@Override
	public void onMouseDragged(MouseEvent e) {
	
	}

	@Override
	public void onMousePressed(MouseEvent e) {
//		Point p = e.getPoint();
//		if (this.triggerDayEnd.clickedInside(p)) {
//			this.parentPane.testDayEnd();
//			System.out.println("TRIGGERED END OF DAY");
//		}
//		if (this.addModule.clickedInside(p)) {
//			this.parentPane.testAddModule();
//		}
//		
	}
	

	
}