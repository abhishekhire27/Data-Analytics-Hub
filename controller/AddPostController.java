package controller;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.SocialMediaPost;
import model.User;
import view.MenuScene;

public class AddPostController {
	
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
	@FXML
	private Text contentError;
	@FXML
	private Text authorError;
	@FXML
	private Text likesError;
	@FXML
	private Text sharesError;
	@FXML
	private Text dateTimeError;
	@FXML
	private Text postAddingError;
	
	private Stage primaryStage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void addPostHandler(ActionEvent event) {
		DatabaseOperations operations = DatabaseOperations.getInstance();
		User loggedInUser = LoggedInUser.getLoggedInUser();
		boolean postIdExists = operations.checkPostIdExists(postId.getText(), loggedInUser.getUserId());
		
		long postLikes = Long.parseLong(likes.getText());
		long postShares = Long.parseLong(shares.getText());
		
		if(postIdExists) {
			postAddingError.setText("Post Id already exists");
		}
		else {
			boolean addPostSuccess = operations.addPost(new SocialMediaPost(postId.getText(), content.getText(), author.getText(), postLikes, postShares, dateTime.getText(), loggedInUser.getUserId()));
			
			if(addPostSuccess) {
				postId.setText("");
				content.setText("");
				author.setText("");
				likes.setText("");
				shares.setText("");
				dateTime.setText("");;
				postAddingError.setText("Post added successfully");
			}
			else {
				postAddingError.setText("Error while adding the post");
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
