package game.components;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class InterventionOption extends JFrame {
	
	
	
	public InterventionOption() {
		super("Intervention Options");
		this.setLayout(new GridLayout(0,2));
    	this.setMinimumSize(new Dimension(600, 200));
		this.setPreferredSize(new Dimension(600, 200));
		this.setMaximumSize(new Dimension(600, 200));
    	JButton kickOff = new JButton("Enact Intervention");
    	JButton cultAmbass = new JButton("Enact Intervention");
    	JButton depArch = new JButton("Enact Intervention");
    	
    	
    	this.add(new JLabel("Hold kick-off meeting (Cost: )"));
    	this.add(kickOff);
    	this.add(new JLabel("Place cultural ambassadors (Cost: )"));
    	this.add(cultAmbass);
    	this.add(new JLabel("Allocate deputy architect (Cost: )"));
    	this.add(depArch);
    	this.pack();
    	this.setVisible(true);

		
		
	}
	
	
	
	
}