package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
		System.out.println("Welcome to login");
		userName.setText("Reuben");
		System.out.println(password.getText());
	}
	
	public void registerHandler(ActionEvent event) {
//		this.switchToRegisterSceneHandler(event);
		System.out.println("Welcome to register");
	}
	
}
