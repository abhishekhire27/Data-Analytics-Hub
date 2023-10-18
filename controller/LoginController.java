package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.User;
import view.MenuScene;
import view.RegisterScene;

public class LoginController {
	
	private Stage primaryStage;
	
//	@FXML
//	private ImageView imageView;
	
//	@FXML
//	private MenuItem clearMenuItem;
	
	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	
	@FXML
	private Text userNameErrorLogin;
	@FXML
	private Text passwordErrorLogin;
	@FXML
	private Text invalidCredentialsMessage;
	
//	@FXML
//	private void initialize() {
//		clearMenuItem.setDisable(true);
//	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
//	@FXML
//	public void chooseImageHandler(ActionEvent event) {
//		
//		FileChooser fileChooser = new FileChooser();
//		
//		fileChooser.getExtensionFilters().add(new ExtensionFilter("images", "*.png", "*.jpeg", "*.jpg", "*.gif"));
//		
//		File file = fileChooser.showOpenDialog(primaryStage);
//		
//		System.out.println(file.getName());
//		
//		try {
//			imageView.setImage(new Image(new FileInputStream(file)));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		
////		clearMenuItem.setDisable(false);
//	}

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
			DatabaseOperations operations = DatabaseOperations.getInstance();
			HashMap<String, Object> returnMap = operations.loginUser(userName.getText(), password.getText());
			
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
	
	private boolean checkCredentials() {
		System.out.println("Checking Credentials");
		return true;
	}
	
}
