package game.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ProcessSimulator {
	private List<ModuleWrapper> allModules = new ArrayList<>();
	private List<Site> allSites = new ArrayList<>();
	
	public void addModule(Module m, String moduleSiteName) {
		this.allModules.add(new ModuleWrapper(m, moduleSiteName));
		Collections.sort(this.allModules, new ModuleComparator());
	}
	
	//this is the simulator that runs at the end of the day, where random occurences are calculated
	public void endOfDaySim() {
		System.out.println("SIMULATING END OF DAY");
		ModuleWrapper m = this.allModules.get(0);
		for (int i = 0; i < 7; i++) {	
			System.out.println(m.mod.stepEstimates[i]);
		}
		for (ModuleWrapper cModule: this.allModules)
		{
			for (Site cSite: cModule.mod.sites)
			{
				cSite.doWork();
			}
		}
		
	}
	

  public void addSite(Site site)
  {
	  this.allSites.add(site);
  }


  public void ProcessSites()
  {	//Tommy - pseudocode
	  //Loop through all the sites and update
	  /*
		 for(Site currentSite : this.allSites)
		 {
	  //Check site is currently active
	  if((currentTime + Site->timezone) > startOfWorkingDay 
	  && (currentTime + Site->timezone) <= endOfWorkingDay )
	  {

	  for(Module currentMod : currentSite.getModules())
	  {

	  }


	  }

	  }*/


  }
	
	
	
	
	private class ModuleComparator implements Comparator<ModuleWrapper> {
	    @Override
	    public int compare(ModuleWrapper m1, ModuleWrapper m2) {
	        return m1.getNumber().compareTo(m2.getNumber());
	    }
	}
	
}
