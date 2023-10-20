package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class ExportPostController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField postId;
	
	@FXML
	private Text postIdErrorMessage;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void backToMenuHandler(ActionEvent event) {

		MenuScene menuScene = new MenuScene(primaryStage);
		primaryStage.setTitle(menuScene.getTitle());
		primaryStage.setScene(menuScene.getScene());

		primaryStage.show();
	}
	
	// FUnction to export the post in the CSV fle
	public void exportPostHandler(ActionEvent event) {
		PostDaoImpl operations = PostDaoImpl.getInstance();
		User loggedInUser = LoggedInUser.getLoggedInUser();
		boolean postIdExists = operations.checkPostIdExists(postId.getText(), loggedInUser.getUserId());
		this.postIdErrorMessage.setText("");
		if(postIdExists) {
			SocialMediaPost socialMediaPost = operations.retrievePost(postId.getText(), loggedInUser.getUserId());
			
			// Adding the headers of the CSV
			List<String> header = new ArrayList<>();
			header.add("Post Id");
	        header.add("Content");
	        header.add("Author");
	        header.add("Likes");
	        header.add("Shares");
	        header.add("Date Time");
	        
	        // Adding the row data of the CSV
	        List<String> rowData = new ArrayList<>();
	        rowData.add(socialMediaPost.getPostId());
	        rowData.add(socialMediaPost.getContent());
	        rowData.add(socialMediaPost.getAuthor());
	        rowData.add(String.valueOf(socialMediaPost.getLikes()));
	        rowData.add(String.valueOf(socialMediaPost.getShares()));
	        rowData.add(socialMediaPost.getDateTime());
	        
	        List<List<String>> data = new ArrayList<>();
	        data.add(header);
	        data.add(rowData);
	        
	        String csvFilePath = "../Post Id - " + socialMediaPost.getPostId() + ".csv";
	        
	        try (FileWriter writer = new FileWriter(csvFilePath)) {
	            for (List<String> row : data) {
	                for (int i = 0; i < row.size(); i++) {
	                    writer.append(row.get(i));
	                    if (i < row.size() - 1) {
	                        writer.append(",");
	                    }
	                }
	                writer.append("\n");
	            }
	          this.postIdErrorMessage.setText("Post Id exported successfully");
	        } catch (IOException e) {
	            e.printStackTrace();
	            this.postIdErrorMessage.setText("Something wrong in exporting the post");
	        }
		}
		else {
			this.postIdErrorMessage.setText("Post Id doesn't exists");
		}
		
	}

}
