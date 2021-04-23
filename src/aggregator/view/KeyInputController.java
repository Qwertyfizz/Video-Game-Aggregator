package aggregator.view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    
    @FXML
    void setKeyAndClose() {
    	holder.setSteamID(steamIDTextField.getText());
    	holder.setSteamKey(apiTextField.getText());
    	
    	System.out.print(holder.getSteamID() + "  " + holder.getSteamKey());
    	close();
    }
    
    @FXML
    void close() {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    }

}
