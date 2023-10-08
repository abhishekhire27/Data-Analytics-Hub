package view;

import java.io.IOException;

import controller.RegisterController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RegisterScene {
	
	private Stage primaryStage;
	private Scene scene;
	
	public RegisterScene(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	public String getTitle() {
		return "Register Page";
	}
	
	public Scene getScene() {
		
		if(scene != null) {
			return scene;
		}
		
		// load FXML
		FXMLLoader loader = new FXMLLoader(getClass().getResource("registration_page.fxml"));
				
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
		
		
		
		
//		Pane pane = new Pane();
//		
//		Button goBackButton = new Button("Go Back");
//
//		 goBackButton.setOnAction(new GoBackButtonController());
//		
//		goBackButton.setOnAction(event -> {
//			// controller logic
//			System.out.println("button clicked");
//		});
//		
//		SecondSceneController controller = new SecondSceneController();
//		controller.setPrimaryStage(primaryStage);
//		
//		goBackButton.setOnAction(controller::goBackButtonHandler);
//		
//		Button b2 = new Button();
//		
//		b2.setOnAction(controller::button2Handler);
//		
//		
//		
//		pane.getChildren().add(goBackButton);
//		pane.setPadding(new Insets(10, 10, 10 , 10));
//		
//		// create a scene
//		Scene scene = new Scene(pane);
//		
//		return scene;
		
	}
	
	
}
