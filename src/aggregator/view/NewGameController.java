package aggregator.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aggregator.Game;
import aggregator.PlatformName;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewGameController {

	private DataHolder holder = DataHolder.getInstance();
    
    private File lastBrowsedFile;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameField;

    @FXML
    private TextField fileField;

    @FXML
    private ChoiceBox<PlatformName> platformChoiceBox;

    @FXML
    private Button addGameButton;

    @FXML
    private TextField launcherField;
    
    @FXML
    private Button fileBrowse;
    
    @FXML
    private Button launcherBrowse;

    @FXML
    void initialize() {
    
//    platformChoiceBox.getItems().addAll(PlatformName.values());
//    platformChoiceBox.setValue(PlatformName.OTHER);
    

    }
    
    @FXML
    void selectFileName() {
    	DirectoryChooser browse = new DirectoryChooser();
    	if (lastBrowsedFile != null) {
			browse.setInitialDirectory(lastBrowsedFile);
		}
		browse.setTitle("Select directory");
    	File selection = browse.showDialog(null);
    	try {
    		fileField.setText(selection.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	lastBrowsedFile = new File(selection.getParent());
    	fileField.setDisable(true);
    }
    
    @FXML
    void selectFileLauncher() {
    	FileChooser browse = new FileChooser();
		if (lastBrowsedFile != null) {
			browse.setInitialDirectory(lastBrowsedFile);
		}
		browse.setTitle("Select launcher");
    	File selection = browse.showOpenDialog(null);
    	try {
    		launcherField.setText(selection.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
    	lastBrowsedFile = new File(selection.getParent());
    	launcherField.setDisable(true);
    }
    
    @FXML
    void addGame() {
    	Game toAdd = new Game(nameField.getText(),fileField.getText(),launcherField.getText(),true,PlatformName.OTHER);
    	holder.getOther().addGame(toAdd);
    	
    	close();
    }
    
    
    
    @FXML
    void close() {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    }
}
