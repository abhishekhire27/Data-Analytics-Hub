package view;

import java.io.IOException;
import controller.RetrievePostController;
import controller.constants.FXMLFileNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RetrievePostScene {
	
	private Stage primaryStage;
	private Scene scene;
	
	public RetrievePostScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	
	public String getTitle() {
		return "Retrieve Post";
	}
	
	public Scene getScene() {
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFileNames.RETRIEVE_POST_PAGE));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		RetrievePostController addPostController = loader.getController();
		addPostController.setPrimaryStage(primaryStage);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}

}
