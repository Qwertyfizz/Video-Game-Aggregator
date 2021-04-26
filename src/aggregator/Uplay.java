package aggregator;

import java.util.ArrayList;
import java.util.List;

import com.registry.RegistryKey;

import aggregator.constants.PlatformName;

/**
 * Platform class for Uplay
 * @author dylan
 *
 */
public class Uplay extends GameCollector {

	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher";
	private static final String INSTALL_KEY_NAME = "InstallDir";
	private static final String GAME_DIR_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher\\Installs";// use listRoots on RegistryKey to get array of game keys
	
	/**
	 * Uplay keeps game informaton in HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher\\Installs
	 * each folder name is the game ID
	 * game can be launched from the ID by using uplay://launch/<ID>/0
	 */
	@Override
	public void scan() {
		RegistryKey installs = RegistryKey.parseKey(GAME_DIR_REG);
		ArrayList<RegistryKey> installedGames = (ArrayList<RegistryKey>) installs.getSubKeys();
		
		for(RegistryKey game : installedGames) {
			int ID = Integer.valueOf(game.getName());
			String location = InputOutput.readRegistry(game, "InstallDir");
			if(location.endsWith("/")) {
				location = location.substring(0,location.length()-1);
			}
			String name = location.substring(location.lastIndexOf("/")+1);
			addGame(new Game(name, ID, location, true, PlatformName.UPLAY));
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
