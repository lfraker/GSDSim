package game.paneScreens;

import game.gamePanes.Pane;
import game.gamePanes.SettingsPane;

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
import java.util.concurrent.TimeUnit;

import javax.swing.JWindow;

import game.components.Button;
import game.components.Difficulty;
import game.components.Slider;

import game.backend.VectorI;


public class SettingsScreen extends Screen {
	

	public Button easy = new Button(new VectorI(0,0), new VectorI(0,0), "easy");
	public Button medium = new Button(new VectorI(0,0), new VectorI(0,0), "medium");
	public Button hard = new Button(new VectorI(0,0), new VectorI(0,0), "hard");
	public Difficulty difficulty;
	VectorI sliderPos;
	public Slider slide = new Slider(new VectorI(0,0), new VectorI(0,0));
	VectorI lineLeft;
	VectorI lineRight;
	VectorI dragDimens;
	int FTSmin;
	float laborCost;
	public boolean dragging;

	public SettingsScreen(Pane pP) {
		super(pP);
		this.fontText = new Font("serif", Font.BOLD, 25);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void onTick(float nanosSincePreviousTick) {
		
		float percent = ((this.timePerDay - 1)/200.0f);
		int tempX = this.lineLeft.x;
		int diff = this.lineRight.x - this.lineLeft.x;
		int toAdd = ((int)(diff * percent)); 
		this.slide.updateX((tempX + toAdd));
//		String lC = ((SettingsPane)this.parentPane).laborCost.getText();
//		String fTS = ((SettingsPane)this.parentPane).followTheSun.getText();
//		if (lC.length() > 0) {
//			try {
//				this.laborCost = Float.parseFloat(lC);
//				if (this.laborCost < 1.0f) {
//					String message = "Please enter a positive number with no spaces, default value of 100.00 has been set";
//					this.parentPane.showMessage(message);
//					((SettingsPane)this.parentPane).laborCost.setText("100.00");
//
//				}
//
//			}
//			catch(NumberFormatException e) {
//				String message = "Please enter only numbers with no spaces, default value of 100.00 has been set";
//				this.parentPane.showMessage(message);
//				((SettingsPane)this.parentPane).laborCost.setText("100.00");
//
//			}
//		}
//		if (fTS.length() > 0) {
//			try {
//				this.FTSmin = Integer.parseInt(fTS);
//				if (this.FTSmin < 1 || this.FTSmin > 15) {
//					String message = "Please enter a number between 1-15, default value of 15 has been set";
//					this.parentPane.showMessage(message);
//					((SettingsPane)this.parentPane).followTheSun.setText("15");
//
//				}
//			}
//			catch(NumberFormatException e) {
//				String message = "Please enter a number between 1-15 with no spaces, default value of 15 has been set";
//				this.parentPane.showMessage(message);
//				((SettingsPane)this.parentPane).followTheSun.setText("15");
//			}
//		}
	//	writeSettings();
	}

	//UNTESTED
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

		long secondsPerDay = (TimeUnit.SECONDS.convert(this.parentPane.getTotalTime(), TimeUnit.NANOSECONDS));

		long minu = (long)(secondsPerDay / 60);
		long seco = (long)(secondsPerDay % 60);
		int width2 = text.stringWidth("Length of Day: " + minu + " minutes and " + seco  + " seconds per day");
		
		
		g.drawString("Length of Day: " + minu + " minutes and " + seco  + " seconds per day", ((this.screenSize.x/2.0f) - width2/2.0f) , ((this.screenSize.y/7.0f)*3.0f));
		this.easy.onDraw(g);
		this.medium.onDraw(g);
		this.hard.onDraw(g);
		g.drawLine(this.lineLeft.x, this.lineLeft.y, this.lineRight.x, this.lineRight.y);
		this.slide.onDraw(g);
	}

