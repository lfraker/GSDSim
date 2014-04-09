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
	public int costDeveloperDay = 60;

	//Percentage performance - normal = 1;
	float effortDeveloperDay = 1;
	double coordinates[] = new double[2];
	public boolean isRussAsian;
	public int geographicDistance, culturalDistance, temporalDistance;
	
	
	public ArrayList<Module> modules = new ArrayList<Module>();
	public Site(String name, int noOfWorkers, MapMarkerDot mDot, int tZone) {
		//this.modules = modules;
		this.siteName = name;
		this.noOfWorkers = noOfWorkers;
		this.mapMarker = mDot;
		this.timezone = tZone;
	}
	
	public Site(String name, int noOfWorkers, MapMarkerDot mDot, int tZone, int cD, float eD, boolean isRA) {
		//this.modules = modules;
		this.siteName = name;
		this.noOfWorkers = noOfWorkers;
		this.mapMarker = mDot;
		this.timezone = tZone;
		this.costDeveloperDay = cD;
		this.effortDeveloperDay = eD;
		this.isRussAsian = isRA;
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

	public void SetCostDeveloperDay(int cdd)
	{
		this.costDeveloperDay = cdd; 
	}

	public int GetCostDeveloperDay()
	{
		return this.costDeveloperDay;
	}

	public void SetEffortPerDeveloperDay(float edd)
	{
		this.effortDeveloperDay = edd;
	}

	public float GetEffortPerDeveloperDay()
	{
		return this.effortDeveloperDay;
	}

	public void SetTemporalDistance(int td)
	{
		this.temporalDistance = td;
	}

	public int GetTemporalDistance()
	{
		return this.temporalDistance;
	}

	public void SetCulturalDistance(int cd)
	{
		this.culturalDistance = cd;
	}

	public int GetCulturalDistance()
	{
		return this.culturalDistance;
	}

	public void SetGeographicDistance(int gd)
	{
		this.geographicDistance = gd;
	}

	public int GetGlobalDistance()
	{
		//Calculates and returns global distance
		return this.geographicDistance + this.culturalDistance + this.temporalDistance;
	}

	public float GetFailureProbability()
	{
		int difficulty = 1;
		int globalDistance = GetGlobalDistance();
		return (float)(difficulty * (globalDistance / (globalDistance + 1)));
	}

}
