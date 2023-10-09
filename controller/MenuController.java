package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.EditProfileScene;
import view.LoginScene;

public class MenuController {
	
	private Stage primaryStage;
	
	@FXML
	private Text welcomeMessage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void updateProfileButtonHandler(ActionEvent event) {
		EditProfileScene editProfileScene = new EditProfileScene(primaryStage);
		primaryStage.setTitle(editProfileScene.getTitle());
		primaryStage.setScene(editProfileScene.getScene());

		primaryStage.show();
	}

	@FXML
	public void addPostButtonHandler(ActionEvent event) {
		
	}

	@FXML
	public void retrievePostButtonHandler(ActionEvent event) {
		
	}

	@FXML
	public void removePostButtonHandler(ActionEvent event) {
		
	}

	@FXML
	public void nLikesButtonHandler(ActionEvent event) {
		
	}
	
	@FXML
	public void exportButtonHandler(ActionEvent event) {
		
	}
	
	@FXML
	public void logoutButtonHandler(ActionEvent event) {
		
		LoginScene loginScene = new LoginScene(primaryStage);
		
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());
		
		primaryStage.show();
	}

}
