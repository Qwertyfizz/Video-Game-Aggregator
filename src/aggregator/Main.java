package aggregator;

import java.util.ArrayList;

/**
 * Main method for redirecting to the GUI Launcher
 * This is a workaround for a known bug with javafx and java's class handling
 */
public class Main {

	public static void main(String[] args) {
		GUILauncher.main(args);
//		GameCollector orTest = new Origin();
//		orTest.scan();
//		ArrayList<Game> g = orTest.getGameList();
//		
//		for (Game e : g) {
//			System.out.println(e);
//			System.out.println(e.getFilepath());
//			System.out.println(e.getAlphAppID());
//		}
	}

}
