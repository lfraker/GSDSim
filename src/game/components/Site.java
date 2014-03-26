package game.components;

import java.util.ArrayList;

import java.util.List;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

//this is the site class, it contains a list of modules
public class Site {

	int noOfWorkers;
	int timezone;
	String siteName;
	MapMarkerDot mapMarker;
	int costDeveloperDay = 4;
	int effortDeveloperDay = 10;
	double coordinates[] = new double[2];

	public ArrayList<Module> modules = new ArrayList<Module>();
	public Site(String name, int noOfWorkers, MapMarkerDot mDot, int tZone) {
		//this.modules = modules;
		this.siteName = name;
		this.noOfWorkers = noOfWorkers;
		this.mapMarker = mDot;
		this.timezone = tZone;
	}
	
	public Site(String name, int noOfWorkers, MapMarkerDot mDot, int tZone, int cD, int eD) {
		//this.modules = modules;
		this.siteName = name;
		this.noOfWorkers = noOfWorkers;
		this.mapMarker = mDot;
		this.timezone = tZone;
		this.costDeveloperDay = cD;
		this.effortDeveloperDay = eD;
	}

	public String getName() {
		return this.siteName;
	}
	
	public MapMarkerDot getMarker() {
		return mapMarker;
	}

	public void setMarker(MapMarkerDot m)
	{
		this.mapMarker = m;
	}
	
	public void addModule(Module m) {
		this.modules.add(m);
	}

	public void doWork() {
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

	public int getTimezone() {
		return timezone;
	}

	public void setTimezone(int t) {
		timezone = t;
	}

	public ArrayList<Module> getModules() {
		return this.modules;
	}
	
	@Override
	public String toString() {
		return this.siteName;
	}

	public void SetCoordinates(double lat, double lon)
	{
		coordinates[0] = lat;
		coordinates[1] = lon;
	}

	public double[] GetCoordinates()
	{
		return coordinates;
	}

	public int GetNumberWorkers()
	{
		return this.noOfWorkers;
	}

	public void Handover(Module m, Site s)
	{
		this.modules.remove(m);
		s.addModule(m);
	}

}
