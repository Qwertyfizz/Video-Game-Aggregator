package aggregator.view;

import java.util.ArrayList;

import aggregator.Epic;
import aggregator.GOG;
import aggregator.Game;
import aggregator.GameCollector;
import aggregator.Origin;
import aggregator.Other;
import aggregator.Steam;
import aggregator.Uplay;

/**
 * Singleton class for managing collectors and data
 * Needed for communication between windows
 */
public final class DataHolder{
	
	private static final DataHolder INSTANCE = new DataHolder();
	
	private GameCollector steam = new Steam();
	private GameCollector origin = new Origin();
	private GameCollector epic = new Epic();
	private GameCollector gog = new GOG();
	private GameCollector uplay = new Uplay();
	private GameCollector other = new Other();
	private GameCollector[] collectors = {steam, origin, epic, gog, uplay, other};
	private ArrayList<Game> fullList = new ArrayList<>();
	
	private String steamID = "";
	private String steamKey = "";
	
	private DataHolder() {}
	
	/**
	 * Returns the single instance of the data holder that exists in this runtime
	 * @return instance
	 */
	public static DataHolder getInstance() {
	    return INSTANCE;
	}

	/**
	 * @return the steam
	 */
	public GameCollector getSteam() {
		return steam;
	}

	/**
	 * @return the origin
	 */
	public GameCollector getOrigin() {
		return origin;
	}

	/**
	 * @return the epic
	 */
	public GameCollector getEpic() {
		return epic;
	}

	/**
	 * @return the gog
	 */
	public GameCollector getGog() {
		return gog;
	}

	/**
	 * @return the uplay
	 */
	public GameCollector getUplay() {
		return uplay;
	}

	/**
	 * @return the steamID
	 */
	public String getSteamID() {
		return steamID;
	}

	/**
	 * @return the steamKey
	 */
	public String getSteamKey() {
		return steamKey;
	}

	/**
	 * @param steamID the steamID to set
	 */
	public void setSteamID(String steamID) {
		this.steamID = steamID;
	}

	/**
	 * @param steamKey the steamKey to set
	 */
	public void setSteamKey(String steamKey) {
		this.steamKey = steamKey;
	}

	/**
	 * @return the other
	 */
	public GameCollector getOther() {
		return other;
	}

	/**
	 * @return the collectors
	 */
	public GameCollector[] getCollectors() {
		return collectors;
	}

	/**
	 * @return the fullList
	 */
	public ArrayList<Game> getFullList() {
		return fullList;
	}
	
	/**
	 * Gets all the games from every platform initialized
	 */
	public void refreshMainList() {
		fullList.clear();
		for (GameCollector gc : collectors) {
			for(Game g : gc.getGameList()) {
				fullList.add(g);
			}
		}
	}
	
}
