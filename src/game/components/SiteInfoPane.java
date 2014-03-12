package game.components;

import game.backend.VectorI;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.swingFramework.FrontEndPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JWindow;
import javax.swing.Timer;

public class SiteInfoPane extends JFrame {
	
	public SiteInfoPane(String name) {
		this.setTitle(name);
		this.setMinimumSize(new Dimension(300, 300));
		this.setPreferredSize(new Dimension(300, 300));
		
	
		
		this.pack();
		this.setVisible(true);
	}

	
	
	
	
}