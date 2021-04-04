package aggregator;

import java.io.File;

public class Origin extends GameCollector {
	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Origin";
	private static final String INSTALL_KEY_NAME = "ClientPath";
	private static final String GAME_DATA_LOCATION = "C:\\ProgramData\\Origin\\LocalContent";

	/**
	 * Origin games can be collected from C:\ProgramData\Origin\LocalContent
	 * 		each game has a directory
	 * 		the presence of a OFB-EAST(GameID).dat indicates that the game is installed
	 * Get Name from folder
	 * Get ID from .dat filename
	 */
	@Override
	public void scan() {
		File[] originGames = new File(GAME_DATA_LOCATION).listFiles();
		for(File game : originGames) {
			File[] gameDir = game.listFiles();
			File gameDat = null;
			for (File f : gameDir) {
				if (f.getName().contains(".dat") && f.getName().contains("OFB-EAST")) {
					gameDat = f;
					break;
				}
			}
			if (gameDat != null) {
				String idName = gameDat.getName();
				idName = idName.replace(".dat", "");
				idName = idName.replace("OFB-EAST", "");
				
				addGame(new Game(game.getName(), Integer.valueOf(idName), game.getAbsolutePath(), true, PlatformName.ORIGIN));
			}
		}
	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(INSTALL_REG, INSTALL_KEY_NAME).equals("null");
	}

}
