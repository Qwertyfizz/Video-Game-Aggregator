package aggregator.view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import aggregator.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainViewController {
	
	private DataHolder holder = DataHolder.getInstance();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea gameInfoPanel;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem loadButton;

    @FXML
    private ListView<Game> gameList;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private ChoiceBox<PlatformName> platformChoiceBox;
    
    @FXML
    private Button addGameButton;

    @FXML
    private MenuItem saveButton;

    @FXML
    private TextField searchBox;

    @FXML
    private RadioButton showUnstalled;
    
    @FXML
    private MenuItem scanGames;
    
    @FXML
    private Button scanButton;

    @FXML
    void initialize() {
    	platformChoiceBox.getItems().addAll(PlatformName.values());
    	platformChoiceBox.setValue(PlatformName.ALL);
    	
    }
    
    @FXML
    void startGameScan() {
    	try {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("GameScan.fxml"));
    	Parent root = loader.load();
    	
    	Stage stage = new Stage();
    	stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.setTitle("Scan for games");
        stage.show();
    	
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void startNewGame() {
    	try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGame.fxml"));
            Parent root = loader.load();
            
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Add New Game");
            stage.showAndWait();
            
            System.out.println(holder.getOther().getGameList().get(0));
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
}
