package game.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//this is the module class, it contains arrays of step estimates, a list of
//all the sites working on the modules, it's current phase stage, etc.
public class Module {
	List <Site> sites = new ArrayList<Site>();
	String modName;
	Random numberGen = new Random();
	float [] stepEstimates = new float [7];
	float [] origStepEstimates = new float [7];
	float [] workDonePerSection = new float [7];

	DevelopmentMethod devMethod;
	int numWorkers;
	//Overall performance level - may be adjusted to simulate poor performance or exceptional performance
	float performanceLevel = 1;

	float totalEstimate = 0.0f;
	public long origEstimate;
	int currentStage;
	boolean complete = false;
	
	public Module(long estimate, String name, List<Site> allSites) 
	{
		this.currentStage = 0;
		this.origEstimate = estimate;
		this.sites = allSites;
		this.modName = name;
		this.origStepEstimates[0] = (estimate * 0.15f);
		this.origStepEstimates[1] = (estimate * 0.15f);
		this.origStepEstimates[2] = (estimate * 0.10f);
		this.origStepEstimates[3] = (estimate * 0.15f);
		this.origStepEstimates[4] = (estimate * 0.15f);
		this.origStepEstimates[5] = (estimate * 0.15f);
		this.origStepEstimates[6] = (estimate * 0.15f);

		for (int i = 0; i < 7; i++) 
		{
			this.stepEstimates[i] = addSub(this.numberGen.nextInt(2), this.origStepEstimates[i]);
			this.totalEstimate += this.stepEstimates[i]; 
		}
		
		//Development type defaults to agile
		this.devMethod = DevelopmentMethod.AGILE;
		this.numWorkers = 1;
	}
	
	public List<Site> getSites() {
		return this.sites;
	}
	
	public String getName() {
		return this.modName;
	}
	
	public void addSite(Site newSite) {
		this.sites.add(newSite);
	}
	
	public float addSub(int addOrSub, float val) {
		float valToChange = 0.0f;
		int numChange = this.numberGen.nextInt(26);
		float nVal = (numChange/100.0f);
		switch (addOrSub) {
			case 0:	valToChange = (val * nVal);
				break;
			case 1: valToChange = (val * -nVal);
				break;
		}
		return (val + valToChange);
	}
	
	public float getEstimate() {
		return this.origEstimate;
	}

