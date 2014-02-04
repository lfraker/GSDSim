package paneScreens;

import gamePanes.Pane;
import gamePanes.SettingsPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
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

import components.Button;
import components.Slider;

import backend.VectorI;


public class SettingsScreen extends Screen {
	

	VectorI screenSize;
	Font fontText = new Font("serif", Font.BOLD, 25);
	Button easy = new Button(new VectorI(0,0), new VectorI(0,0), "easy");
	Button medium = new Button(new VectorI(0,0), new VectorI(0,0), "medium");
	Button hard = new Button(new VectorI(0,0), new VectorI(0,0), "hard");
	int difficulty;
	VectorI sliderPos;
	Slider slide = new Slider(new VectorI(0,0), new VectorI(0,0));
	VectorI lineLeft;
	VectorI lineRight;
	VectorI dragDimens;
	int FTSmin;
	float laborCost;
	boolean dragging;
	int timePerDay;

	public SettingsScreen(Pane pP) {
		super(pP);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onTick(float nanosSincePreviousTick) {
		float percent = ((this.timePerDay - 15)/100.0f);
		int tempX = this.lineLeft.x;
		int diff = this.lineRight.x - this.lineLeft.x;
		int toAdd = ((int)(diff * percent)); 
		this.slide.updateX((tempX + toAdd));
		String lC = ((SettingsPane)this.parentPane).laborCost.getText();
		String fTS = ((SettingsPane)this.parentPane).followTheSun.getText();
		if (lC.length() > 0) {
			try {
				this.laborCost = Float.parseFloat(lC);
				if (this.laborCost < 1.0f) {
					String message = "Please enter a positive number with no spaces, default value of 100.00 has been set";
					this.parentPane.showMessage(message);
					((SettingsPane)this.parentPane).laborCost.setText("100.00");

				}

			}
			catch(NumberFormatException e) {
				String message = "Please enter only numbers with no spaces, default value of 100.00 has been set";
				this.parentPane.showMessage(message);
				((SettingsPane)this.parentPane).laborCost.setText("100.00");

			}
		}
		if (fTS.length() > 0) {
			try {
				this.FTSmin = Integer.parseInt(fTS);
				if (this.FTSmin < 1 || this.FTSmin > 15) {
					String message = "Please enter a number between 1-15, default value of 15 has been set";
					this.parentPane.showMessage(message);
					((SettingsPane)this.parentPane).followTheSun.setText("15");

				}
			}
			catch(NumberFormatException e) {
				String message = "Please enter a number between 1-15 with no spaces, default value of 15 has been set";
				this.parentPane.showMessage(message);
				((SettingsPane)this.parentPane).followTheSun.setText("15");
			}
		}
		writeSettings();
	}

	@Override
	public void onDraw(Graphics2D g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(0, 0, screenSize.x, screenSize.y, 25, 25);
		g.setColor(Color.BLACK);
		g.drawRoundRect(0, 0, screenSize.x - 2, screenSize.y - 2, 25, 25);
		g.setFont(fontText);
		FontMetrics text = g.getFontMetrics();
		int width = text.stringWidth("Difficulty:");
		g.drawString("Difficulty:", ((this.screenSize.x/2.0f) - width/2.0f) , ((this.screenSize.y/7.0f)*1.0f));
		int width2 = text.stringWidth("Length of Day: " + this.timePerDay + " minutes per day");
		g.drawString("Length of Day: " + this.timePerDay + " minutes per day", ((this.screenSize.x/2.0f) - width2/2.0f) , ((this.screenSize.y/7.0f)*3.0f));
		this.easy.onDraw(g);
		this.medium.onDraw(g);
		this.hard.onDraw(g);
		g.drawLine(this.lineLeft.x, this.lineLeft.y, this.lineRight.x, this.lineRight.y);
		this.slide.onDraw(g);
	}

	@Override
	public void onResize(VectorI newSize) {
		float percent = 0.0f;
		System.out.println(this.lineRight);
		System.out.println(this.lineLeft);
		if (this.lineLeft != null && this.lineRight != null) {
			float diff = (this.lineRight.x - this.lineLeft.x);
			float pos = (this.sliderPos.x - this.lineLeft.x);
			System.out.println("DIFF : " + diff);
			System.out.println("POS : " + pos);
			percent = pos/diff;
		}
		
		this.screenSize = newSize;
		this.dragDimens = new VectorI((this.screenSize.x/4), ((this.screenSize.x/4)*3));
		if (this.sliderPos == null) {
			this.sliderPos = new VectorI((this.screenSize.x/4), ((this.screenSize.y/7)*4));
		}
		this.fontText = new Font("serif", Font.BOLD, (newSize.y/20));
		int yCoord = (newSize.y/5);
		int xCoord = (newSize.x/12);
		int width = (newSize.x/7);
		int height = (newSize.y/7);
		this.easy.onResize(new VectorI((xCoord * 3),yCoord), new VectorI(width,height));
		this.medium.onResize(new VectorI((xCoord * 5),yCoord), new VectorI(width,height));
		this.hard.onResize(new VectorI((xCoord * 7),yCoord), new VectorI(width,height));
		this.lineLeft = new VectorI((this.screenSize.x/4), ((this.screenSize.y/7)*4));
		this.lineRight = new VectorI(((this.screenSize.x/4)*3), ((this.screenSize.y/7)*4));
		int xP = this.sliderPos.x;
		if (xP < this.lineLeft.x) {
			this.slide.updateX(this.lineLeft.x);
			this.sliderPos = this.slide.getPos();
		}
		if (xP > this.lineRight.x) {
			this.slide.updateX(this.lineRight.x);
			this.sliderPos = this.slide.getPos();
		}
	
	
		//float percent = pos/diff;
		int newDiff = (this.lineRight.x - this.lineLeft.x);
		float toAdd = (newDiff * percent);
		System.out.println("TO ADD : " + toAdd);
		System.out.println("PERCENT : " + percent);
System.out.println("SLIDER POS x : " + this.sliderPos.x);
System.out.println("THIS RIGHT : " + this.lineRight.x);
		VectorI tL = new VectorI(((int)(this.lineLeft.x + (percent * newDiff))), (this.lineLeft.y - 4));
		VectorI dim = new VectorI(10, 25);
		this.slide.onResize(tL, dim);
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		if (this.easy.clickedInside(e.getPoint())) {
			this.difficulty = 0;
			this.easy.setPressed();
			this.medium.release();
			this.hard.release();
		}
		if (this.medium.clickedInside(e.getPoint())) {
			this.difficulty = 1;
			this.medium.setPressed();
			this.easy.release();
			this.hard.release();
		}
		if (this.hard.clickedInside(e.getPoint())) {
			this.difficulty = 2;
			this.hard.setPressed();
			this.medium.release();
			this.easy.release();
		}
		
		writeSettings();
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
		
		try (BufferedReader buffRead = new BufferedReader(new FileReader("gameFiles/settings.txt"))) {
			String currLine;
			while ((currLine = buffRead.readLine()) != null) {
				if (currLine.contains(":")) {
					String [] val = currLine.split(":");
					if (val[0].contains("Difficulty")) {
						this.difficulty = Integer.parseInt(val[1]);
					}
					if (val[0].contains("Minutes Per Day")) {
						this.timePerDay = Integer.parseInt(val[1]);
					}
					if (val[0].contains("Labor Cost")) {
						((SettingsPane)this.parentPane).laborCost.setText(""+val[1]);
					}
					if (val[0].contains("Follow The Sun Handoff Time")) {
						((SettingsPane)this.parentPane).followTheSun.setText(""+val[1]);
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.difficulty = 0;
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		switch (difficulty) {
			case 0: this.easy.setPressed();
				break;
			case 1: this.medium.setPressed();
				break;
			case 2: this.hard.setPressed();
				break;
		}
		
		
	}
	
	public void writeSettings() {
//		String diff="";
//		switch (difficulty) {
//			case 1: diff = "easy";
//				break;
//			case 2: diff = "medium";
//				break;
//			case 3: diff = "hard";
//				break;
//		}

		String fileText = "This is the settings file. To change a setting, " +
		"simply edit the value after the colon mark next to the field. " +
		"Do not delete the colon, only edit the value after the colon the field." +
		"Do not add any spaces after the colon either, only add the desired value\n" +
		"*****************************\n" +
		"Difficulty (1 for easy, 2 for medium, 3 for hard):"+this.difficulty +"\n" +
		"Minutes Per Day (enter a number between 15-115):"+this.timePerDay+"\n" +
		"Labor Cost(enter a positive decimal):"+this.laborCost+"\n" +
		"Follow The Sun Handoff Time(enter a number between 1-15):"+this.FTSmin;
		
		File dir = new File("gameFiles");
		 
		if (!dir.exists()) {
			dir.mkdir();
		}
		

		File file = new File("gameFiles/settings.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file, false))){
				buffWrite.write(fileText);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		if (this.dragging) {
			int xP = e.getPoint().x;
			if (xP >= this.dragDimens.x && xP <= this.dragDimens.y) {
				this.slide.updateX(e.getPoint().x);
				this.sliderPos = this.slide.getPos();
				//System.out.println("DRAGGING");
				float diff = (this.lineRight.x - this.lineLeft.x);
				float pos = (this.sliderPos.x - this.lineLeft.x);
				this.timePerDay = (((int)((pos/diff) * 100)) + 15);
			}
		}
		
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		if (this.slide.clickedInside(e.getPoint())) {
			//this.initialDragPos = new VectorI(e.getPoint().x, e.getPoint().y);
			//this.
			this.dragging = true;
		}
		
	}
	

	
}