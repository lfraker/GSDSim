package game.components;

import game.components.Module;
import game.components.Site;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class ScenarioLoader {
	public static Scenarios load(String path) {
		Scenarios scenario = null;

		try {
			BufferedReader br = new BufferedReader(new FileReader(path));

			Gson gson = new Gson();
			scenario = gson.fromJson(br, Scenarios.class);

			br.close();
		} catch (JsonSyntaxException e) {
			System.err.println("File \"" + path + "\" does not contain valid JSON");
			System.err.println("\t" + e.getMessage());
		} catch (IOException e) {
			System.err.println("Error loading scenario " + path + "\n" + e);
		}

		return scenario;
	}
}
