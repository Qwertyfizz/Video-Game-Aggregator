import java.io.File;
import java.util.ArrayList;

public class Main {

	// TODO: Replace test code with CIL driver
	public static void main(String[] args) {
		
		Steam.getOwnedGames();
		
//		GameCollector gc = new GameCollector();
//		ArrayList<String> fp = new ArrayList<>(); 
//		fp.add("D:\\Steam\\steamapps\\common");
//		gc.scan(fp , 'n', PlatformName.STEAM);
//		
//		int none = 0;
//		int multi = 0;
//		for(Game f : gc.getGameList()) { //TODO: Check for dead folders (When uninstalled, some games leave behind their savedata)
//			if (f.getLauncher().contains("No exes are present")) {
//				System.out.println(f.getName());
//				none++;
//			}
//			else if(f.getLauncher().contains(" >= 2 found")) {
//				//System.out.println(f);
//				multi++;
//			}
//			else{
//				//System.out.println(f);
//			}
//		}
//		System.out.println("\nTotal Number of games: " + gc.getGameList().size());
//		System.out.println("No launchers in top domain: " + none);
//		System.out.println("Multiple launchers in top domain: " + multi);
//		System.out.println("Sucess rate: " + (1 - (float)(none+multi)/(float)gc.getGameList().size()));
		
//		Steam steam = new Steam();
//		steam.getInstalledGames();
//		Game toLaunch = steam.getGameByName("Warsim: The Realm of Aslona");
//		toLaunch.launch();
	}

}
