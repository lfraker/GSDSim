package game.components;

import game.backend.VectorI;
import game.gamePanes.SetModulesPane;
import game.gamePanes.SettingsPane;
import game.org.openstreetmap.gui.jmapviewer.JMapViewer;
import game.swingFramework.FrontEndPane;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.util.List;

public class SiteInfoPane extends JFrame {
	List<Site> sites;
	JPanel modules;
	public SiteInfoPane(String name) {
		this.setTitle(name);
		this.setMinimumSize(new Dimension(300, 300));
		this.setPreferredSize(new Dimension(300, 300));
		sites = game.swingFramework.FrontEndPane.processSimulator.GetSites();
		modules = new JPanel();
		modules.setLayout(new GridLayout(0,1));
		for (Site s : sites) {
			for (Module m : s.modules) {
				if (name.split(" Site Info")[0].equals(s.siteName)) {
					modules.add(new JLabel("Name: " + m.getName()));
					modules.add(new JLabel("Work done: " + m.workDone() + " hours"));
					modules.add(new JLabel("Sections completed: " + m.sectionsCompleted()));
				}
			}
		}
		this.add(modules);		
		this.pack();
		this.setVisible(true);
	}

	
	
	
	
}
