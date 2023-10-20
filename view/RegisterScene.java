package view;

import java.io.IOException;

import controller.RegisterController;
import controller.constants.FXMLFileNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterScene {
	
	private Stage primaryStage;
	private Scene scene;
	
	public RegisterScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public String getTitle() {
		return "Registration Page";
	}
	
	public Scene getScene() {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFileNames.REGISTRATION_PAGE));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RegisterController registerController = loader.getController();
		registerController.setPrimaryStage(primaryStage);

		// create a scene
		Scene scene = new Scene(parentNode);
		return scene;
		
	}
	
	
}
