import java.util.ArrayList;
import java.util.Scanner;

import com.registry.RegistryKey;

public class Main {

	public static void main(String[] args) {
		boolean quit = false;
		GameCollector[] collectors = {new Steam(), new GOG()};

		ArrayList<Game> fullList = new ArrayList<>();
		
		while (!quit) {
			String command = "";
			Scanner input = new Scanner(System.in);

			System.out.println("Commands");
			System.out.println("s - Scan for games");
			System.out.println("l - List games collected");
			System.out.println("q - quit the program");
			System.out.println("f - find a game by name");
			System.out.println("L - Load saved information");
			System.out.println("S - Save current information");
			System.out.println("");

			while (!quit) {
				System.out.print("Enter a command: ");
				switch (selector(input, "slqfL")) {
				case "s":
					if (!fullList.isEmpty()) {
						fullList.clear();
					}
					fullList = collectGames(collectors);
					System.out.println(fullList.size() + " games collected");
					break;
				case "l":
					System.out.println("Games Collected:\n");
					for (Game g : fullList) {
						System.out.println(g.getName());
					}
					break;
				case "f":
					Game found = findGame(input, fullList);
					gameSelection(found, input);
					break;
				case "L":
					for (GameCollector gc : collectors) {
						gc.loadFromFile();
						for (Game g : gc.getGameList()) {
							fullList.add(g);
						}
					}
					break;
				case "q":
					quit = true;
					System.out.print("Do you want to save (Y/N): ");
					command = selector(input, "YN");
					if (command.equals("Y")) {
						for (GameCollector gc : collectors) {
							gc.saveToFile();
						}
					}
					break;
				default:
					System.out.println("!!INVALID COMMAND ACCEPTED!!");
				}
			}
			input.close();
			break;
		}
	}

	public static String selector(Scanner input, String commands) {
		while (true) {
			String selection = input.nextLine();
			if (commands.contains(selection)) {
				if (selection.length() == 1) {
					return selection;
				}
			} else {
				System.out.println("Invalid command");
				System.out.print("Please try again: ");
			}
		}

	}

	public static ArrayList<Game> collectGames(GameCollector[] collectors) {
		ArrayList<Game> fullList = new ArrayList<>();
		for (GameCollector platform : collectors) {
			platform.scan();
			fullList.addAll(platform.getGameList());
		}
		return fullList;
	}

	public static Game findGame(Scanner input, ArrayList<Game> fullList) {
		System.out.print("Enter a name: ");
		ArrayList<Game> narrowed = new ArrayList<>();
		boolean quit = false;
		
		while (!quit) {
			String name = input.nextLine();
			for (Game g : fullList) {
				if (g.getName().toLowerCase().equals(name.toLowerCase())) {
					return g;
				} else if (g.getName().toLowerCase().contains(name.toLowerCase())) {
					narrowed.add(g);
				}
			}
			if (narrowed.size() == 1) {
				return narrowed.get(0);
			}
			else if (narrowed.isEmpty()){
				System.out.println("No games with \"" + name + "\" found");
				return null;
			}
			int i = 1;
			System.out.println("Game names containing \"" + name + "\"\n");
			for(Game g : narrowed) {
				System.out.println(i + ") " + g.getName());
				i++;
			}
			System.out.print("\nEnter the number of the game you want (or q to exit): ");
			while (!quit) {
				try {
					String selection = input.nextLine();
					if (selection.equals("q")) {
						quit = true;
						break;
					}
					int toInt = Integer.parseInt(selection);
					if (toInt > i || toInt <= 0) {
						System.out.print("Input must be beetween 1 and " + i + ": ");
						continue;
					}
					
					return narrowed.get(toInt-1);
					
				} catch (NumberFormatException e) {
					System.out.print("Input must be a number or q: ");
				}
			}
		}
		return null;
	}
	
	public static void gameSelection(Game game, Scanner input) {
		System.out.print("Game Information\n");
		System.out.println("Name: " + game.getName());
		System.out.println("Platform: " + game.getPlatform());
		if (game.getIsInstalled()) {
			System.out.println("Filepath: " + game.getFilepath());
			System.out.println("This game is installed\n");
			System.out.print("Enter L to launch or q to return to the menu: ");
			String choice = selector(input, "Lq");
			if (choice.equals("L")) {
				game.launch();
			}
		}
		else {

			System.out.println("This game is not installed\n");
			System.out.print("Press any button to return to the menu");
			input.nextLine();
		}
		
	}

}
