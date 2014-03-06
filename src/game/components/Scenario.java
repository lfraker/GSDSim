package game.components;

import java.util.*;
import game.components.Site;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;


public class Scenario {
  public SiteInfo[] sites;

  Scenario(SiteInfo[] sites) {
    this.sites = sites;
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
    return new ArrayList(sitesMap.values());
  }
	public class SiteInfo {
		public String name;
		float[] location;
		int timezone;
		int workers;
	}
}
