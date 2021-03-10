import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	// TODO: Replace test code with CIL driver
	public static void main(String[] args) {
		boolean quit = false;
		GameCollector[] collectors = { new Steam() };// TODO: Add platforms as developed and implement automatic
														// registry detection
		ArrayList<Game> fullList = new ArrayList<>();

		while (!quit) {
			String command = "";
			Scanner input = new Scanner(System.in);

			System.out.println("Commands");
			System.out.println("s - Scan for games");
			System.out.println("l - List games collected");
			System.out.println("q - quit the program");
			System.out.println("f - find a game by name");
			System.out.println("");

			System.out.print("Enter a command: ");

			while (!quit) {
				System.out.print("Enter a command: ");
				switch (selector(input, "slqf")) {
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

					break;
				case "q":
					quit = true;
					System.out.print("Do you want to save (Y/N): ");
					command = selector(input, "YN");
					if (command.equals("Y")) {
						// TODO: Hook in saving system
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

	public static void findGame() {

	}

}
