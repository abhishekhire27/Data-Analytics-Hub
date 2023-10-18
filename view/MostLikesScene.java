package view;

import java.io.IOException;

import controller.AddPostController;
import controller.MostLikesController;
import controller.constants.FXMLFileNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MostLikesScene {
	
	private Stage primaryStage;
	private Scene scene;
	
	public MostLikesScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	
	public String getTitle() {
		return "Most Likes Post";
	}
	
	public Scene getScene() {
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFileNames.MOST_LIKES_PAGE));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MostLikesController mostLikesController = loader.getController();
		mostLikesController.setPrimaryStage(primaryStage);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
	}

}
