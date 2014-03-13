package game.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import com.google.gson.*;


public class ProcessSimulator {
//	private List<ModuleWrapper> allModules = new ArrayList<>();

	private List<Site> allSites = new ArrayList<Site>();
	public long currentTime = 0; //Check out timing
	

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

	public void UpdateTime(long newTime)
	{
		this.currentTime = newTime;
	}

	public void setSiteList(List<Site> sites)
	{
		// Sets the list of sites to be used
		this.allSites = sites;
	}
	

//  public void addSite(Site site)
//  {
//	  this.allSites.add(site);
//  }


  	public void ProcessSites()
  	{	
	  	/*	Tommy 
		*	Loop through all the sites and update
		* 	Run once an hour
		*/

		//Put these somewhere suitable
	  	int startOfWorkingDay = 9; // 9am
	  	int endOfWorkingDay = 18; //6pm

	  	System.out.println("Performing hourly update.");
		
		for(Site currentSite : this.allSites)
		{


			//int localTime = TimeUnit.NANOSECONDS.convert(currentTime, TimeUnit.MINUTES) + currentSite.getTimezone();
			boolean behind = false;

			//System.out.println("LT: " +localTime + ".");

	  		//Check site is currently active - not all sites active at all times - timezones
	  		//if(localTime >= startOfWorkingDay && localTime <= endOfWorkingDay)
	  		//{
	
				System.out.println("Performing work at site: " + currentSite.getName() + ".");

				ArrayList<Module> siteModules = currentSite.getModules();
	  			for(Module currentMod : siteModules)
				{
					if(currentMod.isComplete())
					{
						//Skip complete modules or do something with them here..
						continue;
					}

					System.out.println("Performing work on module: " + currentMod.getName() + ".");
					currentMod.doWork();
					System.out.println("Completion level: " + (currentMod.getCompletionLevel() * 100));

					if (!currentMod.IsOnSchedule()) 
					{
						behind = true;
					}

				}	
				if (behind)
				{
					if (currentSite.mapMarker.status != MapMarkerDot.Status.BEHIND)
					{
						System.out.println("Module has missed completion deadline!");
					}
					currentSite.mapMarker.setBehind();
				}
				else
				{
					if (currentSite.mapMarker.status == MapMarkerDot.Status.BEHIND)
					{
						System.out.println("Module has somehow caught up!");
					}
					currentSite.mapMarker.setOnTime();
				}
			/*
	  		}
	  		else
	  		{
	  			System.out.println("Site: " + currentSite.getName() + " currently closed.");
	  		}*/

	  	}

  	}

  	public void RemoveSites()
  	{
  		this.allSites.clear();
  	}
	
	public void AddSite(Site site)
	{
	  	this.allSites.add(site);
	}
	

	public List<Site> GetSites() {
		return this.allSites;
	}
		

	public void SaveState()
	{
		//Creates a save file

		/*
		Gson gson = new Gson();
		String json = gson.toJson(this.allSites);
		System.out.println(json);
		*/
	}

	public void LoadState(String filename)
	{

	}
	
//	
//	private class ModuleComparator implements Comparator<ModuleWrapper> {
//	    @Override
//	    public int compare(ModuleWrapper m1, ModuleWrapper m2) {
//	        return m1.getNumber().compareTo(m2.getNumber());
//	    }
//	}
	
}