	public void doWork() {
		/* T - Computes work done based on number of workers, development methodology, etc */

		//Example... 
		float methodologyModifier = 1; //Can be used to dictate efficiency based on methodology
		float workPoints = this.numWorkers * this.performanceLevel; //An hour's work per worker for example..
		float workLeftToDo;

		if(this.devMethod == DevelopmentMethod.WATERFALL) 
		{
			/* Finish one phase before moving to the next */

			methodologyModifier = 1;
			workPoints *= methodologyModifier;

			if(this.workRemaining() <= workPoints) 
			{
				this.complete();
			} 
			else 
			{
				for(int currentStep = 0; currentStep < 7; currentStep++) 
				{
					workLeftToDo = stepEstimates[currentStep] - workDonePerSection[currentStep];

					if(workDonePerSection[currentStep] <  stepEstimates[currentStep]) 
					{
						//If the step hasn't been completed yet...
						workLeftToDo = (stepEstimates[currentStep] - workDonePerSection[currentStep]);

						if(workLeftToDo <= workPoints) 
						{
							//If the work done is enough to finish the phase
							workDonePerSection[currentStep] = stepEstimates[currentStep]; //Set section complete
							workPoints -= workLeftToDo;
						} 
						else 
						{
							workDonePerSection[currentStep] += workPoints;
							break;
						}

						if(workPoints == 0) 
						{
							break;
						}
					}
				}

			}

		} 
		else if(this.devMethod == DevelopmentMethod.FOLLOWTHESUN) 
		{
			/* 
			*	Same as waterfall except number of workers may change on a per site basis.
			*	
			*	Also productivity in the first and last hour of each day will be lower due to handover.
			*	Will have to determine the best way to determine time..
			*
			*	Overheads will probably need to be adjusted to more realistic values
			*/

			//if(firstHourOfBusiness || lastHourOfBusiness){
			//	methodologyModifier = 0.85; //Overhead because of handover meetings for example
			//}else{
				methodologyModifier = 0.95f; //5% penalty for development style
			//}

			workPoints *= methodologyModifier;

			if(this.workRemaining() <= workPoints) 
			{
				this.complete();
			} 
			else 
			{

				for(int currentStep = 0; currentStep < 7; currentStep++) 
				{
					workLeftToDo = stepEstimates[currentStep] - workDonePerSection[currentStep];

					if(workDonePerSection[currentStep] <  stepEstimates[currentStep]) 
					{
						//If the step hasn't been completed yet...
						workLeftToDo = (stepEstimates[currentStep] - workDonePerSection[currentStep]);

						if(workLeftToDo <= workPoints) 
						{
							workDonePerSection[currentStep] = stepEstimates[currentStep]; //Set section complete
							workPoints -= workLeftToDo;
						} 
						else 
						{
							workDonePerSection[currentStep] += workPoints;
							break;
						}

						if(workPoints == 0) 
						{
							break;
						}
					}
				}

			}
		} 
		else if(this.devMethod == DevelopmentMethod.AGILE) 
		{
			/* Each part is worked on simultaneously */

			methodologyModifier = 1;
			workPoints *= methodologyModifier;
			float workLeftInModule = this.workRemaining();

			float totalWork = workPoints;

			if(workLeftInModule <= workPoints) 
			{
				this.complete();
			} 
			else 
			{
				//Average number of man hours available for each section
				float manHoursAvailableToSection = (workPoints / (7 - this.sectionsCompleted()));

				while(workPoints > 0)
				{
					for(int currentStep = 0; currentStep < 7; currentStep++) 
					{
						workLeftToDo = (stepEstimates[currentStep] - workDonePerSection[currentStep]);

						if(workLeftToDo > 0) 
						{

							//If the step hasn't been completed yet...
							//Recalculated per section as we may have remaining work points from previous sections

							if(workLeftToDo < manHoursAvailableToSection)
							{
								//Section complete.. Reallocate work to next section

								//System.out.println("WP:" + workPoints);

								//System.out.println(	"Allocated " + (stepEstimates[currentStep] - workDonePerSection[currentStep]) + 
								//					" hours to section " + (currentStep+1));

								workPoints -= (stepEstimates[currentStep] - workDonePerSection[currentStep]);
								
								//Section complete
								workDonePerSection[currentStep] = stepEstimates[currentStep];
								
							}
							else
							{
								//Just allocate an equal amount of work for this section
								workDonePerSection[currentStep] += manHoursAvailableToSection;
								workPoints -= manHoursAvailableToSection;
								//System.out.println("WP:" + workPoints);
								//System.out.println("Allocated " + manHoursAvailableToSection + " hours to section " + (currentStep+1));
							
							}

							if(workPoints <= 0) 
							{
								break;
							}
						}
					}

				}

			System.out.println("Performed " + totalWork + " hours of work on " + this.modName);

			}

		}

	}

	public void complete() {
		this.complete = true;
		System.out.println("Completed Module " + this.modName);
	}

	public boolean isComplete()
	{
		return this.complete;
	}
	
	public void setDevelopmentMethod(DevelopmentMethod dm) {
		//T - Sets development style for the module.. Used when computing work done
		this.devMethod = dm;
	}

	public float workRemaining() {
		//T - Gets number of (hours) work left to do for the module to be complete
		float workLeft = 0;

		for(int currentStep = 0; currentStep < 7; currentStep++)
		{
			workLeft += stepEstimates[currentStep] - workDonePerSection[currentStep];
		}

		return workLeft;
	}

	public float workDone()
	{
		//T - Gets number of (hours) work done on the module
		float workDone = 0;

		for(int currentStep = 0; currentStep < 7; currentStep++)
		{
			workDone += workDonePerSection[currentStep];
		}

		return workDone;
	}

	public float totalWorkRequired()
	{
		//Total work required from start to finish in man hours
		float work = 0;

		for(int currentStep = 0; currentStep < 7; currentStep++)
		{
			work += stepEstimates[currentStep];
		}

		return work;
	}

	public void setNumberWorkers(int numWorkers) 
	{
		this.numWorkers = numWorkers;
	}

	public int getNumberWorkers()
	{
		return this.numWorkers;
	}
	
	public void setPerformanceModifier(float mod) {
		this.performanceLevel = mod;
	}

	public float getCompletionLevel()
	{
		if(this.complete)
		{
			return 1;
		}
		return (this.workDone() / this.totalWorkRequired());
	}

	public int sectionsCompleted()
	{
		//Return number of complete sections for calculating division of labour in a module

		int res = 0;

		for(int currentStep = 0; currentStep < 7; currentStep++)
		{
			if(this.stepEstimates[currentStep] <= this.workDonePerSection[currentStep])
			{
				res++;
			}
		}

		return res;
	}

	public boolean IsOnSchedule()
	{
		//Returns true if the module is on schedule
		if(this.devMethod == DevelopmentMethod.AGILE)
		{
			if(this.workDone() < this.origEstimate || this.isComplete())
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
}
