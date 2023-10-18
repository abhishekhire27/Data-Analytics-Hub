package view;

import java.io.IOException;

import controller.ExportPostController;
import controller.MostLikesController;
import controller.constants.FXMLFileNames;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ExportPostScene {

	private Stage primaryStage;
	private Scene scene;
	
	public ExportPostScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	
	public String getTitle() {
		return "Export Post Page";
	}
	
	public Scene getScene() {
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource(FXMLFileNames.EXPORT_POST_PAGE));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ExportPostController exportPostController = loader.getController();
		exportPostController.setPrimaryStage(primaryStage);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
	}
}
