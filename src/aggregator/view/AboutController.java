package aggregator.view;

import java.awt.Desktop;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class AboutController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Hyperlink gitLink;

    @FXML
    void initialize() {
        Desktop desktop = Desktop.getDesktop();
        URL url;
		try {
			url = new URL("https://github.com/Qwertyfizz/Video-Game-Aggregator");
	        URI uri;
			uri = url.toURI();
		    desktop.browse(uri);
		} catch (MalformedURLException e2) {
			e2.printStackTrace();
		} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
