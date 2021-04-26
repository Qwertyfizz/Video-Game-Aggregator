package aggregator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.registry.*;

/**
 * A utility class containing functions for reading and writing data.
 * @author dylan
 */
public class InputOutput {
	/**
	 * Reads a file line by line, returning the result as an arrayList
	 * @param filename
	 * 		The path to the file
	 * @return Arraylist
	 * 		A arraylist that contains the contents of a file line by line. A empty array indecates an empty file or a bad path.
	 */
	public static ArrayList<String> readFile(String filename) {
		File file = new File(filename);
		try (Scanner reader = new Scanner(file)) {
			ArrayList<String> list = new ArrayList<>();
			while (reader.hasNextLine()) {
				list.add(reader.nextLine());
			}
			return list;
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" was not found");
			return new ArrayList<>();
		}
	}

	/**
	 * Writes a file using an arraylist. Each entry in the arraylist will be on a seprate line.
	 * @param filename
	 * 		the name of the file to write to. (WILL OVERIDE DATA)
	 * @param list
	 * 		The list of strings to pull from
	 */
	public static void writeFile(String filename, ArrayList<String> list) {

		try (FileWriter writer = new FileWriter(filename)) {
			for (String s : list) {
				writer.write(s);
			}
		} catch (IOException e1) {
			System.out.println("ERROR:");
			e1.printStackTrace();
		}

	}

	/**
	 * Create a new file
	 * @param filepath
	 * 		The location and name to use
	 * @return File
	 * 		the new file, null if creation failed
	 */
	public static File createFile(String filepath) {
		try {
			File newFile = new File(filepath);
			newFile.createNewFile();
			return newFile;
		} catch (IOException e) {
			System.out.println("ERROR:");
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 	Writes an arraylist of games into a JSON file
	 * @param list
	 * 		The list to use
	 * @param filename
	 * 		The filename and location to write to
	 */
	public static void writeJSONFile(ArrayList<Game> list, String filename) {
		String gameJson = "";
		Gson gson = new GsonBuilder().create();
		
		String json = gson.toJson(list);
		
		try (FileWriter writer = new FileWriter(filename)){
			writer.write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Read a JSON string into a array
	 * @param json
	 * 		The JSON in string format
	 * @return gameArray
	 * 		The array of game objects in the String
	 */
	public static Game[] readJSONFile(String json) {
		Gson gson = new Gson();
		
		Game[] gameArray = gson.fromJson(json, Game[].class);
		
		return gameArray;
	}

	/**
	 * Send a GET request to the url given. Used for API calls
	 * @param url
	 * 		The URL to querry
	 * @return inputLine
	 * 		The Returned line, null if request failed
	 */
	public static String processRequest(URL url) {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;

			inputLine = in.readLine();
			in.close();

			return inputLine;
		} catch (IOException e) {
			return null;
		}
	}
	
	/**
	 * Returns the registry value using a String to find the location
	 * @param keyLoc
	 * 		the location of the key as a String
	 * @param keyName
	 * 		the name of the key
	 * @return value
	 * 		the value of the key
	 */
	public static String readRegistry(String keyLoc, String keyName) {
		RegistryKey key = RegistryKey.parseKey(keyLoc);
		RegistryValue value = key.getValue(keyName);
		String tempVal = value.toString();
		return tempVal.substring(tempVal.indexOf("Value: ") + 7); // + 7 removes everything up to "Value: " leaving only value requested
	}
	
	/**
	 * Returns the registry value using a Registry object to find the location
	 * @param keyLoc
	 * 		the location of the key as a RegistryKey Object
	 * @param keyName
	 * 		the name of the key
	 * @return value
	 * 		the value of the key
	 */
	public static String readRegistry(RegistryKey key, String keyName) {
		RegistryValue value = key.getValue(keyName);
		String tempVal = value.toString();
		return tempVal.substring(tempVal.indexOf("Value: ") + 7); // + 7 removes everything up to "Value: " leaving only value requested
	}
}
