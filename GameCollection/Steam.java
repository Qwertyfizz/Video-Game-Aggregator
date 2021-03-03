import java.net.MalformedURLException;
import java.net.URL;

public class Steam extends GameCollector {
	private String[] directories = { "C:\\Steam\\steamapps\\common", ".\\steamapps\\common" };
	private static String key = "";
	private static String steamID = "";
	
	public static void getOwnedGames() {
		/*
		 * JSON Reference
		 * appid: application ID - can be used for store info
		 * name: name
		 * playtime_forever: Playtime in minutes
		 * img_icon_url, img_logo_url: images of the game's icon and logo
		 * Playtime_{OS}_forever: OS specific playtimes? (All games return 0 for this field)
		 */
		URL url;
		try {
			url = new URL("https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key="+ key +"&steamid=" + steamID + "&include_appinfo=true&include_played_free_games=true&include_free_sub=false");
			
			System.out.println(InputOutput.processRequest(url));
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
