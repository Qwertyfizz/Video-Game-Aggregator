import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import com.registry.*;

public class InputOutput {
	public static ArrayList<String> readFile(String filename) {
		File file = new File(filename);
		try (Scanner reader = new Scanner(file)) {
			ArrayList<String> list = new ArrayList<>();
			while (reader.hasNextLine()) {
				list.add(reader.nextLine());
			}
			return list;
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + filename + "\" was not found");
			return new ArrayList<>();
		}
	}

	public static void writeFile(String filename, ArrayList<String> list) {

		try (FileWriter writer = new FileWriter(filename)) {
			for (String s : list) {
				writer.write(s);
			}
		} catch (IOException e1) {
			System.out.println("ERROR:");
			e1.printStackTrace();
		}

	}

	public static void createFile(String filepath) {
		try {
			File newFile = new File(filepath);
			if (newFile.createNewFile()) {

			}
		} catch (IOException e) {
			System.out.println("ERROR:");
			e.printStackTrace();
		}
	}

	public static String processRequest(URL url) {
		try {
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String inputLine;
			String output;

			inputLine = in.readLine();
			in.close();

			return inputLine;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	public static String readRegistry(String keyLoc, String keyName) {
		RegistryKey key = new RegistryKey(keyLoc);
		RegistryValue value = key.getValue(keyName);
		return value.toString();
	}
	
	public static String readRegistry(String root, String keyLoc, String keyName) {
		RegistryKey rootLoc = new RegistryKey(root);
		RegistryKey key = new RegistryKey(rootLoc, keyLoc);
		RegistryValue value = key.getValue(keyName);
		return value.toString();
	}
}
