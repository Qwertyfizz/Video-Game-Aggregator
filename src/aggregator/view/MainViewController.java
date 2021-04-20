package aggregator.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import aggregator.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
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
	private PlatformName platformFilter = PlatformName.ALL;
	private ArrayList<Game> displayedList;

    @FXML
    private Button searchButton;

    @FXML
    private MenuItem addGameMenu;

    @FXML
    private MenuItem loadButton;

    @FXML
    private MenuItem scanGamesMenu;

    @FXML
    private MenuItem aboutButton;

    @FXML
    private ChoiceBox<PlatformName> platformChoiceBox;

    @FXML
    private Button addGameButton;

    @FXML
    private Button scanButton;

    @FXML
    private MenuItem closeButton;

    @FXML
    private ListView<Game> gameList;

    @FXML
    private MenuItem saveButton;

    @FXML
    private TextField searchBox;

    @FXML
    private RadioButton showUnstalled;
    
    @FXML
    private TextArea gameInfoTextField;
    
    @FXML
    private Button launchGameButton;
    
    @FXML
    private Button openLocationButton;

    @FXML
    void initialize() {
    	
    	gameInfoTextField.setEditable(false);
    	
    	platformChoiceBox.getItems().addAll(PlatformName.values());
    	platformChoiceBox.setValue(PlatformName.ALL);
    	platformChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				filterByPlatform(platformChoiceBox.getItems().get(newValue.intValue()));
			}
		});
    	
    	gameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Game>() {

			@Override
			public void changed(ObservableValue<? extends Game> observable, Game oldValue, Game newValue) {
				if(!newValue.getIsInstalled()) {
					launchGameButton.setDisable(true);
				}
				else {
					launchGameButton.setDisable(false);
				}
				displayGameInfo(newValue);
			}
		});
    	
    	searchBox.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				filterByName();
			}
		});
    	
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
        stage.setResizable(false);
        stage.showAndWait();
        updateMainList();
    	
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
        	stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setTitle("Add New Game");
            stage.setResizable(false);
            stage.showAndWait();
    
        } catch (IOException e) {
            e.printStackTrace();
        }
    	
    }
    
    @FXML
    void saveInfo() {
    	for(GameCollector gc : holder.getCollectors()) {
    		gc.saveToFile();
    	}
    }
    
    @FXML
    void loadInfo() {
    	for(GameCollector gc : holder.getCollectors()) {
    		gc.getGameList().clear();
    		gc.loadFromFile();
    	}
    	updateMainList();
    }
    
    private void displayGameInfo(Game g) {
    	StringBuilder gameInfo = new StringBuilder();
    	gameInfo.append("Name: " + g.getName() + "\n\n" );
    	if(g.getAppID() != -1) {
    		gameInfo.append("Application ID: " + g.getAppID() + "\n\n");
    	}
    	gameInfo.append("Filepath: " + g.getFilepath() + "\n\n" );
    	if(g.getPlaytime() != -1) {
    		gameInfo.append("Playtime: " + parsePlaytime(g.getPlaytime()) + "\n\n");
    	}
    	gameInfo.append("Platform: " + g.getPlatform().toString().toLowerCase() + "\n\n" );
    	if (!g.getExe().equals("none")) {
			gameInfo.append("Launcher: " + g.getExe() + "\n\n");
		}
		if(g.getIsInstalled()) {
    		gameInfo.append("This game is currently installed");
    	}
    	else {
    		gameInfo.append("This game is not installed");
    	}
    	
    	gameInfoTextField.setText(gameInfo.toString());
    }

    private void filterByPlatform(PlatformName selection) {
    	gameList.getItems().clear();
    	if (selection == PlatformName.ALL) {
    		updateMainList();
    	}
    	else {
    		for(Game g : holder.getFullList()) {
    			if(g.getPlatform() == selection) {
    				gameList.getItems().add(g);
    			}
    		}
    	}
    }
    
    @FXML
    void filterByName() {
    	updateMainList();
    	ArrayList<Game> temp = new ArrayList<>();
    	String toFind = searchBox.getText();
    	if(toFind.equals("") || toFind == null) {
    		updateMainList();
    	}
    	else {
    		for(Game g : displayedList) {
    			if (g.getName().toLowerCase().contains(toFind.toLowerCase())) {
    				temp.add(g);
    			}
    		}
    		displayedList.clear();
    		displayedList.addAll(temp);
    		gameList.getItems().clear();
    		gameList.getItems().addAll(displayedList);
    	}
    }
    
    @FXML
    private void launchGame() {
    	gameList.getSelectionModel().getSelectedItem().launch();
    }
    
    @FXML
    void openFileLocation() {
    	String filepath = gameList.getSelectionModel().getSelectedItem().getFilepath();
    	try {
			Desktop.getDesktop().open(new File(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private String parsePlaytime(int rawTime) {
    	
    	if (rawTime == 0) {
    		return "Unplayed";
    	}
    	
    	int hours = rawTime / 60;
    	int min = rawTime % 60;
    	
    	if (hours == 0) {
    		if (min >= 2) {
				return min + " minutes";
			}
    		else {
    			return min + " minute";
    		}
    	}
    	else if (min == 0) {
    		if (hours >= 2) {
				return hours + " hours";
			}
    		else {
    			return hours + " hour";
    		}
    	}
    	else {
    		String parsedString = "";
    		if (hours >= 2) {
    			parsedString += hours + " hours";
			}
    		else {
    			parsedString += hours + " hour";
    		}
    		
    		if (min >= 2) {
    			parsedString += min + " minutes";
			}
    		else {
    			parsedString += min + " minute";
    		}
    		
    		return parsedString;
    		
    	}
    	
    }
    
    private void updateMainList() {
    	holder.refreshMainList();
    	displayedList = new ArrayList<>(holder.getFullList());
    	gameList.getItems().addAll(displayedList);
    }
}
