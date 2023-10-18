package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import view.AddPostScene;
import view.EditProfileScene;
import view.ExportPostScene;
import view.LoginScene;
import view.MostLikesScene;
import view.RemovePostScene;
import view.RetrievePostScene;

public class MenuController {
	
	private Stage primaryStage;
	
	@FXML
	private Text welcomeMessage;
	
	@FXML
	private Text subscriptionText;
	
	@FXML
	private Text importCsvText;
	
	@FXML
	private Text dataVisualisationText;
	
	@FXML
	private Button importButtonId;
	
	@FXML
	private Button dataVisualisationButtonId;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
//		this.subscriptionText.setText("");
		this.importCsvText.setText("");
		this.dataVisualisationText.setText("");
		
		this.importButtonId.setVisible(false);
		this.dataVisualisationButtonId.setVisible(false);
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
		AddPostScene addPostScene = new AddPostScene(primaryStage);
		primaryStage.setTitle(addPostScene.getTitle());
		primaryStage.setScene(addPostScene.getScene());

		primaryStage.show();
	}

	@FXML
	public void retrievePostButtonHandler(ActionEvent event) {
		RetrievePostScene retrievePostScene = new RetrievePostScene(primaryStage);
		
		primaryStage.setTitle(retrievePostScene.getTitle());
		primaryStage.setScene(retrievePostScene.getScene());
		
		primaryStage.show();
	}

	@FXML
	public void removePostButtonHandler(ActionEvent event) {
		RemovePostScene removePostScene = new RemovePostScene(primaryStage);
		
		primaryStage.setTitle(removePostScene.getTitle());
		primaryStage.setScene(removePostScene.getScene());
		
		primaryStage.show();
	}

	@FXML
	public void nLikesButtonHandler(ActionEvent event) {
		MostLikesScene mostLikesScene = new MostLikesScene(primaryStage);
		
		primaryStage.setTitle(mostLikesScene.getTitle());
		primaryStage.setScene(mostLikesScene.getScene());
		
		primaryStage.show();
	}
	
	@FXML
	public void exportButtonHandler(ActionEvent event) {
		ExportPostScene exportPostScene = new ExportPostScene(primaryStage);
		
		primaryStage.setTitle(exportPostScene.getTitle());
		primaryStage.setScene(exportPostScene.getScene());
		
		primaryStage.show();
	}
	
	@FXML
	public void logoutButtonHandler(ActionEvent event) {
		
		LoginScene loginScene = new LoginScene(primaryStage);
		
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());
		
		primaryStage.show();
	}
	
	@FXML
	public void makeVipMemberHandler(ActionEvent event) {
		
	}
	
	@FXML
	public void importCsvHandler(ActionEvent event) {
		
	}
	
	@FXML
	public void postDataVisualisationHandler(ActionEvent event) {
		
	}

}
