package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

public class AddPostController {
	
	// Fields which are used in .fxml files
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
		// Since it is an singleton class, the getInstance will always give you the same object. So no multiple database connections are formed.
		PostDaoImpl operations = PostDaoImpl.getInstance();
		// Since it is an singleton class, the getInstance will always give you the same object. So no multiple logged In usr objects are formed.
		User loggedInUser = LoggedInUser.getLoggedInUser();
		boolean postIdExists = operations.checkPostIdExists(postId.getText(), loggedInUser.getUserId());
		long postLikes = 0;
		long postShares = 0;
		
		// Set the error message to empty
		postIdError.setText("");
		contentError.setText("");
		authorError.setText("");
		likesError.setText("");
		sharesError.setText("");
		dateTimeError.setText("");;
		
		boolean correctDataEntered = true;
		try {
			//Checking if the user has entered the number and not the string
			postLikes = Long.parseLong(likes.getText());
		}
		catch(Exception e) {
			//If exception, the parsing has failed and user has added some invalid input
			likesError.setText("Likes can only be numbers");
			correctDataEntered = false;
		}
		
		try {
			//Checking if the user has entered the number and not the string
			postShares = Long.parseLong(shares.getText());
		}
		catch(Exception e) {
			//If exception, the parsing has failed and user has added some invalid input
			sharesError.setText("Shares can only be numbers");
			correctDataEntered = false;
		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy HH:mm");
		try {
			//Checking if the user has entered the date time in correct format
			LocalDateTime.parse(dateTime.getText(),formatter);
		}
		catch(Exception e) {
			//If exception, the parsing has failed and user has added some invalid input
			dateTimeError.setText("Please enter the date in correct format");
			correctDataEntered = false;
		}
		
		if(postIdExists) {
			//If the post Id is duplicated
			postAddingError.setText("Post Id already exists");
		}
		else if(!correctDataEntered) {
			//pass
		}
		else {
			boolean addPostSuccess = operations.addPost(new SocialMediaPost(postId.getText(), content.getText(), author.getText(), postLikes, postShares, dateTime.getText(), loggedInUser.getUserId()));
			
			//If post added successfully, reset the text fields.
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
	
	// Function to set the go back to menu
	@FXML
	public void backToMenuHandler(ActionEvent event) {
		MenuScene menuScene = new MenuScene(primaryStage);
		primaryStage.setTitle(menuScene.getTitle());
		primaryStage.setScene(menuScene.getScene());

		primaryStage.show();
	}

}
