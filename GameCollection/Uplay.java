
public class Uplay extends GameCollector {

	private final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher";
	private final String INSTALL_KEY_NAME = "InstallDir";
	private final String GAME_DIR_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher\\Installs";// use listRoots on RegistryKey to get array of game keys
	
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
