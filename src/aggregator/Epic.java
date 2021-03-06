package aggregator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import aggregator.constants.PlatformName;

/**
 * This class manages games from the Epic Games store
 * 
 * @author Dylan Bollman
 *
 */
public class Epic extends GameCollector {
	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Epic Games\\EpicGamesLauncher";
	private static final String INSTALL_KEY_NAME = "AppDataPath";
	private static final String METADATA_REG = "HKEY_CURRENT_USER\\SOFTWARE\\Epic Games\\EOS";
	private static final String METADATA_VALUE_NAME = "ModSdkMetadataDir";

	/**
	 *Epic stores game info in .item files
	 *	scan will(for each .item):
	 *		Search for ""AppName": "", (This can be used to launch the platforms names (Why not an ID???))
	 *		if it is empty, move to the next file (it's a Artbook, soundtrack, etc.)
	 *		else, it is a game!
	 *				exeName ("AppName": "<here>",)
	 *				name ("DisplayName": "<here>",)
	 *				filepath ("InstallLocation": "<here>",)
	 *				isInstalled = true
	 *				platform = EPIC
	 *				create a new Game object and add it to the gameList
	 */
	@Override
	public void scan() {
		String[] extentions = {"item"};
		ArrayList<File> fileList = new ArrayList<>(directorySearch(new File(InputOutput.readRegistry(METADATA_REG, METADATA_VALUE_NAME)), extentions, false));
		for (File f : fileList) {
			try {
				ArrayList<String> gameData = InputOutput.readFile(f.getCanonicalPath());
				boolean isGame = false;
				for (String s : gameData) {
					if (s.contains("\"LaunchExecutable\": \"")) {
						isGame = !s.contains("\"\"");// does this file have a executeable?
						break;
					}
				}
				if (isGame) {
					String name = "";
					String exe = "";
					String filepath = "";
					
					for (String s : gameData) {
						if(s.contains("\"AppName\": \"")) {
							exe = s.split("\"")[3];
						}
						else if (s.contains("\"DisplayName\": \"")) {
							name = s.split("\"")[3];
						}
						else if (s.contains("\"InstallLocation\": \"")) {
							filepath = s.split("\"")[3];
						}
					}
					filepath = filepath.replace("\\\\", "\\");
					exe = exe.replaceAll("\\\\", "\\");
					addGame(new Game(name, filepath, exe, true, PlatformName.EPIC));
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Checks if the platform is installed
	 * @return boolean
	 * 			true if installed, false otherwise
	 */
	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(INSTALL_REG, INSTALL_KEY_NAME).equals("null");
	}

}
