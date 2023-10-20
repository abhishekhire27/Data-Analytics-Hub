package controller;

import java.util.HashMap;

import dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.User;
import view.MenuScene;
import view.RegisterScene;

public class LoginController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField userName;
	@FXML
	private PasswordField password;
	
	@FXML
	private Text userNameErrorLogin;
	@FXML
	private Text passwordErrorLogin;
	@FXML
	private Text invalidCredentialsMessage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	// Function to go to the registration page
	@FXML
	public void switchToRegisterSceneHandler(ActionEvent event) {
		
		RegisterScene registerScene = new RegisterScene(primaryStage);
		primaryStage.setTitle(registerScene.getTitle());
		primaryStage.setScene(registerScene.getScene());

		primaryStage.show();
		
	}
	
	public void loginHandler(ActionEvent event) {
		// Reseting the error message
		userNameErrorLogin.setText("");
		passwordErrorLogin.setText("");
		
		//Checking if both username and password is entered
		boolean wrongCredentialsEntered = false;
		if(userName.getText() == null || userName.getText() == "") {
			userNameErrorLogin.setText("User name cannot be empty");
			wrongCredentialsEntered = true;
		}
		if(password.getText() == null || password.getText() == "") {
			passwordErrorLogin.setText("Password cannot be empty");
			wrongCredentialsEntered = true;
		}
		if(!wrongCredentialsEntered) {
			UserDaoImpl operations = UserDaoImpl.getInstance();
			HashMap<String, Object> returnMap = operations.loginUser(userName.getText(), password.getText());
			
			// If username and password matches in the database
			if((boolean)returnMap.get("loginSuccess")) {
				LoggedInUser.setInstance((User)returnMap.get("user"));
				MenuScene menuScene = new MenuScene(primaryStage);
				primaryStage.setTitle(menuScene.getTitle());
				primaryStage.setScene(menuScene.getScene());

				primaryStage.show();
			}
			else {
				invalidCredentialsMessage.setText("Username and password doesn't match");
			}
		}

	}
	
}
