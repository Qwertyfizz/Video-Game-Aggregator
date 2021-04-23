package aggregator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;

import aggregator.constants.PlatformName;

public class Steam extends GameCollector {
	
	private static final String PLATFORM_INSTALL_KEY = "HKEY_LOCAL_MACHINE\\SOFTWARE\\WOW6432Node\\Valve\\Steam";
	private static final String PLATFORM_INSTALL_VALUE = "InstallPath";
	
	
	private static String key = "";
	private static String steamID = "";

	/**
	 * 
	 */
	public Steam() {
		super();
	}

	@Override
	public void scan() {
		getInstalledGames();
	}

	public void getInstalledGames() {
		String dataFile = "steamGameData.txt";
		File exe = new File("scripts\\SteamGameFinder.exe");
		try {
			Runtime run = Runtime.getRuntime();
			String canPath = exe.getCanonicalPath();
			Process proc = run.exec(canPath);
			proc.waitFor();

			ArrayList<String> installedGames = InputOutput.readFile(dataFile);

			for (String data : installedGames) {
				String[] parsed = data.split(",,,,");

				int appID = Integer.parseInt(parsed[0]);
				String path = parsed[1];
				String name = parsed[2];

				addGame(new Game(name, appID, path, true, PlatformName.STEAM));
			}

			File temp = new File(dataFile);
			temp.delete();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}

	}

	public boolean getOwnedGames() {
		/*
		 * JSON Reference appid: application ID - can be used for store info name: name
		 * playtime_forever: Playtime in minutes 
		 * img_icon_url, img_logo_url: images of the game's icon and logo 
		 * Playtime_{OS}_forever: OS specific playtimes? (All games return 0 for all fields, might be broken)
		 */
		URL url;
		try {
			url = new URL("https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key=" + key + "&steamid="
					+ steamID + "&include_appinfo=true&include_played_free_games=true&include_free_sub=false");
			String returnedJSON = InputOutput.processRequest(url);
			
			if (returnedJSON == null) {
				return false;
			}
			
			//remove the beginning and ending wrappers, only the game array is needed
			returnedJSON = returnedJSON.substring(returnedJSON.indexOf('['));
			returnedJSON = returnedJSON.substring(0, returnedJSON.length()-2);
			
			//Remove the unneeded stats for easier processing
			returnedJSON = returnedJSON.replace("\"has_community_visible_stats\": true,", "");
			returnedJSON = returnedJSON.replace("\"has_community_visible_stats\": false,", "");
			
			Gson gson = new Gson();
			
			SteamAPIGame[] gameArray = gson.fromJson(returnedJSON, SteamAPIGame[].class);
			
			for(SteamAPIGame apiG : gameArray) {
				boolean inList = false;
				for(Game g : getGameList()) {
					if(apiG.getAppid() == g.getAppID()) {
						g.setPlaytime(apiG.playtime_forever);
						inList = true;
						break;
					}
				}
				if(!inList) {
					Game g = new Game(apiG.name,apiG.playtime_forever,false,PlatformName.STEAM);
					g.setFilepath("none");
					getGameList().add(g);
				}
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * @return the key
	 */
	public static String getKey() {
		return key;
	}

	/**
	 * @return the steamID
	 */
	public static String getSteamID() {
		return steamID;
	}

	/**
	 * @param key the key to set
	 */
	public static void setKey(String key) {
		Steam.key = key;
	}

	/**
	 * @param steamID the steamID to set
	 */
	public static void setSteamID(String steamID) {
		Steam.steamID = steamID;
	}

	@Override
	public boolean checkForPlatform() {
		return !InputOutput.readRegistry(PLATFORM_INSTALL_KEY, PLATFORM_INSTALL_VALUE).equals("null");
	}
}
