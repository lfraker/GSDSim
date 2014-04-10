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


	
public class InterventionOption extends JDialog {
	FrontEndPane parentfP;
	public Site site;
	
	
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
    	

    	kickOff.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					//HoldMeeting();





				}
		});

		cultAmbass.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					//PlaceAmbassador();
				}
		});

		depArch.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					//AllocateDeputyArchitect();
				}
		});

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
    	
    	
    	this.add(new JLabel("Hold kick-off meeting (Cost: )"));
    	this.add(kickPan);
    	this.add(new JLabel("Place cultural ambassadors (Cost: )"));
    	this.add(cultPan);
    	this.add(new JLabel("Allocate deputy architect (Cost: )"));
    	this.add(depPan);
    	this.pack();
    	this.setVisible(true);

		
		
	}
	
}