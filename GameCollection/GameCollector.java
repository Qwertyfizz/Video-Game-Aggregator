import java.io.File;
import java.util.ArrayList;

public class GameCollector {
	private ArrayList<Game> gameList;

	public void scan(ArrayList<String> filepaths, char scanType, PlatformName platform) {
		for (String filepath : filepaths) {
			File currFilepath = new File(filepath);
			File[] dirList = currFilepath.listFiles();
			for (File dir : dirList) {
				String tempName = dir.getName();
				String tempPath = dir.getAbsolutePath();
				String tempExe = findExe(dir).getAbsolutePath(); // TODO: Implement Exe search algo (See design doc)

				gameList.add(new Game(tempName, tempPath, tempExe, platform));
			}
		}
	}

	public File findExe(File directory) {
		File[] dirList = directory.listFiles();
		ArrayList<File> exeList = new ArrayList<>();
		for (File f : dirList) {
			if (f.getName().contains(".exe") && !f.isDirectory()) {
				exeList.add(f);
			}
		}
		if (exeList.size() == 1) {
			return exeList.get(0);
		} else if (exeList.size() >= 2) {
			// TODO: Implement file diff
		} else {
			// TODO: Implement recursive call to search deeper file
		}
		return null;
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
