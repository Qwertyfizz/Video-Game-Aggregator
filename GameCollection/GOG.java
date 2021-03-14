
public class GOG extends GameCollector {
	private static final String REG_GAME_DIRS = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\GOG.com\\Games";// use listRoots on RegistryKey to get array of game keys
	private static final String GAMENAME_VALUE_NAME = "gameName";
	private static final String EXE_NAME_VALUE_NAME = "exe";
	private static final String LAUNCH_COMMAND_VALUE_NAME = "launchCommand";
	
	@Override
	public void scan() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(REG_GAME_DIRS, GAMENAME_VALUE_NAME).equals("null");
	}

}
