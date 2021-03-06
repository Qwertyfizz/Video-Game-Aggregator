package aggregator.view;

import java.net.URL;
import java.util.ResourceBundle;

import aggregator.Steam;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the key input prompt
 * @author dylan
 *
 */
public class KeyInputController {

	private DataHolder holder = DataHolder.getInstance();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField apiTextField;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField steamIDTextField;

    @FXML
    private Button doneButton;
    
    @FXML
    void initialize() {
    	
    }
    
    /**
     * Set the steam key data and close the prompt
     */
    @FXML
    void setKeyAndClose() {
    	Steam steam = (Steam) holder.getSteam();
    	steam.setSteamID(steamIDTextField.getText());
    	steam.setKey(apiTextField.getText());
    	
    	System.out.print(holder.getSteamID() + "  " + holder.getSteamKey());
    	close();
    }
    
    /**
     * Close the prompt
     */
    @FXML
    void close() {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    }

}
