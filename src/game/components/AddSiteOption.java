package game.components;

import game.swingFramework.FrontEndPane;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddSiteOption extends JDialog {
	public int numEmployees;
	public JTextField siteN;
	public String siteName;
	public JTextField numEmp;
	public JTextField effDev;
	public JTextField costDev;
	public int timeZoneDiff;
	public JTextField timeZone;
	public int effD;
	public int costD;
	private FrontEndPane owne;
	public boolean cancelled = true;
	
	
	public AddSiteOption(FrontEndPane owner, String title) {
		super(owner.getWindow(), title, Dialog.ModalityType.DOCUMENT_MODAL);
		this.owne = owner;
		JPanel btnPanel = new JPanel();
		JButton okBtn = new JButton("Accept");
		JButton noBtn = new JButton("Cancel");
		this.setMinimumSize(new Dimension(700, 300));
		this.setPreferredSize(new Dimension(700, 300));
		this.setMaximumSize(new Dimension(700, 300));
		btnPanel.add(okBtn);
		okBtn.addActionListener(new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					okButton();
				}
		});
		noBtn.addActionListener(new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				noButton();
			}
		});
		btnPanel.add(noBtn);
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(0,2));
		this.siteN = new JTextField();
		this.numEmp = new JTextField();
		this.timeZone = new JTextField();
		this.effDev = new JTextField();
		this.costDev = new JTextField();
		inputPanel.add(new JLabel("Site Name:"));
		inputPanel.add(this.siteN);
		this.siteN.setText("Site 1");
		inputPanel.add(new JLabel("Number of Workers:"));
		inputPanel.add(this.numEmp);
		inputPanel.add(new JLabel("Time Zone Hours Difference:"));
		inputPanel.add(this.timeZone);
		inputPanel.add(new JLabel("<html>Cost of Developer-Day measured in Euros: enter a positive number or default values will be set</html>"));
		inputPanel.add(this.costDev);
		inputPanel.add(new JLabel("<html>Effort Per Developer-Day measured as the percentage per day the developer actually works: enter a number from 1-100 or default values will be set</html>"));
		inputPanel.add(this.effDev);
		getContentPane().add(inputPanel, BorderLayout.CENTER);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		pack();
		
	}
	public void noButton() {
		this.setVisible(false);
	}
	
	public void okButton() {
		this.siteName = this.siteN.getText();
		if (this.siteName.length() == 0) {
			return;
		}
		try {
			this.numEmployees = Integer.parseInt(this.numEmp.getText());
		}
		catch (NumberFormatException e) {
			this.numEmployees = 5;
			this.numEmp.setText(this.numEmployees+"");
			return;
		}
		try {
			this.timeZoneDiff = Integer.parseInt(this.timeZone.getText());
		}
		catch (NumberFormatException e) {
			this.timeZoneDiff = 0;
			this.timeZone.setText(this.timeZoneDiff+"");
			return;
		}
		try {
			this.costD = Integer.parseInt(this.costDev.getText());
		}
		catch (NumberFormatException e) {
			try {
				this.costD = Integer.parseInt(this.owne.getGlobalParam("AvgCostDevDay"));
			}
			catch (NumberFormatException e2) {
				this.costD = 4;
			}
			if (this.costD < 0) {
				this.costD = 4;
			}
			this.costDev.setText(this.costD+"");
			return;
		}
		try {
			this.effD = Integer.parseInt(this.effDev.getText());
		}
		catch (NumberFormatException e) {
			try {
				this.effD = Integer.parseInt(this.owne.getGlobalParam("AvgEffDevDay"));
			}
			catch (NumberFormatException e2) {
				this.effD = 90;
			}
			if (this.effD < 1 || this.effD > 100) {
				this.effD = 90;
			}
			this.effDev.setText(this.effD+"");
			return;
		}
		this.cancelled = false;
		this.setVisible(false);
	}
	
	public int getNumberEmployees() {
		return this.numEmployees;
	}
	
	public String getSiteName() {
		return this.siteName;
	}
	
	public boolean getCancelled() {
		return this.cancelled;
	}
	
	public int getCostDev() {
		return this.costD;
	}
	
	public int getEffortDev() {
		return this.effD;
	}
	
	public int getTimeZone() {
		return this.timeZoneDiff;
	}
		
}
	
	
