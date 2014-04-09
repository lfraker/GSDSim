package game.components;

import game.swingFramework.FrontEndPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;


public class FinalReport extends JDialog {
	public JTextArea report;
	public JTextArea fileName;
	public JDialog curr;
	
	public FinalReport (String temp, FrontEndPane f) {
		super(f.getWindow(), "Final Game Report");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLayout(new BorderLayout());
		curr = this;
		this.setMinimumSize(new Dimension(500, 550));
		this.setPreferredSize(new Dimension(600, 650));
		this.setMaximumSize(new Dimension(700, 750));
		JPanel bottomPan = new JPanel();
		JButton save = new JButton("Save Report");
		this.fileName = new JTextArea();
		this.report = new JTextArea();
		this.report.setText(temp);
		this.report.setEditable(false);
		this.fileName.setBorder(new LineBorder(Color.BLACK));

		JScrollPane repScroll = new JScrollPane(report);
		repScroll.setBorder(new LineBorder(Color.BLACK));


		
		save.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String fName = (fileName.getText()).trim();
				if (fName.length() == 0) {
					JOptionPane.showMessageDialog(curr, "Please enter a file name.");
					return;
				}
				File dir = new File("finalReports");
				 
				if (!dir.exists()) {
					dir.mkdir();
				}
				File file = new File("./finalReports/" + fName + ".txt");
				if (!file.exists()) {
					try {
						file.createNewFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				try (BufferedWriter buffWrite = new BufferedWriter(new FileWriter(file, false))){
						buffWrite.write(report.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
				
				JOptionPane.showMessageDialog(curr, "File: " + fName + ".txt was saved in finalReports directory");
				curr.dispose();
				curr = null;
				System.exit(0);
				
			}
			
		});
		
		bottomPan.setLayout(new GridLayout(0,2));
		bottomPan.add(this.fileName);
		bottomPan.add(save);
		this.add(bottomPan, BorderLayout.NORTH);
		this.add(repScroll, BorderLayout.CENTER);

		
		this.pack();
		this.setVisible(true);

	}
	
	
	
	
	
}