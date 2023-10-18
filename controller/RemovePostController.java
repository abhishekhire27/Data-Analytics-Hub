package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SocialMediaPost;
import view.MenuScene;

public class RemovePostController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField postId;
	
	@FXML
	private Text postIdRemovalMessage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void deletePostButton(ActionEvent event) {
		DatabaseOperations operations = DatabaseOperations.getInstance();
		boolean removePostSuccess = operations.removePost(postId.getText());
		
		if(removePostSuccess) {
			postId.setText("");
			postIdRemovalMessage.setText("Post deleted successfully");
		}
		else {
			postIdRemovalMessage.setText("Post Id doesn't exist");
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
