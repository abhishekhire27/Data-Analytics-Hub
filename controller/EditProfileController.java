package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.User;
import view.LoginScene;
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
		// Reseting the error message
		firstNameErrorEditProfile.setText("");
		lastNameErrorEditProfile.setText("");
		userNameErrorEditProfile.setText("");
		passwordErrorEditProfile.setText("");
				
		boolean wrongDataEntered = false;
		if(firstNameProfileEdit.getText() == null || firstNameProfileEdit.getText() == "") {
			firstNameErrorEditProfile.setText("First name cannot be empty");
			wrongDataEntered = true;
		}
		if(lastNameProfileEdit.getText() == null || lastNameProfileEdit.getText() == "") {
			lastNameErrorEditProfile.setText("Last name cannot be empty");
			wrongDataEntered = true;
		}
		if(userNameProfileEdit.getText() == null || userNameProfileEdit.getText() == "") {
			userNameErrorEditProfile.setText("User name cannot be empty");
			wrongDataEntered = true;
		}
		if(passwordProfileEdit.getText() == null || passwordProfileEdit.getText() == "") {
			passwordErrorEditProfile.setText("Password cannot be empty");
			wrongDataEntered = true;
		}
		
		if(!wrongDataEntered) {
			User loggedInUser = LoggedInUser.getLoggedInUser();
			DatabaseOperations operations = DatabaseOperations.getInstance();
			
			boolean userNameExists = false;
			if(!loggedInUser.getUserName().equals(userNameProfileEdit.getText())) {
				userNameExists = operations.checkUserNameExists(userNameProfileEdit.getText());
			}
			
			if(userNameExists) {
				userNameErrorEditProfile.setText("User name already exists");
			}
			else {
				User user = new User();
				
				user.setUserId(loggedInUser.getUserId());
				user.setFirstName(firstNameProfileEdit.getText());
				user.setLastName(lastNameProfileEdit.getText());
				user.setUserName(userNameProfileEdit.getText());
				user.setPassword(passwordProfileEdit.getText());
				user.setVipMember(loggedInUser.isVipMember());
				
				boolean userUpdated = operations.updateUserInDatabase(user);
				if(userUpdated) {
					LoggedInUser.setInstance(user);
					invalidCredentilsErrorMessage.setText("Profile Updated successfully");
				}
				else {
					invalidCredentilsErrorMessage.setText("Something went wrong while registering");
				}
			}
		}
	}
	
	@FXML
	public void backToMenuHandler(ActionEvent event) {
		MenuScene menuScene = new MenuScene(primaryStage);
		primaryStage.setTitle(menuScene.getTitle());
		primaryStage.setScene(menuScene.getScene());

		primaryStage.show();
	}

}
