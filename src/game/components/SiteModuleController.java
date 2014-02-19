package game.components;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SiteModuleController {
	ProcessSimulator pSim;
	private List<Site> allSites = new ArrayList<>();
	
	public SiteModuleController() {
		this.pSim = new ProcessSimulator();
	}
	
	public void endDay() {
		
	}
	
	public List<Site> getSites() {
		return this.allSites;
	}
	
	
	
//	public void addModule(Module m, String moduleSiteName) {
//		this.allModules.add(new ModuleWrapper(m, moduleSiteName));
//		Collections.sort(this.allModules, new ModuleComparator());
//	}
	
	public void addSite(Site site)
	{
	  this.allSites.add(site);
	}
	
	
	
	
	
	
	private class ModuleComparator implements Comparator<ModuleWrapper> {
	    @Override
	    public int compare(ModuleWrapper m1, ModuleWrapper m2) {
	        return m1.getNumber().compareTo(m2.getNumber());
	    }
	}
	
	
}