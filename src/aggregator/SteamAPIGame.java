/**
 * 
 */
package aggregator;


/**
 * This class handles the storage and processing of the information from the returned API JSON.
 * NOTE: For the purposes of this project, the value ""has_community_visible_stats": (boolean)," has no use and is ignored. Thus, it must be stripped out of the original JSON line
 */
public class SteamAPIGame {

	int appid;
	String name;
	int playtime_forever;
	String img_icon_url;
	String img_logo_url;
	String playtime_windows_forever;
	String playtime_mac_forever;
	String playtime_linux_forever;
	
	/**
	 * Constructor for a SteamAPIGame
	 * 
	 * @param appid
	 * @param name
	 * @param playtime_forever
	 * @param img_icon_url
	 * @param img_logo_url
	 * @param playtime_windows_forever
	 * @param playtime_mac_forever
	 * @param playtime_linux_forever
	 */
	public SteamAPIGame(int appid, String name, int playtime_forever, String img_icon_url, String img_logo_url,
			String playtime_windows_forever, String playtime_mac_forever, String playtime_linux_forever) {
		this.appid = appid;
		this.name = name;
		this.playtime_forever = playtime_forever;
		this.img_icon_url = img_icon_url;
		this.img_logo_url = img_logo_url;
		this.playtime_windows_forever = playtime_windows_forever;
		this.playtime_mac_forever = playtime_mac_forever;
		this.playtime_linux_forever = playtime_linux_forever;
	}

	/**
	 * @return the appid
	 */
	public int getAppid() {
		return appid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the playtime_forever
	 */
	public int getPlaytime_forever() {
		return playtime_forever;
	}

	/**
	 * @return the img_icon_url
	 */
	public String getImg_icon_url() {
		return img_icon_url;
	}

	/**
	 * @return the img_logo_url
	 */
	public String getImg_logo_url() {
		return img_logo_url;
	}

	/**
	 * @return the playtime_windows_forever
	 */
	public String getPlaytime_windows_forever() {
		return playtime_windows_forever;
	}

	/**
	 * @return the playtime_mac_forever
	 */
	public String getPlaytime_mac_forever() {
		return playtime_mac_forever;
	}

	/**
	 * @return the playtime_linux_forever
	 */
	public String getPlaytime_linux_forever() {
		return playtime_linux_forever;
	}

	/**
	 * Displays a generic string of the 
	 */
	@Override
	public String toString() {
		return "SteamAPIGame [appid=" + appid + ", name=" + name + ", playtime_forever=" + playtime_forever
				+ ", img_icon_url=" + img_icon_url + ", img_logo_url=" + img_logo_url + ", playtime_windows_forever="
				+ playtime_windows_forever + ", playtime_mac_forever=" + playtime_mac_forever
				+ ", playtime_linux_forever=" + playtime_linux_forever + "]";
	}
	
	
}
