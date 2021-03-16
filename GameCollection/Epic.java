
public class Epic extends GameCollector {
	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Epic Games\\EpicGamesLauncher";
	private static final String INSTALL_KEY_NAME = "AppDataPath";
	private static final String METADATA_REG = "HKEY_CURRENT_USER\\SOFTWARE\\Epic Games\\EOS";
	private static final String METADATA_VALUE_NAME = "ModSdkMetadataDir";

	@Override
	public void scan() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(INSTALL_REG, INSTALL_KEY_NAME).equals("null");
	}

}
