package aggregator;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Origin extends GameCollector {
	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Origin";
	private static final String INSTALL_KEY_NAME = "ClientPath";
	private static final String GAME_DATA_LOCATION = "C:\\ProgramData\\Origin\\LocalContent";

	/**
	 * Origin games can be collected from C:\ProgramData\Origin\LocalContent
	 * 		each game has a directory
	 * 		the presence of a mfst file indecates the game is installed
	 * Get Name from folder
	 * Get ID from &id={GAME_ID}
	 */
	@Override
	public void scan() {
		File[] originGames = new File(GAME_DATA_LOCATION).listFiles();
		for(File game : originGames) {
			File[] gameDir = game.listFiles();
			File gameDat = null;
			for (File file : gameDir) {
				if (file.getName().contains(".mfst")) {
					gameDat = file;
					break;
				}
			}
			
			if (gameDat != null) {
				String gameInfo = InputOutput.readFile(gameDat.getAbsolutePath()).get(0);
				String gameId = gameInfo.substring(gameInfo.indexOf("&id=")+4);
				try {
					gameId = URLDecoder.decode(gameId.substring(0, gameId.indexOf('&')),"UTF-8" );
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}
				
				String gamePath = gameInfo.substring(gameInfo.indexOf("&dipinstallpath=") + 16);
				try {
					gamePath = URLDecoder.decode(gamePath.substring(0, gamePath.indexOf('&')), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				Game newGame = new Game(game.getName(), -1, gamePath, true, PlatformName.ORIGIN);
				newGame.setAlphAppID(gameId);
				addGame(newGame);
			}
			
		}
	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(INSTALL_REG, INSTALL_KEY_NAME).equals("null");
	}

}
