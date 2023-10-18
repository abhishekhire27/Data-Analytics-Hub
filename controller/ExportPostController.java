package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SocialMediaPost;
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
	
	public void exportPostHandler(ActionEvent event) {
		DatabaseOperations operations = DatabaseOperations.getInstance();
		
		boolean postIdExists = operations.checkPostIdExists(postId.getText());
		if(postIdExists) {
			SocialMediaPost socialMediaPost = operations.retrievePost(postId.getText());
			
			List<String> header = new ArrayList<>();
			header.add("Post Id");
	        header.add("Content");
	        header.add("Author");
	        header.add("Likes");
	        header.add("Shares");
	        header.add("Date Time");
	        
	        List<String> rowData = new ArrayList<>();
	        rowData.add(socialMediaPost.getPostId());
	        rowData.add(socialMediaPost.getContent());
	        rowData.add(socialMediaPost.getAuthor());
	        rowData.add(socialMediaPost.getLikes());
	        rowData.add(socialMediaPost.getShares());
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
