import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Desktop;

import static java.awt.Desktop.*;


public class Game {

	private String name;
	private int appID;
	private String filepath;
	private String exe;
	private int playtime;
	private Boolean isInstalled;
	private PlatformName platform;

	/**
	 * @param name
	 * @param filepath
	 * @param exe
	 * @param platform
	 */
	public Game(String name, String filepath, String exe, PlatformName platform) {
		this.name = name;
		this.appID = -1;
		this.filepath = filepath;
		this.exe = exe;
		this.platform = platform;
	}

	/**
	 * @param name
	 * @param appID
	 * @param filepath
	 * @param isInstalled
	 * @param platform
	 */
	public Game(String name, int appID, String filepath, boolean isInstalled, PlatformName platform) {
		this.name = name;
		this.appID = appID;
		this.filepath = filepath;
		this.isInstalled = isInstalled;
		this.platform = platform;
	}
	
	

	public Game(String name, int appID, String path, String exe, boolean isInstalled, PlatformName platform) {
		this.name = name;
		this.appID = appID;
		this.filepath = path;
		this.exe = exe;
		this.isInstalled = isInstalled;
		this.platform = platform;
	}

	public boolean launch() {
		if (Boolean.FALSE.equals(isInstalled)) {
			return false;
		}
		switch(platform) {
		case STEAM:
			Desktop desktop = getDesktop();
			URI steamProtocol;
			try {
				steamProtocol = new URI("steam://run/" + appID);
				desktop.browse(steamProtocol);
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			try {
				Runtime run = Runtime.getRuntime();
				Process proc = run.exec(exe);
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
		return true;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the filepath
	 */
	public String getFilepath() {
		return filepath;
	}

	/**
	 * @return the platform
	 */
	public PlatformName getPlatform() {
		return platform;
	}

	/**
	 * @return the playtime
	 */
	public int getPlaytime() {
		return playtime;
	}

	public String getLauncher() {
		return exe;
	}

	/**
	 * @return the isInstalled
	 */
	public Boolean getIsInstalled() {
		return isInstalled;
	}

	/**
	 * @param isInstalled the isInstalled to set
	 */
	public void setIsInstalled(Boolean isInstalled) {
		this.isInstalled = isInstalled;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param filepath the filepath to set
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * @param platform the platform to set
	 */
	public void setPlatform(PlatformName platform) {
		this.platform = platform;
	}

	/**
	 * @param playtime the playtime to set
	 */
	public void setPlaytime(int playtime) {
		this.playtime = playtime;
	}

	@Override
	public String toString() {
		return name + "@@@" + appID + "@@@" + filepath + "@@@" + exe + "@@@"
				+ playtime + "@@@" + isInstalled.toString() + "@@@" + platform + "\n";
	}

	

}
