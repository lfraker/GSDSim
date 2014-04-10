package game.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import game.swingFramework.FrontEndPane;

import com.google.gson.*;

import java.util.Random;

import javax.swing.JOptionPane;

public class ProcessSimulator {

	private List<Site> allSites = new ArrayList<Site>();
	public long currentTime = 0;
	public long dayLength = 24;
	public int hoursSinceStartOfDay = 0;
	public FrontEndPane fPane;
	private int startOfWorkingDay = 9; // 9am
	private int endOfWorkingDay = 18; //6pm
	private Difficulty difficulty = FrontEndPane.difficulty;

	public double interventionEffects = 0;

	public boolean HasFailed = false;

	private Random rnd = new Random();


	public double GetInterventionEffects()
	{
		return this.interventionEffects;
	}

	public void SetInterventionEffects(double interventionEff)
	{
		this.interventionEffects = interventionEff;
	}

	//This is the simulator that runs at the end of the day, where random occurences are calculated
	public void endOfDaySim() 
	{
		//System.out.println("SIMULATING END OF DAY");

		for(int i = 0; i < this.allSites.size(); i++)
		{
			Site currentSite = this.allSites.get(i);
			
			//Calculate costs from each site and subtracts it from the balance.

			float siteCostPerDay = (float)(currentSite.GetCostDeveloperDay() * currentSite.GetNumberWorkers());

			//Check if a failure should occur

			//Adjust depending on difficulty

			double difficultyModifier;

			if(this.difficulty == Difficulty.EASY)
			{
				difficultyModifier = 0.7;
			}
			else
			{
				difficultyModifier = 1;
			}

			double adjustedInterventionEffects = (this.interventionEffects / (1 + this.interventionEffects));

			float failProb = (float)((difficultyModifier * currentSite.GetFailureProbability()) * (1 - adjustedInterventionEffects));
			float r = this.rnd.nextFloat();
			

			ArrayList currentSiteModules = currentSite.getModules();

			float totalHoursEffortRequired = 0;

			for(int k = 0; k < currentSiteModules.size(); k++)
			{
				Module currentMod = (Module)currentSiteModules.get(k);
				totalHoursEffortRequired += currentMod.totalWorkRequired();
			}

			float averageHoursPerModule = (totalHoursEffortRequired / currentSiteModules.size());

			failProb = (float)(failProb * 0.1);
			

			if(r <= failProb && currentSiteModules.size() > 0)
			{
				//Problem occurs

				//Choose a random module to experience a problem
				Module problemMod = (Module)currentSiteModules.get(this.rnd.nextInt(currentSiteModules.size()));

				//Current task in module
				int currentTask = problemMod.sectionsCompleted();

				/*
				*	Problems
				*
				*	a site falls behind more than 25% on a task 	Design 	Repeat Design 	15%
				*	a site falls behind more than 25% on a task 	Implementation 	Repeat Implementation 	15%
				*	module fails unit tests 						Unit Test 	Go back to beginning of Implementation task 	25%
				*	module fails to integrate properly 				Integration 	Go back to beginning of Implementation task 	40%
				*	module fails system tests 						System test 	Go back to beginning of Integration task 	55%
				*	module fails to deploy correctly 				Deployment 	Go back to beginning of System test task 	70%
				*	module or product fails acceptance tests (fails to meet real requirements) 	Acceptance test 	Go back to beginning of Design task 	100%
				*	team reports progress inaccurately, according to cultural norms 	any 	If site is located in Russia or Asia, status will be green regardless 	n/a
				*/

				switch(currentTask)
				{
					case 0 : 	this.HasFailed = problemMod.RestartFromStage(0); //Repeat Design
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting Design.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
					case 1 : 	this.HasFailed = problemMod.RestartFromStage(1); //Repeat From Implementation
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting Implementation.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
					case 2 : 	this.HasFailed = problemMod.RestartFromStage(1); //Repeat From Implementation
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting Implementation.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
					case 3 : 	this.HasFailed = problemMod.RestartFromStage(1); //Repeat From Implementation
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting Implementation.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
					case 4 : 	this.HasFailed = problemMod.RestartFromStage(3); //Repeat From Integration
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting Integration.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
					case 5 : 	this.HasFailed = problemMod.RestartFromStage(4); //Repeat From System Test
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting System Test.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
					case 6 : 	this.HasFailed = problemMod.RestartFromStage(0); //Repeat From Design
								System.out.println("Problem with module " + problemMod.getName() + ". Restarting Design.");
								if (this.fPane.difficulty == Difficulty.EASY) {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to pause the game and enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								else {
									JOptionPane.showMessageDialog(this.fPane.getWindow(), "Problem with module " + problemMod.getName() + ". Restarting Design.\n" +
											" It is recommended to enact an intervention, \notherwise if the problem repeats three times, the site will fail" +
											" and you will lose");
								}
								break;
				}

				if(this.HasFailed)
				{
					currentSite.mapMarker.setFailed();
				}
				
				

			}


		}



	}
	public void setFP(FrontEndPane fp) {
		this.fPane = fp;
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

			System.out.println("Added site " + site.getName() + " with distance " + site.GetGlobalDistance());


		}

	  	this.allSites.add(site);
	}
	

	public List<Site> GetSites() {
		return this.allSites;
	}
		

	public boolean SaveState(String savefile)
	{
		//Creates a save file

		GameState gs = new GameState(allSites);
		return gs.SaveState(savefile);
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
	
	
}
