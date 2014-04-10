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

import game.components.ProcessSimulator;


	
public class InterventionOption extends JDialog {
	FrontEndPane parentfP;
	public Site site;

	ProcessSimulator ps;
	
	
	public InterventionOption(FrontEndPane f) {
		super(f.getWindow(), "Intervention Options");
		this.parentfP = f;
		this.setLayout(new GridLayout(0,2));
    	this.setMinimumSize(new Dimension(750, 650));
		this.setPreferredSize(new Dimension(800, 700));
		this.setMaximumSize(new Dimension(900, 900));

		this.ps = this.parentfP.processSimulator;


    	JButton ftf1 = new JButton("Face-to-face meeting");
    	JButton xchg = new JButton("Exchange program");
    	JButton syncCom = new JButton("Synchronous communication possibilities");
    	JButton vidConf = new JButton("Support for video conferencing");
    	JButton commtools = new JButton("Suitable selection of communication tools");
    	JButton relocate = new JButton("Relocate to adjacent time zone");
    	JButton adoptFTS = new JButton("Adopt Follow the Sun development");
    	JButton bridgingTeam = new JButton("Create bridging team");
    	JButton ftf2 = new JButton("Face-to-face meeting");
    	JButton culturalTraining = new JButton("Cultural Training");
    	JButton cultAmbass = new JButton("Cultural Ambassador");
    	JButton lowContext = new JButton("Adopt low-context communication style");
    	JButton reduceCrossCultInt = new JButton("Reduce cross cultural interaction");



    	









    	ftf1.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 4);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 500000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//500,000 
				}
		});

		xchg.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 4);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 500000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//500,000 
				}
		});

		syncCom.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 3);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 125000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//125,000 
				}
		});

		vidConf.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 2);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 25000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//25,000 

				}
		});

		commtools.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 2);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 25000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//25,000 
				}
		});

		relocate.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 4);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 500000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//500,000 
				}
		});

		adoptFTS.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 4);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 500000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//500,000 

				}
		});

		bridgingTeam.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 3);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 125000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//125,000 
				}
		});

		ftf2.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 4);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 500000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//500,000 
				}
		});

		culturalTraining.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 3);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 125000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//125,000 

				}
		});

		cultAmbass.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 3);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 125000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//125,000 
				}
		});


		lowContext.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 2);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 25000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					//25,000
				}
		});

		reduceCrossCultInt.addActionListener(new ActionListener() 
		{
				public void actionPerformed(java.awt.event.ActionEvent e) 
				{
					ps.SetInterventionEffects(ps.GetInterventionEffects() + 1);
					float currMon = 0.0f;
					try {
						currMon = Float.parseFloat(parentfP.getGlobalParam("UsrMoney"));
					}
					catch (NumberFormatException e2) {
						currMon = FrontEndPane.budgStart;
					}
					currMon -= 5000.0f;
					parentfP.updateGlobalParam("UsrMoney", currMon+"");
					// 5,000
				}
		});

    	JPanel ftf1Pan = new JPanel();
    	ftf1Pan.setLayout(new GridLayout(3,0));
    	ftf1Pan.add(new JLabel());
    	ftf1Pan.add(ftf1);
    	ftf1Pan.setOpaque(false);

    	JPanel xchgPan = new JPanel();
    	xchgPan.setLayout(new GridLayout(3,0));
    	xchgPan.add(new JLabel());
    	xchgPan.add(xchg);
    	xchgPan.setOpaque(false);

    	JPanel syncComPan = new JPanel();
    	syncComPan.setLayout(new GridLayout(3,0));
    	syncComPan.add(new JLabel());
    	syncComPan.add(syncCom);
    	syncComPan.setOpaque(false);

    	JPanel vidConfPan = new JPanel();
    	vidConfPan.setLayout(new GridLayout(3,0));
    	vidConfPan.add(new JLabel());
    	vidConfPan.add(vidConf);
    	vidConfPan.setOpaque(false);

    	JPanel commtoolsPan = new JPanel();
    	commtoolsPan.setLayout(new GridLayout(3,0));
    	commtoolsPan.add(new JLabel());
    	commtoolsPan.add(commtools);
    	commtoolsPan.setOpaque(false);

    	JPanel relocatePan = new JPanel();
    	relocatePan.setLayout(new GridLayout(3,0));
    	relocatePan.add(new JLabel());
    	relocatePan.add(relocate);
    	relocatePan.setOpaque(false);

    	JPanel adoptFTSPan = new JPanel();
    	adoptFTSPan.setLayout(new GridLayout(3,0));
    	adoptFTSPan.add(new JLabel());
    	adoptFTSPan.add(adoptFTS);
    	adoptFTSPan.setOpaque(false);

    	JPanel bridgingTeamPan = new JPanel();
    	bridgingTeamPan.setLayout(new GridLayout(3,0));
    	bridgingTeamPan.add(new JLabel());
    	bridgingTeamPan.add(bridgingTeam);
    	bridgingTeamPan.setOpaque(false);

    	JPanel ftf2Pan = new JPanel();
    	ftf2Pan.setLayout(new GridLayout(3,0));
    	ftf2Pan.add(new JLabel());
    	ftf2Pan.add(ftf2);
    	ftf2Pan.setOpaque(false);

    	
    	JPanel culturalTrainingPan = new JPanel();
    	culturalTrainingPan.setLayout(new GridLayout(3,0));
    	culturalTrainingPan.add(new JLabel());
    	culturalTrainingPan.add(culturalTraining);
    	culturalTrainingPan.setOpaque(false);

    	JPanel cultAmbassPan = new JPanel();
    	cultAmbassPan.setLayout(new GridLayout(3,0));
    	cultAmbassPan.add(new JLabel());
    	cultAmbassPan.add(cultAmbass);
    	cultAmbassPan.setOpaque(false);

    	JPanel lowContextPan = new JPanel();
    	lowContextPan.setLayout(new GridLayout(3,0));
    	lowContextPan.add(new JLabel());
    	lowContextPan.add(lowContext);
    	lowContextPan.setOpaque(false);

    	JPanel reduceCrossCultIntPan = new JPanel();
    	reduceCrossCultIntPan.setLayout(new GridLayout(3,0));
    	reduceCrossCultIntPan.add(new JLabel());
    	reduceCrossCultIntPan.add(reduceCrossCultInt);
    	reduceCrossCultIntPan.setOpaque(false);
    	
    	this.add(new JLabel("Hold Face-to-face meeting (Cost: 500,000 Euros)"));
    	this.add(ftf1Pan);
    	this.add(new JLabel("Exchange program (Cost: 500,000 Euros)"));
    	this.add(xchgPan);
    	this.add(new JLabel("Synchronous communication possibilities (Cost: 125,000 Euros)"));
    	this.add(syncComPan);
    	this.add(new JLabel("Support for video conferencing (Cost: 25,000 Euros)"));
    	this.add(vidConfPan);
    	this.add(new JLabel("Suitable selection of communication tools (Cost: 25,000 Euros)"));
    	this.add(commtoolsPan);
    	this.add(new JLabel("Relocate to adjacent time zone (Cost: 500,000 Euros)"));
    	this.add(relocatePan);
    	this.add(new JLabel("Adopt Follow the Sun development (Cost: 500,000 Euros)"));
    	this.add(adoptFTSPan);
    	this.add(new JLabel("Create bridging team (Cost: 125,000 Euros)"));
    	this.add(bridgingTeamPan);
    	this.add(new JLabel("Face-to-face meeting (Cost: 500,000 Euros)"));
    	this.add(ftf2Pan);
    	this.add(new JLabel("Cultural Training (Cost: 125,000 Euros)"));
    	this.add(culturalTrainingPan);
    	this.add(new JLabel("Cultural Ambassador (Cost: 125,000 Euros)"));
    	this.add(cultAmbassPan);
    	this.add(new JLabel("Adopt low-context communication style (Cost: 25,000 Euros)"));
    	this.add(lowContextPan);
    	this.add(new JLabel("Reduce cross cultural interaction (Cost: 5,000 Euros)"));
    	this.add(reduceCrossCultIntPan);
    	this.pack();
    	this.setVisible(true);

		
		
	}
	
}