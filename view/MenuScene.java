package view;

import java.io.IOException;

import controller.MenuController;
import controller.constants.FXMLFileNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuScene {
	private Stage primaryStage;
	private Scene scene;
	
	public MenuScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	
	public String getTitle() {
		return "Menu Page";
	}
	
	public Scene getScene() {
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFileNames.MENU_PAGE));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MenuController menuController = loader.getController();
		menuController.setPrimaryStage(primaryStage);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
}
