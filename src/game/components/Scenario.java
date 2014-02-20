package game.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

public class Scenario {
	SiteInfo[] sites;
	ModuleInfo[] modules;

	Scenario(SiteInfo[] sites, ModuleInfo[] modules) {
		this.sites = sites;
		this.modules = modules;
	}

	class SiteInfo {
		String name;
		float[] location;
		int timezone;
		int workers;
	}

	class ModuleInfo {
		String name;
		int hours;
		String[] sites;
	}

	public void print() {
		System.out.println("" + sites.length + " sites loaded");
	}

	// Associates modules to their sites, and returns finished list of sites
	public List<Site> process() {
		HashMap<String,Site> sitesMap = new HashMap<>();
		List<Module> rModules = new ArrayList<>();

		for (SiteInfo si: sites) {
			if (si.location.length != 2) {
				System.err.println("Invalid location for site " + si.name);
				continue;
			}
			MapMarkerDot pos = new MapMarkerDot(null, si.name, si.location[0], si.location[1]);
			Site s = new Site(si.name, si.workers, pos, si.timezone);
			sitesMap.put(si.name, s);
		}

		for (ModuleInfo mi: modules) {
			List<Site> moduleSites = new ArrayList<>();
			for (String site: mi.sites) {
				Site s = sitesMap.get(site);
				if (s != null) {
					moduleSites.add(s);
				}
			}
			Module m = new Module(mi.hours, mi.name, moduleSites);
			for (Site site: moduleSites) {
				site.addModule(m);
			}
			rModules.add(m);
		}
		return new ArrayList(sitesMap.values());
	}

}
