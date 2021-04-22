package aggregator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

import aggregator.constants.PlatformName;

public abstract class GameCollector {
	private ArrayList<Game> gameList;
	private String gameListFile = "gameList_"+this.getClass().getName().replaceAll("aggregator.", "")+".json";

	/*
	 * Known Non-Game executables (Crash handlers, common libraries, un-installers,
	 * etc.) Many games use the same utilities due to engine/OS sharing
	 */
	private String[] nonGameLaunchers = { "UnityCrash", "vcredist", "crashpad", "CrashReporter", "Uninstaller",
			"uninstall", "uins", "gdb", "dotNet", "ModGen", "SystemInfo", "Updater" };

	protected GameCollector() {
		this.gameList = new ArrayList<>();
	}

	public abstract void scan(); // Implementation for specific scanning methods per platform
	
	public abstract boolean checkForPlatform(); // Function to check for platform install

	public Collection<File> directorySearch(File dir, String[] extentions, boolean searchAll) {

		return FileUtils.listFiles(dir, extentions, searchAll);
	}

	public void addGame(Game g) {
		gameList.add(g);
	}

	public ArrayList<Game> getGameList() {
		return gameList;
	}

	public Game getGameByName(String name) {
		for (Game g : gameList) {
			if (g.getName().equals(name)) {
				return g;
			}
		}
		return null;
	}

	public ArrayList<Game> getGamesByPlatform(PlatformName platform) {
		ArrayList<Game> platList = new ArrayList<>();
		for (Game g : gameList) {
			if (g.getPlatform() == platform) {
				platList.add(g);
			}
		}
		return platList;
	}
	
	public void saveToFile() {
		
		File saveFile = InputOutput.createFile(gameListFile);
		
		InputOutput.writeJSONFile(gameList, gameListFile);
	}
	
	public void loadFromFile() {
		String json = InputOutput.readFile(gameListFile).get(0);
		Game[] toLoad = InputOutput.readJSONFile(json);
		for (Game g : toLoad) {
			addGame(g);
		}
	}
}
