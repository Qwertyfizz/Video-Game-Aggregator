import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public abstract class GameCollector {
	private ArrayList<Game> gameList;
	private String gameListFile = "gameList_"+this.getClass().getName()+".json";

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

	public File findExe(File directory) {
		System.out.println("Working on " + directory.getName());
		String[] extention = { "exe" };
		ArrayList<File> exeList = new ArrayList<>(directorySearch(directory, extention, false));

		if (exeList.size() == 1) {// If it's the only file in the first directory, It is likely the main exe
			return exeList.get(0);
		} else if (exeList.size() >= 2) // Strip out common executables and look again
		{
			for (int i = 0; i < exeList.size(); i++) {
				for (int j = 0; j < nonGameLaunchers.length; j++) {
					if (exeList.get(i).getName().contains(nonGameLaunchers[j])) {
						exeList.remove(i);
						break;
					}
				}
			}
			if (exeList.size() == 1) {
				return exeList.get(0);
			}
			for (File exe : exeList) {
				if (exe.getName().toLowerCase().contains("launch")) {
					return exe;
				}
			}
		}
		// Get a list of ALL executables in the directories
		// Strip out all known non game launchers
		// look for good hints at which is the correct one (name of game in file(No
		// spaces, Spaces to _), first letters only, etc.)
		// WILL BE SLOW - AVOID IF POSSIBLE
		else {
			String name = directory.getName();
			exeList.clear();
			exeList = new ArrayList<>(directorySearch(directory, extention, true));
			for (int i = 0; i < exeList.size(); i++) {
				for (int j = 0; j < nonGameLaunchers.length; j++) {
					if (exeList.get(i).getName().contains(nonGameLaunchers[j])) {
						exeList.remove(i);
						break;
					}
				}
			}
			if (exeList.size() == 1) {
				return exeList.get(0);
			} else if (!exeList.isEmpty()) {
				return new File(" >= 2");
			}

		}
		// Nothing in there! Indicates a left over file from a previously installed game
		return new File("No exes are present");
	}

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
