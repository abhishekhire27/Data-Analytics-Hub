package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import view.LoginScene;

public class RegisterController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField firstName;
	
	@FXML
	private TextField lastName;
	
	@FXML
	private TextField userName;
	
	@FXML
	private TextField password;
	
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

//	public void goBackButtonHandler(ActionEvent event) {
//		Button b = (Button) event.getSource();
//		
//		LoginScene imageViewerScene = new LoginScene(primaryStage);
//		
//		primaryStage.setTitle(imageViewerScene.getTitle());
//		primaryStage.setScene(imageViewerScene.getScene());
//		
//		primaryStage.show();
//
//		
//		// can access primary stage here
//		
//	}
//	
//	public void button2Handler(ActionEvent event) {
//		// can access primary stage here		
//	}
	
	@FXML
	public void registerHandler(ActionEvent event) {
		System.out.println(firstName.getText());
	}

}
