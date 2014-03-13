package game.components;

import java.util.*;
import game.components.Site;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;


public class Scenario {
  public SiteInfo[] sites;
	public ModuleInfo[] modules;

  Scenario(SiteInfo[] sites) {
    this.sites = sites;
		this.modules = modules;
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
					System.out.println("Module " + mi.name + " added at site " + site);
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
	public class SiteInfo {
		public String name;
		float[] location;
		int timezone;
		int workers;
	}
	public class ModuleInfo {
		public String name;
		public int hours;
		public String[] sites;
	}
}
