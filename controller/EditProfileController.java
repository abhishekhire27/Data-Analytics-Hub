package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.User;
import view.MenuScene;

public class EditProfileController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField firstNameProfileEdit;
	@FXML
	private TextField lastNameProfileEdit;
	@FXML
	private TextField userNameProfileEdit;
	@FXML
	private TextField passwordProfileEdit;
	
	@FXML
	private Text firstNameErrorEditProfile;
	@FXML
	private Text lastNameErrorEditProfile;
	@FXML
	private Text userNameErrorEditProfile;
	@FXML
	private Text passwordErrorEditProfile;
	@FXML
	private Text invalidCredentilsErrorMessage;
	
	public EditProfileController() {
		
	}
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		User loggedInUser = LoggedInUser.getLoggedInUser();
		firstNameProfileEdit.setText(loggedInUser.getFirstName());
		lastNameProfileEdit.setText(loggedInUser.getLastName());
		userNameProfileEdit.setText(loggedInUser.getUserName());
		passwordProfileEdit.setText(loggedInUser.getPassword());
	}
	
	@FXML
	public void updateProfileButton(ActionEvent event) {
		
	}
	
	@FXML
	public void backToMenuHandler(ActionEvent event) {
		MenuScene menuScene = new MenuScene(primaryStage);
		primaryStage.setTitle(menuScene.getTitle());
		primaryStage.setScene(menuScene.getScene());

		primaryStage.show();
	}

}
