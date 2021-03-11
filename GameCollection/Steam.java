import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Steam extends GameCollector {
	private String[] directories = { "C:\\Steam\\steamapps\\common", ".\\steamapps\\common" };
	private int steamCount = 0;
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

	public static void getOwnedGames() {
		/*
		 * JSON Reference appid: application ID - can be used for store info name: name
		 * playtime_forever: Playtime in minutes img_icon_url, img_logo_url: images of
		 * the game's icon and logo Playtime_{OS}_forever: OS specific playtimes? (All
		 * games return 0 for this field)
		 */
		URL url;
		try {
			url = new URL("https://api.steampowered.com/IPlayerService/GetOwnedGames/v1/?key=" + key + "&steamid="
					+ steamID + "&include_appinfo=true&include_played_free_games=true&include_free_sub=false");
			String returnedJSON = InputOutput.processRequest(url);
			System.out.println(returnedJSON);

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
