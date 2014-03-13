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
	private int timeZoneDiff;
	private JTextField timeZone;
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
		inputPanel.add(new JLabel("Site Name:"));
		inputPanel.add(this.siteN);
		this.siteN.setText("Site 1");
		inputPanel.add(new JLabel("Number of Workers:"));
		inputPanel.add(this.numEmp);
		inputPanel.add(new JLabel("Time Zone Hours Difference:"));
		inputPanel.add(this.timeZone);
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
		}
		catch (NumberFormatException e) {
			this.numEmployees = 5;
			this.timeZoneDiff = 0;
			this.numEmp.setText(this.numEmployees+"");
			this.timeZone.setText(this.timeZoneDiff+"");
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
	
	public int getTimeZone() {
		return this.timeZoneDiff;
	}
		
}
	
	
