package aggregator;


public class Origin extends GameCollector {
	private static final String INSTALL_REG = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Origin";
	private static final String INSTALL_KEY_NAME = "ClientPath";

	@Override
	public void scan() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(INSTALL_REG, INSTALL_KEY_NAME).equals("null");
	}

}
