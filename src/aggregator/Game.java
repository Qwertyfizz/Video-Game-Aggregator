package aggregator;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import aggregator.constants.PlatformName;

import java.awt.Desktop;

import static java.awt.Desktop.*;


public class Game {


	private String name;
	private int appID = -1; //not all platforms show game IDs
	private String alphAppID;
	private String filepath;
	private String exe = "none";
	private int playtime = -1; // not all platforms keep records/make those stats recoverable
	private Boolean isInstalled;
	private PlatformName platform;
	
	/**
	 * @param name
	 * @param filepath
	 * @param exe
	 * @param isInstalled
	 * @param platform
	 */
	public Game(String name, String filepath, String exe, boolean isInstalled, PlatformName platform) {

		this.name = name;
		this.filepath = filepath;
		this.exe = exe;
		this.isInstalled = isInstalled;
		this.platform = platform;
	}
	
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
	
	/**
	 * @param name
	 * @param appID
	 * @param isInstalled
	 * @param platform
	 */
	public Game(String name, int appID, Boolean isInstalled, PlatformName platform) {
		super();
		this.name = name;
		this.appID = appID;
		this.isInstalled = isInstalled;
		this.platform = platform;
	}

	public boolean launch() {
		if (Boolean.FALSE.equals(isInstalled)) {
			return false;
		}
		switch(platform) {
		case STEAM:
			Desktop desktopSteam = getDesktop();
			URI steamProtocol;
			try {
				steamProtocol = new URI("steam://run/" + appID);
				desktopSteam.browse(steamProtocol);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case ORIGIN:
			Desktop desktopOrigin = getDesktop();
			URI originProtocol;
			try {
				originProtocol = new URI("origin://launchgame/" + alphAppID);
				desktopOrigin.browse(originProtocol);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case UPLAY:
			Desktop desktopUplay = getDesktop();
			URI uplayProtocol;
			try {
				uplayProtocol = new URI("uplay://launch/" + appID + "/0");
				desktopUplay.browse(uplayProtocol);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case EPIC:
			Desktop desktopEpic = getDesktop();
			URI epicProtocol;
			try {
				epicProtocol = new URI("com.epicgames.launcher://apps/" + exe + "?action=launch&silent=true");
				desktopEpic.browse(epicProtocol);
			} catch (URISyntaxException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case GOG:
			ProcessBuilder pb = new ProcessBuilder();
			pb.command(new GOG().getGOGPath(), "/command=runGame", "/gameId=" + appID, "/path=" + filepath);
			try {
				pb.start();
			} catch (IOException e1) {
				System.out.println();
				e1.printStackTrace();
			}
			break;
		default:
			File file = new File(exe);
			ProcessBuilder processBuilder = new ProcessBuilder(file.getAbsolutePath());
			processBuilder.directory(file.getParentFile());
			
			try {
				processBuilder.start();
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
	 * @return the appID
	 */
	public int getAppID() {
		return appID;
	}

	/**
	 * @return the exe
	 */
	public String getExe() {
		return exe;
	}

	/**
	 * @return the alphAppID
	 */
	public String getAlphAppID() {
		return alphAppID;
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

	/**
	 * @param appID the appID to set
	 */
	public void setAppID(int appID) {
		this.appID = appID;
	}

	/**
	 * @param exe the exe to set
	 */
	public void setExe(String exe) {
		this.exe = exe;
	}

	/**
	 * @param alphAppID the alphAppID to set
	 */
	public void setAlphAppID(String alphAppID) {
		this.alphAppID = alphAppID;
	}

	@Override
	public String toString() {
		return name;
	}

	

	

}
