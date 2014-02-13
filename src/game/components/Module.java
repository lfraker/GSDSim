package game.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//this is the module class, it contains arrays of step estimates, a list of
//all the sites working on the modules, it's current phase stage, etc.
public class Module {
	List <Site> sites = new ArrayList<>();
	float timeEstimate;
	Random numberGen = new Random();
	float [] stepEstimates = new float [7];
	float [] origStepEstimates = new float [7];
	float totalEstimate = 0.0f;
	float origEstimate;
	int currentStage;
	boolean complete = false;
	
	public Module(float estimate) {
		this.currentStage = 0;
		this.origEstimate = estimate;
		this.origStepEstimates[0] = (estimate * 0.15f);
		this.origStepEstimates[1] = (estimate * 0.15f);
		this.origStepEstimates[2] = (estimate * 0.10f);
		this.origStepEstimates[3] = (estimate * 0.15f);
		this.origStepEstimates[4] = (estimate * 0.15f);
		this.origStepEstimates[5] = (estimate * 0.15f);
		this.origStepEstimates[6] = (estimate * 0.15f);
		for (int i = 0; i < 7; i++) {
			this.stepEstimates[i] = addSub(this.numberGen.nextInt(2), this.origStepEstimates[i]);
			this.totalEstimate += this.stepEstimates[i]; 
		}
		

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
		return this.timeEstimate;
	}
	

	
	public void complete() {
		this.complete = true;
		System.out.println("Completing module");
	}
	
	
	
}
