package game.gamePanes;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JButton;
import game.components.Scenarios;
import java.util.List;
import game.components.Site;
import java.awt.Dimension;
import game.components.ScenarioLoader;
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












class ChooseDefaultPane extends JFrame {
	JRadioButton rB;
	ButtonGroup bG;
	JPanel okPanel;
	JButton ok;
	JPanel scenarioPanel;
	String cities;
	String workers;
	String[] cities1;
	Scenarios s;
	List<Site> sites;
	public ChooseDefaultPane() {
		bG = new ButtonGroup();
		this.setSize(new Dimension(800, 520));
		this.setLocationRelativeTo(null);
		s = ScenarioLoader.load("./gameFiles/scenario1.json");
		this.setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		JPanel scenariosContainer = new JPanel();
		scenariosContainer.setLayout(new GridLayout(0, s.scenarios.length));
		scenariosContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		cities1 = new String[s.scenarios.length];
		for (int i = 0; i < s.scenarios.length; i++) {
			scenarioPanel = new JPanel();
			scenarioPanel.setLayout(new BoxLayout(scenarioPanel,BoxLayout.Y_AXIS));
			for (int j = 0; j < s.scenarios[i].sites.length; j++) {
				if (j == 0) {
					workers = s.scenarios[i].sites[j].workers;
					cities = s.scenarios[i].sites[j].name;
				} else {
					cities += ", " + s.scenarios[i].sites[j].name;
					workers += ", " + s.scenarios[i].sites[j].workers;
				}
			}
			cities1[i] = cities;
			rB = new JRadioButton("Scenario " + (i+1));
			scenarioPanel.add(rB);
			scenarioPanel.add(new JLabel("Sites: " + cities));
			scenarioPanel.add(new JLabel("Workers: " + workers));
			bG.add(rB);
			scenarioPanel.add(Box.createRigidArea(new Dimension(0, 30)));
			for (int m = 0; m < s.scenarios[i].modules.length; m++) {
				scenarioPanel.add(new JLabel("Name: " + s.scenarios[i].modules[m].name));
				scenarioPanel.add(new JLabel("Hours: " + s.scenarios[i].modules[m].hours));
				scenarioPanel.add(new JLabel("Site: " + s.scenarios[i].modules[m].sites[0]));
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

		this.pack();
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Enumeration<AbstractButton> buttons = bG.getElements(); buttons.hasMoreElements();) {
					AbstractButton button = buttons.nextElement();

					if (button.isSelected()) {
						for (int k = 0; k < cities1.length; k++) {
							if (button.getText().equals("Scenario " + (k+1))) {
								sites = s.scenarios[k].process();
								game.swingFramework.FrontEndPane.modules.clearSites();
								game.swingFramework.FrontEndPane.siteStatus.removeAllMapMarkers();
								game.swingFramework.FrontEndPane.processSimulator.RemoveSites();

								for (int l = 0; l < sites.size(); l++) {
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
