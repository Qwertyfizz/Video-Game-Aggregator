import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

public abstract class GameCollector {
	private ArrayList<Game> gameList;
	private String gameListFile = "/data/gameList.csv";

	/*
	 * Known Non-Game executables (Crash handlers, common libraries, un-installers,
	 * etc.) Many games use the same utilities due to engine/OS sharing
	 */
	private String[] nonGameLaunchers = { "UnityCrash", "vcredist", "crashpad", "CrashReporter", "Uninstaller",
			"uninstall", "uins", "gdb", "dotNet", "ModGen", "SystemInfo", "Updater" };

	public GameCollector() {
		this.gameList = new ArrayList<>();
	}

	public void scan(ArrayList<String> filepaths, char scanType, PlatformName platform) { // Generic scan of a platforms
																							// files
		for (String filepath : filepaths) {
			File currFilepath = new File(filepath);
			File[] dirList = currFilepath.listFiles();
			for (File dir : dirList) {
				String tempName = dir.getName();
				String tempPath = dir.getAbsolutePath();
				String tempExe = findExe(dir).getAbsolutePath(); // TODO: Implement Exe search algo (See design doc)
				if (!tempExe.contains(">= 2") && !tempExe.contains("None found")
						&& !tempExe.contains("No exes are present")) {
					System.out.println("Done! Launcher is: " + tempExe);
				} else {
					System.out.println("FALURE: " + tempExe.substring(tempExe.lastIndexOf("\\"), tempExe.length()));
				}
				gameList.add(new Game(tempName, tempPath, tempExe, platform));
			}
		}
	}

	public abstract void scan(); // Implementation for specific scanning methods per platform

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

	public void saveGameList() {
		ArrayList<String> output = new ArrayList<>();
		for (Game g : gameList) {
			output.add(g.toString());
		}
		InputOutput.writeFile(gameListFile, output);
	}

	public void loadGameList() {
		ArrayList<String> input = new ArrayList<>();
		input = InputOutput.readFile(gameListFile);
		for (String s : input) {
			String[] temp = s.strip().split(",");
			gameList.add(new Game(temp[0], temp[1], temp[2], PlatformName.valueOf(temp[3])));
		}
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
}
