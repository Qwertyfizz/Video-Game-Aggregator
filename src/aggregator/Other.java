package aggregator;

/**
 * 
 * Platform class for manually added games
 * 
 * @author dylan
 *
 */
public class Other extends GameCollector {

	@Override
	public void scan() {
		//All Other games are manually added and can be in any location in the filesystem
		//As such, there is is nothing to scan
	}

	
	/**
	 * Checks if the platform is installed
	 * @return true
	 * 			always true, as there can always be custom games
	 */
	@Override
	public boolean checkForPlatform() {
		return true;
	}

}
