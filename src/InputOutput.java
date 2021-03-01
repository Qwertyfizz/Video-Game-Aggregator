import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class InputOutput
{
    public ArrayList<String> readFile(String filename)
    {
        try
        {
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while (reader.hasNextLine())
            {
                list.add(reader.nextLine());
            }
            reader.close();
            return list;
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File \"" + filename + "\" was not found");
            return new ArrayList<>();
        }
    }


    public void writeFile(String filename, ArrayList<String> list)
    {
    	
			try (FileWriter writer = new FileWriter(filename)) {
				for (String s : list) {
					writer.write(s);
				}
			} catch (IOException e1) {
				System.out.println("ERROR:");
				e1.printStackTrace();
			}
		
    }
    
    
    public void createFile(String filepath) {
    	try {
			File newFile = new File(filepath);
			if (newFile.createNewFile()) {
				
			}
		} catch (IOException e) {
			System.out.println("ERROR:");
			e.printStackTrace();
		}
    }

}
