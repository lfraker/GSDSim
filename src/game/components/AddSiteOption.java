package game.components;

import game.swingFramework.FrontEndPane;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddSiteOption extends JDialog {
	private int numEmployees;
	private JTextField siteN;
	private String siteName;
	private JTextField numEmp;
	private JTextField effDev;
	private JTextField costDev;
	private int timeZoneDiff;
	private JTextField timeZone;
	private int effD;
	private int costD;
	private boolean cancelled = true;
	
	
	public AddSiteOption(Frame owner, String title) {
		super(owner, title, Dialog.ModalityType.DOCUMENT_MODAL);
		JPanel btnPanel = new JPanel();
		JButton okBtn = new JButton("Accept");
		JButton noBtn = new JButton("Cancel");
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
		inputPanel.add(new JLabel("Cost of Developer-Day:"));
		inputPanel.add(this.costDev);
		inputPanel.add(new JLabel("Effort Per Developer-Day:"));
		inputPanel.add(this.effDev);
		getContentPane().add(inputPanel, BorderLayout.CENTER);

		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		pack();
		
	}
	private void noButton() {
		this.setVisible(false);
	}
	
	private void okButton() {
		try {
			this.siteName = this.siteN.getText();
			if (this.siteName.length() == 0) {
				return;
			}
			this.numEmployees = Integer.parseInt(this.numEmp.getText());
			this.timeZoneDiff = Integer.parseInt(this.timeZone.getText());
			this.costD = Integer.parseInt(this.costDev.getText());
			this.effD = Integer.parseInt(this.effDev.getText());
		}
		catch (NumberFormatException e) {
			this.numEmployees = 5;
			this.timeZoneDiff = 0;
			this.costD = 4;
			this.effD = 10;
			this.numEmp.setText(this.numEmployees+"");
			this.timeZone.setText(this.timeZoneDiff+"");
			this.effDev.setText(this.effD+"");
			this.costDev.setText(this.costD+"");
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
	
	
