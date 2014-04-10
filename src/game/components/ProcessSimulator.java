package game.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import com.google.gson.*;

import java.util.Random;

public class ProcessSimulator {

	private List<Site> allSites = new ArrayList<Site>();
	public long currentTime = 0;
	public long dayLength = 24;
	public int hoursSinceStartOfDay = 0;

	private int startOfWorkingDay = 9; // 9am
	private int endOfWorkingDay = 18; //6pm

	private Random rnd = new Random();

	//This is the simulator that runs at the end of the day, where random occurences are calculated
	public void endOfDaySim() 
	{
		System.out.println("SIMULATING END OF DAY");

		for(int i = 0; i < this.allSites.size(); i++)
		{
			Site currentSite = this.allSites.get(i);
			
			//Calculate costs from each site and subtracts it from the balance.

			float siteCostPerDay = (float)(currentSite.GetCostDeveloperDay() * currentSite.GetNumberWorkers());


			//float siteCostPerDay = (float)(currentSite.GetCostDeveloperDay() * currentSite.GetNumberWorkers());



			//Check if a failure should occur

			float failProb = currentSite.GetFailureProbability();

			float r = this.rnd.nextFloat();

			System.out.println("Prob Sim:" + r + " : " + failProb );



			if(r <= failProb)
			{
				//Problem occurs

				System.out.println("Problem");


			}


		}



	}

	public void UpdateTime(long newTime)
	{
		this.currentTime = newTime;
	}

	public void SetDayLength(long d)
	{
		if (d == 0) {
		   return;
		}
		this.dayLength = d;
	}

	public void setSiteList(List<Site> sites)
	{
		// Sets the list of sites to be used
		this.allSites = sites;
	}

