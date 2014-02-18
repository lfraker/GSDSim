package game.components;

import java.util.ArrayList;
import java.util.List;

//this is the site class, it contains a list of modules
public class Site {

	int noOfWorkers;
	int timezone;

	ArrayList<Module> modules = new ArrayList<>();
	public Site(String name, int noOfWorkers) {
		//this.modules = modules;
		this.noOfWorkers = noOfWorkers;
	}

	void doWork() {
		float effortUnits = 100;
		Module x = modules.get(0);
		while (effortUnits > 0 && x != null) {
			float stepRemaining = x.stepEstimates[x.currentStage];
			if (stepRemaining > effortUnits) {
				x.stepEstimates[x.currentStage] -= effortUnits;
				x.totalEstimate -= effortUnits;
				effortUnits = 0;
			} else {
				x.totalEstimate -= stepRemaining;
				effortUnits -= stepRemaining;
				x.stepEstimates[x.currentStage] = 0;
				System.out.println("Completing stage " + x.currentStage);
				x.currentStage++;
				if (x.getEstimate() == 0) {
					x.complete();
					modules.remove(0);
					if (!modules.isEmpty()) {
						x = modules.get(0);
					}
				}
			}
		}
	}

	int getTimezone() {
		return timezone;
	}

	ArrayList<Module> getModules() {
		return modules;
	}


}