	@Override
	public void onResize(VectorI newSize) {
		float percent = 0.0f;

		if (this.lineLeft != null && this.lineRight != null) {
			float diff = (this.lineRight.x - this.lineLeft.x);
			float pos = (this.sliderPos.x - this.lineLeft.x);

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
		VectorI tL = new VectorI(((int)(this.lineLeft.x + (percent * newDiff))), (this.lineLeft.y - 4));
		VectorI dim = new VectorI(10, 25);
		this.slide.onResize(tL, dim);
	}

	@Override
	public void onMouseReleased(MouseEvent e) {
		if (this.easy.clickedInside(e.getPoint())) {
			this.difficulty = Difficulty.EASY;
			this.easy.setPressed();
			this.medium.release();
			this.hard.release();
			this.parentPane.setDifficulty(Difficulty.EASY);
			this.parentPane.setCanPause();

		}
		if (this.medium.clickedInside(e.getPoint())) {
			this.difficulty = Difficulty.MEDIUM;
			this.medium.setPressed();
			this.easy.release();
			this.hard.release();
			this.parentPane.setDifficulty(Difficulty.MEDIUM);

		}
		if (this.hard.clickedInside(e.getPoint())) {
			this.difficulty = Difficulty.HARD;
			this.hard.setPressed();
			this.medium.release();
			this.easy.release();
			this.parentPane.setDifficulty(Difficulty.HARD);
		}
		
		//writeSettings();
	}


	public void readSettings() {
		try (BufferedReader buffRead = new BufferedReader(new FileReader("./gameFiles/settings.txt"))) {
			String currLine;
			while ((currLine = buffRead.readLine()) != null) {
				if (currLine.contains(":")) {
					String [] val = currLine.split(":");
					try {
						if (val[0].contains("Difficulty")) {
							switch (val[1]) {
							case "EASY":	this.difficulty = Difficulty.EASY;
								this.parentPane.setDifficulty(Difficulty.EASY);	
								this.parentPane.setCanPause();
								break;
							case "MEDIUM":	this.difficulty = Difficulty.MEDIUM;
								this.parentPane.setDifficulty(Difficulty.MEDIUM);	
								break;
							case "HARD":	this.difficulty = Difficulty.HARD;
								this.parentPane.setDifficulty(Difficulty.HARD);	
								break;
							default:		this.difficulty = Difficulty.EASY;
								this.parentPane.setDifficulty(Difficulty.EASY);	
								String message = "Please follow the correct input instructions. Check that there are no extra spaces, default values have been set. Edit the settings file so that it is correct.";
								this.parentPane.showMessage(message);	
								break;
							}

						}
						if (val[0].contains("Seconds Per Day")) 
						{
							int v1i = Integer.parseInt(val[1]);

							this.timePerDay = v1i;

							if (v1i < 1 || v1i > 200) 
							{
								String message = "Please follow the correct input instructions. Check that there are no extra spaces, default values have been set. Edit the settings file so that it is correct.";
								this.parentPane.showMessage(message);
								this.timePerDay = 30;
							}
							long timeNano = TimeUnit.NANOSECONDS.convert(this.timePerDay, TimeUnit.SECONDS);
							this.parentPane.setTimePerDay(timeNano);
						}
						if (val[0].contains("PCode")) {
							String [] valInner = val[0].split("=");
							String val1 = "4";
							if (val.length > 1) {
								val1 = val[1];
							}
							this.parentPane.updateGlobalParam(valInner[1], val1);


						}
//						if (val[0].contains("Labor Cost")) {
//							float v1 = Float.parseFloat(val[1]);
//							((SettingsPane)this.parentPane).laborCost.setText(""+val[1]);
//							if (v1 < 1.0f || v1 > 100.0f) {
//								String message = "Please follow the correct input instructions. Check that there are no extra spaces, default values have been set.";
//								this.parentPane.showMessage(message);
//								((SettingsPane)this.parentPane).laborCost.setText("100.00");
//								
//							}
//						}
//						if (val[0].contains("Follow The Sun Handoff Time")) {
//							int v1i = Integer.parseInt(val[1]);
//							((SettingsPane)this.parentPane).followTheSun.setText(""+val[1]);
//							if (v1i < 0 || v1i > 15) {
//								String message = "Please follow the correct input instructions. Check that there are no extra spaces, default values have been set.";
//								this.parentPane.showMessage(message);
//								((SettingsPane)this.parentPane).followTheSun.setText("15");
//							}
//						}
					} catch (NumberFormatException e) {
						String message = "Please follow the correct input instructions. Check that there are no extra spaces, default values have been set. Edit the settings file so that it is correct.";
						this.parentPane.showMessage(message);
						this.difficulty = Difficulty.EASY;
						this.parentPane.setDifficulty(Difficulty.EASY);	
						this.timePerDay = 30;
//						((SettingsPane)this.parentPane).laborCost.setText("100.00");
//						((SettingsPane)this.parentPane).followTheSun.setText("15");

					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			this.difficulty = Difficulty.EASY;
			String message = "Please follow the correct input instructions. Check that there are no extra spaces, default values have been set. Edit the settings file so that it is correct.";
			this.parentPane.showMessage(message);	
			
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.easy.release();
		this.medium.release();
		this.hard.release();
		switch (difficulty) {
			case EASY: this.easy.setPressed();
				break;
			case MEDIUM: this.medium.setPressed();
				break;
			case HARD: this.hard.setPressed();
				break;
		}
		
		
	}
	
//	public void writeSettings() {
//		return;
////		String diff="";
////		switch (difficulty) {
////			case 1: diff = "easy";
////				break;
////			case 2: diff = "medium";
////				break;
////			case 3: diff = "hard";
////				break;
////		}
//
////		String fileText = "This is the settings file. To change a setting, " +
////		"simply edit the value after the colon mark next to the field. " +
////		"Do not delete the colon, only edit the value after the colon the field." +
////		"Do not add any spaces after the colon either, only add the desired value\n" +
////		"*****************************\n" +
////		"Difficulty (EASY for easy, MEDIUM for medium, HARD for hard):"+this.difficulty +"\n" +
////		"Seconds Per Day (enter a number between 30-230):"+this.timePerDay+"\n"; // +
////		//"Labor Cost(enter a positive decimal):"+this.laborCost+"\n" +
////		//"Follow The Sun Handoff Time(enter a number between 1-15):"+this.FTSmin;
////		
////		File dir = new File("gameFiles");
////		 
////		if (!dir.exists()) {
////			dir.mkdir();
////		}
////		
////
////		File file = new File("./gameFiles/settings.txt");
////		if (!file.exists()) {
////			try {
////				file.createNewFile();
////			} catch (IOException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////		try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file, false))){
////				//buffWrite.write(fileText);
////		} catch (IOException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//	}

	@Override
	public void onMouseDragged(MouseEvent e) {
		if (this.dragging) {
			int xP = e.getPoint().x;
			if (xP >= this.dragDimens.x && xP <= this.dragDimens.y) {
				this.slide.updateX(e.getPoint().x);
				this.sliderPos = this.slide.getPos();
				float diff = (this.lineRight.x - this.lineLeft.x);
				float pos = (this.sliderPos.x - this.lineLeft.x);
				this.timePerDay = (((long)((pos/diff) * 200)) + 1);
				long timeNano = TimeUnit.NANOSECONDS.convert(this.timePerDay, TimeUnit.SECONDS);
				this.parentPane.setTimePerDay(timeNano);
			}
		}
		
	}

	@Override
	public void onMousePressed(MouseEvent e) {
		if (this.slide.clickedInside(e.getPoint())) {

			this.dragging = true;
		}
		
	}



	

	
}
