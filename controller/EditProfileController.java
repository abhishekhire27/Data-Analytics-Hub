package controller;

import dao.UserDaoImpl;
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
	
	// Fields which are used in .fxml files
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
	
	// TO set this page as the primary stage
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
				
		// Checking one by one if any fields are empty. If yes, give the error message
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
		
		// If all the data entered is correct
		if(!wrongDataEntered) {
			User loggedInUser = LoggedInUser.getLoggedInUser();
			UserDaoImpl operations = UserDaoImpl.getInstance();
			
			// Check if username is already present
			boolean userNameExists = false;
			if(!loggedInUser.getUserName().equals(userNameProfileEdit.getText())) {
				userNameExists = operations.checkUserNameExists(userNameProfileEdit.getText());
			}
			
			// If present, then give the error message and don't allow to edit the profile
			if(userNameExists) {
				userNameErrorEditProfile.setText("User name already exists");
			}
			else {
				// If not present, call a function to update the user in the database
				User user = new User();
				
				user.setUserId(loggedInUser.getUserId());
				user.setFirstName(firstNameProfileEdit.getText());
				user.setLastName(lastNameProfileEdit.getText());
				user.setUserName(userNameProfileEdit.getText());
				user.setPassword(passwordProfileEdit.getText());
				user.setVipMember(loggedInUser.isVipMember());
				
				boolean userUpdated = operations.updateUserInDatabase(user);
				//To check if the update operation in the database is successful for not.
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
	
	// Function to go back to main menu
	@FXML
	public void backToMenuHandler(ActionEvent event) {
		MenuScene menuScene = new MenuScene(primaryStage);
		primaryStage.setTitle(menuScene.getTitle());
		primaryStage.setScene(menuScene.getScene());

		primaryStage.show();
	}

}
