package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SocialMediaPost;
import view.MenuScene;

public class RetrievePostController {
	
private Stage primaryStage;
	
	@FXML
	private TextField postId;
	@FXML
	private TextField content;
	@FXML
	private TextField author;
	@FXML
	private TextField likes;
	@FXML
	private TextField shares;
	@FXML
	private DatePicker dateTime;
	
	@FXML
	private Text postIdError;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void retrievePostHandler(ActionEvent event) {
		DatabaseOperations operations = DatabaseOperations.getInstance();
		SocialMediaPost socialMediaPost = operations.retrievePost(postId.getText());
		
		if(socialMediaPost != null) {
			content.setText(socialMediaPost.getContent());
			author.setText(socialMediaPost.getAuthor());
			likes.setText(socialMediaPost.getLikes());
			shares.setText(socialMediaPost.getShares());
			dateTime.setAccessibleText(socialMediaPost.getDateTime());
		}
		else {
			postIdError.setText("Post Id doesn't exist");
			content.setText("");
			author.setText("");
			likes.setText("");
			shares.setText("");
			dateTime.setAccessibleText("");
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
