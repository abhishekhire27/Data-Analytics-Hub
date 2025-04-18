package controller;

import dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import view.LoginScene;

public class RegisterController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField firstNameRegistration;
	@FXML
	private TextField lastNameRegistration;
	@FXML
	private TextField userNameRegistration;
	@FXML
	private PasswordField passwordRegistration;
	
	@FXML
	private Text firstNameErrorRegistration;
	@FXML
	private Text lastNameErrorRegistration;
	@FXML
	private Text userNameErrorRegistration;
	@FXML
	private Text passwordErrorRegistration;
	@FXML
	private Text invalidCredentilsErrorMessage;
	
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	// FUnction to go back to login page
	@FXML
	public void backToLogin(ActionEvent event) {
		LoginScene loginScene = new LoginScene(primaryStage);
		
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());
		
		primaryStage.show();
	}
	
	@FXML
	public void registerHandler(ActionEvent event) {
		// Reseting the error message
		firstNameErrorRegistration.setText("");
		lastNameErrorRegistration.setText("");
		userNameErrorRegistration.setText("");
		passwordErrorRegistration.setText("");
		
		//CHecking if all the data is entered and no fields are left blank
		boolean wrongDataEntered = false;
		if(firstNameRegistration.getText() == null || firstNameRegistration.getText() == "") {
			firstNameErrorRegistration.setText("First name cannot be empty");
			wrongDataEntered = true;
		}
		if(lastNameRegistration.getText() == null || lastNameRegistration.getText() == "") {
			lastNameErrorRegistration.setText("Last name cannot be empty");
			wrongDataEntered = true;
		}
		if(userNameRegistration.getText() == null || userNameRegistration.getText() == "") {
			userNameErrorRegistration.setText("User name cannot be empty");
			wrongDataEntered = true;
		}
		if(passwordRegistration.getText() == null || passwordRegistration.getText() == "") {
			passwordErrorRegistration.setText("Password cannot be empty");
			wrongDataEntered = true;
		}
		
		if(!wrongDataEntered) {
			UserDaoImpl operations = UserDaoImpl.getInstance();
			//Checking if username already exists
			boolean userNameExists = operations.checkUserNameExists(userNameRegistration.getText());
			
			if(userNameExists) {
				userNameErrorRegistration.setText("User name already exists");
			}
			else {
				operations.getLatestUserId();
				// Function to save the user in the database
				boolean userSaved = operations.saveUserInDatabase(new User(firstNameRegistration.getText(), lastNameRegistration.getText(), userNameRegistration.getText(), passwordRegistration.getText()));
				if(userSaved) {
					LoginScene loginScene = new LoginScene(primaryStage);
					primaryStage.setTitle(loginScene.getTitle());
					primaryStage.setScene(loginScene.getScene());

					primaryStage.show();
				}
				else {
					invalidCredentilsErrorMessage.setText("Something went wrong while registering");
			}
				
			}
		}
		
	}

}
