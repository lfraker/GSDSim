package game.components;

import java.util.ArrayList;

import java.util.List;

import java.lang.Math;

import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

//this is the site class, it contains a list of modules
public class Site {

	int noOfWorkers;
	int timezone;
	String siteName;
	String language;
	MapMarkerDot mapMarker;
	public int costDeveloperDay = 60;

	//Percentage performance - normal = 1;
	float effortDeveloperDay = 1;
	double coordinates[] = new double[2];
	public boolean isRussAsian;

	public double globalDistance;


	
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
	
	public boolean isRussAsian()
	{
		return this.isRussAsian;
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

	/*
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
	*/

	public void SetGlobalDistance(double gd)
	{
		this.globalDistance = gd;
	}

	public double GetGlobalDistance()
	{
		//Calculates and returns global distance
		return this.globalDistance;
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

	public float GetFailureProbability()
	{
		int difficulty = 1;
		double globalDistance = this.GetGlobalDistance();
		return (float)(difficulty * (globalDistance / (globalDistance + 1)));
	}

	public double CalcGlobalDistanceToSite(Site s)
	{
		//Calculate Global Distance to site


		int temporalDifference = Math.abs(this.timezone - s.getTimezone());

		//Geographical distance

		double geographicDistance = this.GeoDistanceToSite(s);


		//return (double) (temporalDifference + geographicDistance);

		return geographicDistance;
	}

	public static double toRad(double deg)
	{
		//Converts degrees to radians for distance calculations

		return (deg * 0.0174532925);
	}

	public double GeoDistanceToSite(Site s)
	{
		//Uses the 'Haversine' formula to get distance between this and one other site
		//http://www.movable-type.co.uk/scripts/latlong.html

		double earthRadius = 6371; //Radius of the earth in km

		double coordinates[] = this.GetCoordinates();

		double lat1 = coordinates[0];
		double lon1 = coordinates[1];

		coordinates = s.GetCoordinates();

		double lat2 = coordinates[0];
		double lon2 = coordinates[1];

		double dLat = toRad(lat2 - lat1);
		double dLon = toRad(lon2 - lon1);

		lat1 = toRad(lat1);
		lat2 = toRad(lat2);

		double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2); 

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		double geographicDistance = earthRadius * c;


		return geographicDistance;
	}

}
