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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class NewGameController {

	private DataHolder holder = DataHolder.getInstance();
	
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
    
    private File lastBrowsedFile;

    @FXML
    void initialize() {
    
    platformChoiceBox.getItems().addAll(PlatformName.values());
    

    }
    
    @FXML
    void helloWorld() {
    	System.out.println("helloWorld!");
    }
    
    @FXML
    void selectFileName() {
    	FileChooser browse = new FileChooser();
    	if (lastBrowsedFile != null) {
			browse.setInitialDirectory(lastBrowsedFile);
		}
		browse.setTitle("Select directory");
    	File selection = browse.showOpenDialog(null);
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
    	Game toAdd = new Game(nameField.getText(),fileField.getText(),launcherField.getText(),true,platformChoiceBox.getValue());
    	
    	switch(platformChoiceBox.getValue()) {
    	case STEAM:
    		holder.getSteam().addGame(toAdd);
    		break;
    	case ORIGIN:
    		holder.getOrigin().addGame(toAdd);
    		break;
    	case EPIC:
    		holder.getEpic().addGame(toAdd);
    		break;
    	case GOG:
    		holder.getGog().addGame(toAdd);
    		break;
    	case UPLAY:
    		holder.getUplay().addGame(toAdd);
    		break;
    	case OTHER:
    		holder.getOther().addGame(toAdd);
    		break;
    	default:
    		holder.getOther().addGame(toAdd);
    		break;
    	}
    	
    	close();
    }
    
    @FXML
    void close() {
    	Stage stage = (Stage) cancelButton.getScene().getWindow();
    	stage.close();
    }
}
