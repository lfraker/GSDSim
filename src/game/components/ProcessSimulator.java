package game.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class ProcessSimulator {
//	private List<ModuleWrapper> allModules = new ArrayList<>();
//	private List<Site> allSites = new ArrayList<>();
	private int currentTime;
	
//	public void addModule(Module m, String moduleSiteName) {
//		this.allModules.add(new ModuleWrapper(m, moduleSiteName));
//		Collections.sort(this.allModules, new ModuleComparator());
//	}
	
//	this is the simulator that runs at the end of the day, where random occurences are calculated
	public void endOfDaySim(List<ModuleWrapper> mods) {
		System.out.println("SIMULATING END OF DAY");
		ModuleWrapper m = mods.get(0);
		for (int i = 0; i < 7; i++) {	
			System.out.println(m.mod.stepEstimates[i]);
		}
		for (ModuleWrapper cModule: mods)
		{
			for (Site cSite: cModule.mod.sites)
			{
				cSite.doWork();
			}
		}
		
	}

	public void updateTime(int newTime)
	{
		this.currentTime = newTime;
	}
	

//  public void addSite(Site site)
//  {
//	  this.allSites.add(site);
//  }


//  public void ProcessSites()
//  {	
//  		/*	Tommy 
//	  	*	Loop through all the sites and update
//		*/
//
//		//Put these somewhere suitable
//	  	int startOfWorkingDay = 9; // 9am
//	  	int endOfWorkingDay = 18; //6pm
//
//		
//		for(Site currentSite : this.allSites)
//		{
//			int localTime = (currentTime + currentSite.getTimezone());
//
//	  		//Check site is currently active - not all sites active at all times - timezones
//	  		if(localTime >= startOfWorkingDay && localTime <= endOfWorkingDay)
//	  		{
//	
//				ArrayList<Module> siteModules = currentSite.getModules();
//	  			for(Module currentMod : siteModules)
//				{
//					currentMod.doWork();
//				}	
//
//	  		}
//
//	  	}
//
//
//  }
	
	
	
//	
//	private class ModuleComparator implements Comparator<ModuleWrapper> {
//	    @Override
//	    public int compare(ModuleWrapper m1, ModuleWrapper m2) {
//	        return m1.getNumber().compareTo(m2.getNumber());
//	    }
//	}
	
}
