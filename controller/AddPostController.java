package controller;

import java.time.LocalDateTime;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SocialMediaPost;
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
	private DatePicker dateTime;
	
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
		boolean addPostSuccess = operations.addPost(new SocialMediaPost(postId.getText(), content.getText(), author.getText(), likes.getText(), shares.getText(), dateTime.getAccessibleText()));
		
		if(addPostSuccess) {
			postId.setText("");
			content.setText("");
			author.setText("");
			likes.setText("");
			shares.setText("");
			dateTime.setAccessibleText("");;
			postAddingError.setText("Post added successfully");
		}
		else {
			postAddingError.setText("Error while adding the post");
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
