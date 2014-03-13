package game.components;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class SiteModuleController {
	ProcessSimulator pSim;
	public List<Site> allSites = new ArrayList<>();
	
	public SiteModuleController() {
		this.pSim = new ProcessSimulator();

		//Pass the site list to process simulator
		this.pSim.setSiteList(this.allSites);
	}

	public ProcessSimulator getProcessSimulator() {
		return pSim;
	}

	public void hourlyUpdate()
	{
		this.pSim.ProcessSites();
	}
	
	public void endDay() {
		for(Site s: allSites) {
			//this.pSim.endOfDaySim(s.getModules());
		}
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
	
	public void removeSites()
	{
		this.allSites.clear();
	}
	
	
	
	
	private class ModuleComparator implements Comparator<ModuleWrapper> {
	    @Override
	    public int compare(ModuleWrapper m1, ModuleWrapper m2) {
	        return m1.getNumber().compareTo(m2.getNumber());
	    }
	}
	
	
}
