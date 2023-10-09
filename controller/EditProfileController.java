package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditProfileController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField firstNameProfileEdit;
	@FXML
	private TextField lastNameNameProfileEdit;
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
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void updateProfileButton(ActionEvent event) {
		
	}
	
	@FXML
	public void backToMenuHandler(ActionEvent event) {
		
	}

}
