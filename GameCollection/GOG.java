import java.util.List;

import com.registry.*;

public class GOG extends GameCollector {
	private static final String REG_GAME_DIRS = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\GOG.com\\Games";// use listRoots on RegistryKey to get array of game keys
	private static final String GAMENAME_VALUE_NAME = "gameName";
	private static final String EXE_NAME_VALUE_NAME = "exe";
	private static final String LAUNCH_COMMAND_VALUE_NAME = "launchCommand";
	private static final String GAME_ID_VALUE_NAME = "gameID";
	private static final String FILEPATH_VALUE_NAME = "path";
	
	@Override
	public void scan() {
		// TODO Auto-generated method stub
		RegistryKey gameFolder = RegistryKey.parseKey(REG_GAME_DIRS);
		List<RegistryKey> regList = gameFolder.getSubKeys();
		
		for (RegistryKey game : regList) {
			String name = InputOutput.readRegistry(game, GAMENAME_VALUE_NAME);
			String exe = InputOutput.readRegistry(game, EXE_NAME_VALUE_NAME);
			String appId = InputOutput.readRegistry(game, GAME_ID_VALUE_NAME);
			String path = InputOutput.readRegistry(game, FILEPATH_VALUE_NAME);
			
			addGame(new Game(name, Integer.valueOf(appId), path, exe, true, PlatformName.GOG));
		}
	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(REG_GAME_DIRS, GAMENAME_VALUE_NAME).equals("null");
	}

}
