package aggregator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import aggregator.constants.PlatformName;

/**
 * An abstract class that implements the basic functions that all platforms will have. All platforms inherit from this class. 
 * 
 * @author Dylan Bollman
 *
 */
public abstract class GameCollector {
	private ArrayList<Game> gameList;
	private String gameListFile = "gameList_"+this.getClass().getName().replaceAll("aggregator.", "")+".json";

	protected GameCollector() {
		this.gameList = new ArrayList<>();
	}

	/**
	 * Scans for games
	 * Each platform has a different method to retrieve information
	 * See a Platform class for a dicription of that platforms method
	 */
	public abstract void scan(); // Implementation for specific scanning methods per platform
	
	/**
	 * Checks if the platform is installed
	 * @return boolean
	 * 		true if the platform is found
	 */
	public abstract boolean checkForPlatform(); // Function to check for platform install

	/**
	 * Lists all files in a directory
	 * @param dir
	 * 		the directory to search
	 * @param extentions
	 * 		the extentions to search for
	 * @param searchAll
	 * 		True to recursively search every folder below the given dir. False to only search the directory given.
	 * @return
	 */
	public Collection<File> directorySearch(File dir, String[] extentions, boolean searchAll) {

		return FileUtils.listFiles(dir, extentions, searchAll);
	}

	/**
	 * Adds a game to the gamelist
	 * @param g
	 * 		The game to be added
	 */
	public void addGame(Game g) {
		gameList.add(g);
	}

	/** 
	 * Return the current list of games
	 * @return
	 */
	public ArrayList<Game> getGameList() {
		return gameList;
	}

	/**
	 * Returns a game if its name matches the string given. Returns null if not found.
	 * @param name
	 * 		the name to search for
	 * @return g
	 * 		the Game found, null if not found
	 */
	public Game getGameByName(String name) {
		for (Game g : gameList) {
			if (g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}
	/**
	 * Saves current gamelist to a file named <PlatformName>.json
	 */
	public void saveToFile() {
		
		File saveFile = InputOutput.createFile(gameListFile);
		
		InputOutput.writeJSONFile(gameList, gameListFile);
	}
	
	/**
	 * Loads from the file for the specific Platform class calling this function
	 */
	public void loadFromFile() {
		String json = InputOutput.readFile(gameListFile).get(0);
		Game[] toLoad = InputOutput.readJSONFile(json);
		for (Game g : toLoad) {
			addGame(g);
		}
	}
}
