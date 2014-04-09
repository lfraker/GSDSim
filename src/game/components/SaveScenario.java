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

public class SaveScenario extends JDialog {
	private JTextField fileName;
	private String fName;
	private FrontEndPane fP;
	
	public SaveScenario(Frame owner, String title, FrontEndPane f) {
		super(owner, title, Dialog.ModalityType.DOCUMENT_MODAL);
		this.setMinimumSize(new Dimension(175, 100));
		this.setPreferredSize(new Dimension(200, 100));
		this.setMaximumSize(new Dimension(300, 100));
		JPanel btnPanel = new JPanel();
		JButton okBtn = new JButton("Save");
		JButton noBtn = new JButton("Cancel");
		btnPanel.add(okBtn);
		this.fP = f;
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
		this.fileName = new JTextField();
		inputPanel.add(new JLabel("File Name:"));
		inputPanel.add(this.fileName);
		getContentPane().add(inputPanel, BorderLayout.CENTER);

		getContentPane().add(btnPanel, BorderLayout.SOUTH);
		pack();
		
	}
	private void noButton() {
		this.setVisible(false);
		this.dispose();
	}
	
	private void okButton() {
		this.fName = this.fileName.getText();
		if (this.fName.length() <= 0) {
			this.fileName.setText("CustomScenario1");
			return;
		}
		else {
			this.fP.saveCustomGameScen(this.fName);
		}
		this.setVisible(false);
		this.dispose();
	}
	
}
	
	
