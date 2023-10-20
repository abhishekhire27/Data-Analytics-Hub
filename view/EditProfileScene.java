package view;

import java.io.IOException;

import controller.EditProfileController;
import controller.constants.FXMLFileNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditProfileScene {
	
	private Stage primaryStage;
	private Scene scene;
	
	public EditProfileScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	
	public String getTitle() {
		return "Edit Profile";
	}
	
	public Scene getScene() {
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFileNames.EDIT_PROFILE_PAGE));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		EditProfileController editProfileController = loader.getController();
		editProfileController.setPrimaryStage(primaryStage);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}

}
