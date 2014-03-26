package game.components;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import game.components.Site;
import game.org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import com.google.gson.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
/*
*	Saves or restores a game session.
*
*/

public class GameState 
{

	List<Site> siteList;

	public GameState(List<Site> sites)
	{
		this.siteList = sites;

		/*
		System.out.println();

		for(Site s : this.siteList)
		{
			System.out.println("Modules: " + s.modules.size());
		}*/
	}

	public List<Site> LoadState(String filename)
	{
		
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Gson gson = new Gson();
			//List<Site> sites = gson.fromJson(br, Site.class);

			Type siteListType = new TypeToken<ArrayList<Site>>(){}.getType();
 			List<Site> sites =  gson.fromJson(br, siteListType);
			br.close();

			return sites;
		} 
		catch (JsonSyntaxException e) 
		{
			System.err.println("File \"" + filename + "\" does not contain valid JSON");
			System.err.println("\t" + e.getMessage());
		} 
		catch (IOException e) 
		{
			System.err.println("Error loading scenario " + filename + "\n" + e);
		}


		return null;
		/*

		MapMarkerDot pos = new MapMarkerDot(null, si.name, si.location[0], si.location[1]);
      	Site s = new Site(si.name, Integer.parseInt(si.workers), pos, si.timezone);
	
		*/

	}


	public void SaveState(String filename)
	{
		//Gson gson = new Gson();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(siteList);  
		//System.out.println(json);

		try
		{
			FileWriter writer = new FileWriter(filename);  
	   		writer.write(json);  
	   		writer.close();
		}
		catch(Exception e)
		{

		}
		  

	}

	public List<List<Site>> LoadScenarios()
	{

		File folder = new File("Scenarios/");
		File[] listOfFiles = folder.listFiles();

		List<List<Site>> scenarios = new ArrayList<List<Site>>();

	    for (int i = 0; i < listOfFiles.length; i++) 
	    {
		    if (listOfFiles[i].isFile()) 
		    {
		    	scenarios.add(this.LoadState("Scenarios/" + listOfFiles[i].getName()));

		        //System.out.println("File " + listOfFiles[i].getName());
		    } 
	    }

	    return scenarios;
	}

	//public 

}
