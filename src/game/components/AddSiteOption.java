package game.components;

import game.swingFramework.FrontEndPane;

import java.awt.BorderLayout;
import java.awt.Checkbox;
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
	public Checkbox oneLack;
	public Checkbox twoUneven;
	public Checkbox threeEast;
	public Checkbox fourHigh;
	public Checkbox fiveDiff;
	public Checkbox sixDiff;
	public int timeZoneDiff;
	public JTextField timeZone;
	public float effD;
	public int costD;
	private FrontEndPane owne;
	public boolean cancelled = true;
	
	
	public AddSiteOption(FrontEndPane owner, String title) {
		super(owner.getWindow(), title, Dialog.ModalityType.DOCUMENT_MODAL);
		this.owne = owner;
		JPanel btnPanel = new JPanel();
		JButton okBtn = new JButton("Accept");
		JButton noBtn = new JButton("Cancel");
		this.setMinimumSize(new Dimension(550, 550));
		this.setPreferredSize(new Dimension(600, 600));
		this.setMaximumSize(new Dimension(700, 650));
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
		this.oneLack = new Checkbox();
		this.twoUneven = new Checkbox();
		this.threeEast = new Checkbox();
		this.fourHigh = new Checkbox();
		this.fiveDiff = new Checkbox();
		this.sixDiff = new Checkbox();
		inputPanel.add(new JLabel("Site Name:"));
		inputPanel.add(this.siteN);
		this.siteN.setText("Site 1");
		inputPanel.add(new JLabel("Number of Workers:"));
		inputPanel.add(this.numEmp);
		inputPanel.add(new JLabel("Time Zone Hours Difference:"));
		inputPanel.add(this.timeZone);
		inputPanel.add(new JLabel("<html>Cost Per Developer Per Day measured in Euros: enter a positive number or default values will be set</html>"));
		inputPanel.add(this.costDev);
		inputPanel.add(new JLabel("<html>Effort Per Developer-Day: Efficiency of developers. 1.0 is normal.</html>"));
		inputPanel.add(this.effDev);
		inputPanel.add(new JLabel("<html>Lack of a common language</html>"));
		inputPanel.add(this.oneLack);
		inputPanel.add(new JLabel("<html>Uneven language skills</html>"));
		inputPanel.add(this.twoUneven);
		inputPanel.add(new JLabel("<html>East/West divide in culture</html>"));
		inputPanel.add(this.threeEast);
		inputPanel.add(new JLabel("<html>High versus Low context cultures</html>"));
		inputPanel.add(this.fourHigh);
		inputPanel.add(new JLabel("<html>Different national culture</html>"));
		inputPanel.add(this.fiveDiff);
		inputPanel.add(new JLabel("<html>Different organizational culture</html>"));
		inputPanel.add(this.sixDiff);
		getContentPane().add(inputPanel, BorderLayout.CENTER);
		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		pack();
		
	}
	public void noButton() {
		this.setVisible(false);
	}
	
	public boolean [] getChecks() {
		boolean [] toRet = new boolean[6];
		toRet[0] = this.oneLack.getState();
		toRet[1] = this.twoUneven.getState();
		toRet[2] = this.threeEast.getState();
		toRet[3] = this.fourHigh.getState();
		toRet[4] = this.fiveDiff.getState();
		toRet[5] = this.sixDiff.getState();
		return toRet;
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
			this.effD = Float.parseFloat(this.effDev.getText());
		}
		catch (NumberFormatException e) {
			try {
				this.effD = Float.parseFloat(this.owne.getGlobalParam("AvgEffDevDay"));
			}
			catch (NumberFormatException e2) {
				this.effD = 0.9f;
			}
			if (this.effD < 0 || this.effD > 3) {
				this.effD = 0.9f;
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
	
	public float getEffortDev() {
		return this.effD;
	}
	
	public int getTimeZone() {
		return this.timeZoneDiff;
	}
		
}
	
	