  	public void ProcessSites()
  	{	
	  	/*	Tommy 
		*	Loop through all the sites and update
		* 	Run once an hour
		*/
		

		//this.SaveState("tommy.json"); //Testing the save function

		//Put these somewhere suitable
	  

	  	ArrayList<Module> ftsModulesToMove = new ArrayList<Module>();

	  	//Used to determine the most suitable site to move a 'Follow The Sun' module to.
	  	int ftsMaxOpenHours = 0;
	  	int ftsMinHoursTilOpen = 24;
	  	Site ftsSiteToMoveTo = null;

	  	System.out.println("Performing hourly update.");
		
		for(int i = 0; i < this.allSites.size(); i++)
		{
			Site currentSite = this.allSites.get(i);
			boolean behind = false;
			long hour = (long)(this.dayLength / 24);
			long localTime = ( ( (this.currentTime / hour) + currentSite.getTimezone()) % 24);

			float effortPerDeveloperDay = currentSite.GetEffortPerDeveloperDay();




			//Determine best site to move a follow the sun module to.
			if(localTime >= this.startOfWorkingDay && localTime < this.endOfWorkingDay)
			{
				//If this site is open
				if((this.endOfWorkingDay - localTime) > 1 && (this.endOfWorkingDay - localTime) > ftsMaxOpenHours)
				{
					ftsMaxOpenHours = (int)(this.endOfWorkingDay - localTime);
					ftsSiteToMoveTo = this.allSites.get(i);
				}
			}
			else if(ftsMaxOpenHours == 0)
			{
				int hoursTilOpen;

				if(localTime >= this.endOfWorkingDay)
				{
					//If the site is closed for the day..
					hoursTilOpen = (int)(this.startOfWorkingDay + (24-localTime));
				}
				else
				{
					//If the site is not yet open..
					hoursTilOpen = (int)(this.startOfWorkingDay - localTime);
				}

				if(hoursTilOpen < ftsMinHoursTilOpen)
				{
					ftsSiteToMoveTo = this.allSites.get(i);
					ftsMinHoursTilOpen = hoursTilOpen;
				}
			}
			

			//Check site is currently active - not all sites active at all times due to timezones
	  		if(localTime >= this.startOfWorkingDay && localTime < this.endOfWorkingDay)
	  		{
				ArrayList<Module> siteModules = currentSite.getModules();

				if(siteModules.size() > 0)
				{
					System.out.println("Performing work at site: " + currentSite.getName() + ".");
				}

	  			for(int k = 0; k < siteModules.size(); k++)
				{
					Module currentMod = siteModules.get(k);

					currentMod.setPerformanceModifier(effortPerDeveloperDay);

					if(currentMod.isComplete())
					{
						//Skip complete modules or do something with them here..
						continue;
					}

					System.out.println("Performing work on module: " + currentMod.getName() + ".");
					currentMod.doWork();
					System.out.println("Completion level: " + (currentMod.getCompletionLevel() * 100));

					if(currentMod.getDevelopmentMethod() == DevelopmentMethod.FOLLOWTHESUN && ((this.endOfWorkingDay - localTime) == 1))
	  				{
	  					//Check if there are any follow the sun modules to be moved
	  					ftsModulesToMove.add(currentMod);
	  					currentSite.modules.remove(currentMod);
	  					System.out.println("Preparing module '" + currentMod.getName()  + "' for handover.");
	  				}
	  				else if (!currentMod.IsOnSchedule()) 
					{
						//Only applies if the module isn't about to be moved anyway..
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
						System.out.println("Module has caught up!");
					}
					currentSite.mapMarker.setOnTime();
				}
			
	  		}
	  		else
	  		{
	  			//Site is closed
	  			//System.out.println("Site: " + currentSite.getName() + " currently closed.");
	  		}
	  	}

	  	//Check if there are any fts modules to be handed over..
	  	for(int i = 0; i < ftsModulesToMove.size(); i++)
	  	{
	  		Module modToBeMoved = ftsModulesToMove.get(i);

	  		if(ftsSiteToMoveTo == null)
	  		{
	  			//System.out.println("Couldn't find a suitable place for fts module.");
	  		}
	  		else
	  		{
	  			ftsModulesToMove.remove(modToBeMoved);
	  			ftsSiteToMoveTo.addModule(modToBeMoved);
	  			System.out.println("Handed over module '" + modToBeMoved.getName()  + "' to site '" + ftsSiteToMoveTo.getName() + "'.");
	  		}
	  		
	  	}

	  	this.hoursSinceStartOfDay++;

	  	if(this.hoursSinceStartOfDay == 24)
	  	{
	  		this.endOfDaySim();
	  		this.hoursSinceStartOfDay = 0;
	  	}

	  	NominalScheduleCalc();
  	}

  	public void RemoveSites()
  	{
  		this.allSites.clear();
  	}
	
	public void AddSite(Site site)
	{
		if(this.allSites.size() > 0)
		{
			Site homeSite = this.allSites.get(0);
			site.SetGlobalDistance(homeSite.CalcGlobalDistanceToSite(site));

			System.out.println("Added site " + homeSite.getName() + " with distance " + site.GetGlobalDistance() + "km");


		}

	  	this.allSites.add(site);
	}
	

	public List<Site> GetSites() {
		return this.allSites;
	}
		

	public void SaveState(String savefile)
	{
		//Creates a save file

		GameState gs = new GameState(allSites);
		gs.SaveState(savefile);
	}

	public void LoadState(String filename)
	{

	}


	










	

	public long NominalScheduleCalc()
	{
		//Returns total amount of work left over all modules

		long totalEffort = 0;

		for(Site currentSite : this.allSites)
		{
			ArrayList<Module> siteModules = currentSite.getModules();
	  		
	  		for(Module currentMod : siteModules)
			{
				if(currentMod.isComplete())
				{
					//Skip
					continue;
				}

				totalEffort += (currentMod.totalWorkRequired() - currentMod.workDone());
			}
		}

	//	System.out.println("TE: " + totalEffort);
		return totalEffort;
	}
	
//	
//	private class ModuleComparator implements Comparator<ModuleWrapper> {
//	    @Override
//	    public int compare(ModuleWrapper m1, ModuleWrapper m2) {
//	        return m1.getNumber().compareTo(m2.getNumber());
//	    }
//	}
	
}
