package game.gamePanes;

import javax.swing.JFrame;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.*;
import game.components.Site;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import game.gamePanes.SettingsPane;

import game.components.*;
import game.org.openstreetmap.gui.jmapviewer.Coordinate;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

//UNTESTED
public class ChooseDefaultPane extends JFrame {
	JRadioButton rB;
	ButtonGroup bG;
	JPanel okPanel;
	JButton ok;
	JPanel scenarioPanel;
	String cities;
	String workers;
	String[] cities1;
	List<Site> sites;
	List<List<Site>> scenarios;

	public ChooseDefaultPane() {
		bG = new ButtonGroup();
		this.setSize(new Dimension(800, 520));
		this.setLocationRelativeTo(null);

		GameState gs = new GameState(sites);
		scenarios = gs.LoadScenarios();


		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JPanel scenariosContainer = new JPanel();
		scenariosContainer.setLayout(new GridLayout(0, scenarios.size()));
		scenariosContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		cities1 = new String[scenarios.size()];

		for (int i = 0; i < scenarios.size(); i++) 
		{
			scenarioPanel = new JPanel();
			scenarioPanel.setLayout(new BoxLayout(scenarioPanel,BoxLayout.Y_AXIS));

			List<Site> currentSites = scenarios.get(i);

			for (int j = 0; j < currentSites.size(); j++) 
			{
				if (j == 0) 
				{
					workers = Integer.toString(currentSites.get(j).GetNumberWorkers());
					cities = currentSites.get(j).getName();
				} 
				else 
				{
					cities += ", " + currentSites.get(j).getName();
					workers += ", " + currentSites.get(j).GetNumberWorkers();
				}
			}

			cities1[i] = cities;
			rB = new JRadioButton("Scenario " + (i+1));
			scenarioPanel.add(rB);
			scenarioPanel.add(new JLabel("Sites: " + cities));
			scenarioPanel.add(new JLabel("Workers: " + workers));
			bG.add(rB);
			scenarioPanel.add(Box.createRigidArea(new Dimension(0, 30)));

			ArrayList<Module> modules = new ArrayList<Module>();

			for(Site s : currentSites)
			{

				ArrayList<Module>sitemodules = s.getModules();
				for(Module m : sitemodules)
				{
					modules.add(m);
				}
			}	

			for (int m = 0; m < modules.size(); m++) 
			{
				scenarioPanel.add(new JLabel("Name: " + modules.get(m).getName()));
				scenarioPanel.add(new JLabel("Hours: " + modules.get(m).getEstimate()));
				//scenarioPanel.add(new JLabel("Site: " + scenarios.get(i).modules[m].sites[0]));
				scenarioPanel.add(Box.createRigidArea(new Dimension(0, 20)));
			}

			scenariosContainer.add(scenarioPanel);
		}


		this.add(scenariosContainer);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.X_AXIS));
		buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		ok = new JButton("Start Sim");
		buttonPane.add(Box.createHorizontalGlue());
		buttonPane.add(ok);
		this.add(buttonPane);
		this.setVisible(true);
		this.pack();
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Enumeration<AbstractButton> buttons = bG.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();

					if (button.isSelected()) {
						for (int k = 0; k < scenarios.size(); k++) 
						{
							if (button.getText().equals("Scenario " + (k+1))) 
							{		
								sites = scenarios.get(k);
								game.swingFramework.FrontEndPane.modules.clearSites();
								game.swingFramework.FrontEndPane.siteStatus.removeAllMapMarkers();
								game.swingFramework.FrontEndPane.processSimulator.RemoveSites();

								for (int l = 0; l < sites.size(); l++) 
								{
									double siteCoords[] = sites.get(l).GetCoordinates();
									String sitename = sites.get(l).getName();

									sites.get(l).setMarker(new MapMarkerDot(null, sitename , siteCoords[0], siteCoords[1]));
									System.out.println(sites.get(l).getMarker().toString());
			
									game.swingFramework.FrontEndPane.addSiteToCombo(sites.get(l));
									game.swingFramework.FrontEndPane.processSimulator.AddSite(sites.get(l));
									game.swingFramework.FrontEndPane.siteStatus.addMapMarker(sites.get(l).getMarker());
								}

							}
						}
					}

				}
				dispose();
				game.gamePanes.Pane.parentComp.enableSites();
				game.gamePanes.Pane.parentComp.startLoadedSim();
				game.gamePanes.Pane.parentComp.loadedSim();
			}
		});

	}
}
