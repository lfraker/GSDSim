package game.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class Scenarios {
	public Scenario[] scenarios;
	Scenarios(Scenario[] scenarios) {
		this.scenarios = scenarios;
	}
	public List<Site> process() {
		return scenarios[0].process();
	}
	public void print() {
		scenarios[0].print();
	}
}
