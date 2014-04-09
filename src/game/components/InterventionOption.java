package game.components;

import game.swingFramework.FrontEndPane;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;


<<<<<<< HEAD
public class InterventionOption extends JFrame {

	public Site site;
	
=======
public class InterventionOption extends JDialog {
	FrontEndPane parentfP;
>>>>>>> dbf875563fa5cf7d4296d13a35c7c16a70e58d13
	
	
	public InterventionOption(FrontEndPane f) {
		super(f.getWindow(), "Intervention Options");
		this.parentfP = f;
		this.setLayout(new GridLayout(0,2));
    	this.setMinimumSize(new Dimension(500, 150));
		this.setPreferredSize(new Dimension(600, 200));
		this.setMaximumSize(new Dimension(700, 250));
    	JButton kickOff = new JButton("Enact Intervention");
    	JButton cultAmbass = new JButton("Enact Intervention");
    	JButton depArch = new JButton("Enact Intervention");
    	
<<<<<<< HEAD

    	kickOff.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					HoldMeeting();
				}
		});

		cultAmbass.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					PlaceAmbassador();
				}
		});

		depArch.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					AllocateDeputyArchitect();
				}
		});

=======
    	JPanel kickPan = new JPanel();
    	kickPan.setLayout(new GridLayout(3,0));
    	kickPan.add(new JLabel());
    	kickPan.add(kickOff);
    	kickPan.setOpaque(false);
    	
    	
    	JPanel cultPan = new JPanel();
    	cultPan.setLayout(new GridLayout(3,0));
    	cultPan.add(new JLabel());
    	cultPan.add(cultAmbass);
    	cultPan.setOpaque(false);
    	
    	
    	JPanel depPan = new JPanel();
    	depPan.setLayout(new GridLayout(3,0));
    	depPan.add(new JLabel());
    	depPan.add(depArch);
    	depPan.setOpaque(false);
    	
>>>>>>> dbf875563fa5cf7d4296d13a35c7c16a70e58d13
    	
    	this.add(new JLabel("Hold kick-off meeting (Cost: )"));
    	this.add(kickPan);
    	this.add(new JLabel("Place cultural ambassadors (Cost: )"));
    	this.add(cultPan);
    	this.add(new JLabel("Allocate deputy architect (Cost: )"));
    	this.add(depPan);
    	this.pack();
    	this.setVisible(true);

		
		
	}

	public void HoldMeeting()
	{
		
	}
	
	public void PlaceAmbassador()
	{

	}
	
	public void AllocateDeputyArchitect()
	{

	}
	
}