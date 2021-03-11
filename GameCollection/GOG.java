
public class GOG extends GameCollector {

	private final String REG_GAME_DIRS = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\GOG.com\\Games";// use listRoots on RegistryKey to get array of game keys
	private final String GAMENAME_VALUE_NAME = "gameName";
	private final String EXE_NAME_VALUE_NAME = "exe";
	private final String LAUNCH_COMMAND_VALUE_NAME = "launchCommand";
	
	@Override
	public void scan() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForPlatform() {
		// TODO Auto-generated method stub
		return false;
	}

}
