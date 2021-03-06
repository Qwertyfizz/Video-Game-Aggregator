package aggregator.view;

import java.net.URL;
import java.util.ResourceBundle;

import aggregator.GameCollector;
import aggregator.Steam;
import aggregator.constants.PlatformName;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Window that scans the computer and displays the number of games found
 * @author dylan
 *
 */
public class GameScanController {

	private DataHolder holder = DataHolder.getInstance();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label uplayNum;

    @FXML
    private Label epicNum;

    @FXML
    private Label gogNum;

    @FXML
    private Label steamNum;

    @FXML
    private Label originNum;
    
    @FXML
    private Button doneButton;

    /**
     * Calls the scan function upon launch
     */
    @FXML
    void initialize() {
        preformScan();
    }
    
    /**
     * Scan every platform detected on the computer
     */
    @FXML
    void preformScan() {
    	GameCollector[] collectors = holder.getCollectors();
        for(GameCollector gc : collectors) {
        	gc.getGameList().clear();
        	gc.scan();
        	switch(PlatformName.valueOf(gc.getClass().toString().replace("class aggregator.", "").toUpperCase())) {
			case EPIC:
				epicNum.setText(gc.getGameList().size() + " games found");
				break;
			case GOG:
				gogNum.setText(gc.getGameList().size() + " games found");
				break;
			case ORIGIN:
				originNum.setText(gc.getGameList().size() + " games found");
				break;
			case STEAM:
				if (!Steam.getKey().isEmpty()) {
					((Steam) gc).getOwnedGames();
				}
				steamNum.setText(gc.getGameList().size() + " games found");
				break;
			case UPLAY:
				uplayNum.setText(gc.getGameList().size() + " games found");
				break;
			default:
				break;
        	}
        }
        
    }
    
    /**
     * Close the window
     */
    @FXML
    void close() {
    	Stage stage = (Stage) doneButton.getScene().getWindow();
    	stage.close();
    }
}
