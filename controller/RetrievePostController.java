package controller;

import dao.PostDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.SocialMediaPost;
import model.User;
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
	private TextField dateTime;
	
	@FXML
	private Text postIdError;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	// Function to handle the retrieve post button
	@FXML
	public void retrievePostHandler(ActionEvent event) {
		PostDaoImpl operations = PostDaoImpl.getInstance();
		User loggedInUser = LoggedInUser.getLoggedInUser();
		SocialMediaPost socialMediaPost = operations.retrievePost(postId.getText(), loggedInUser.getUserId());
		
		// Setting the retrived data into the text fields
		if(socialMediaPost != null) {
			content.setText(socialMediaPost.getContent());
			author.setText(socialMediaPost.getAuthor());
			likes.setText(String.valueOf(socialMediaPost.getLikes()));
			shares.setText(String.valueOf(socialMediaPost.getShares()));
			dateTime.setText(socialMediaPost.getDateTime());
		}
		else {
			postIdError.setText("Post Id doesn't exist");
			content.setText("");
			author.setText("");
			likes.setText("");
			shares.setText("");
			dateTime.setText("");
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
