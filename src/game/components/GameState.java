package game.components;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import com.google.gson.*;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;


public class GameState 
{
	/*
	*	Saves or restores a game state.
	*/

	List<Site> siteList;

	public GameState(List<Site> sites)
	{
		this.siteList = sites;
	}

	public List<Site> LoadState(String filename)
	{
		//Retrieves a saved game state (sites and modules) from the given filename.
		try 
		{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			Gson gson = new Gson();
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
	}


	public void SaveState(String filename)
	{
		//Will save current game state (sites and modules) to the given filename.

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(siteList); 
		try
		{
			FileWriter writer = new FileWriter(filename);  
	   		writer.write(json);  
	   		writer.close();
		}
		catch(Exception e)
		{
			System.out.println("Couldn't save state.");
		}

	}

	public List<List<Site>> LoadScenarios()
	{
		//Loads multiple scenarios stored as seperate game state files in the 'Scenarios' directory
		File folder = new File("Scenarios/");
		File[] listOfFiles = folder.listFiles();

		List<List<Site>> scenarios = new ArrayList<List<Site>>();

	    for (int i = 0; i < listOfFiles.length; i++) 
	    {
		    if (listOfFiles[i].isFile()) 
		    {
		    	scenarios.add(this.LoadState("Scenarios/" + listOfFiles[i].getName()));
		    } 
	    }

	    return scenarios;
	}

}
