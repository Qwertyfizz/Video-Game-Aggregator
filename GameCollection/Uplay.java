
public class Uplay extends GameCollector {

	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher";
	private static final String INSTALL_KEY_NAME = "InstallDir";
	private static final String GAME_DIR_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Ubisoft\\Launcher\\Installs";// use listRoots on RegistryKey to get array of game keys
	
	/**
	 * Uplay keeps game directory informaton in the registry
	 * launchers need to be searched for
	 */
	@Override
	public void scan() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(INSTALL_REG, INSTALL_KEY_NAME).equals("null");
	}

}
