package view;

import java.io.IOException;

import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginScene {
	
	private Stage primaryStage;
	private Scene scene;
	
	public LoginScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
		scene = null;
	}
	
	public String getTitle() {
		return "Login Page";
	}
	
	public Scene getScene() {
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("login_page.fxml"));
				
		// load the FXML
		Parent parentNode = null;
		try {
			parentNode = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		LoginController loginController = loader.getController();
		loginController.setPrimaryStage(primaryStage);
		
		// create a scene
		Scene scene = new Scene(parentNode);
		
		return scene;
		
	}
	
}









