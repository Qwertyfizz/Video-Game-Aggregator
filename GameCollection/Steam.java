import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Steam extends GameCollector {
	private String[] directories = { "C:\\Steam\\steamapps\\common", ".\\steamapps\\common" };
	private String key = "";

	public void getOwnedGames() {
		URL url;
		try {
			url = new URL(
					"https://partner.steam-api.com/ISteamUser/CheckAppOwnership/v2/?key=**********&steamid=76561198050400292&appid=659540");

			try {
				HttpURLConnection con = (HttpURLConnection) url.openConnection();

				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

				String inputLine;

				while ((inputLine = in.readLine()) != null) {
					System.out.println(inputLine);
				}
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
