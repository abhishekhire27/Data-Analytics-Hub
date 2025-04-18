package controller;

import java.util.ArrayList;

import dao.PostDaoImpl;
import exceptions.CustomNumberFormatException;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.SocialMediaPost;
import model.User;
import view.MenuScene;

public class MostLikesController {
	
	private Stage primaryStage;
	
	@FXML
	private TextField nLikes;
	
	@FXML
	private Text nLikesErrorMessage;
	
	@FXML
    private AnchorPane mainPane;
	
	private TableView<SocialMediaPost> tableView;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	
	@FXML
	public void backToMenuHandler(ActionEvent event) {
		this.removeTableView();
		MenuScene menuScene = new MenuScene(primaryStage);
		primaryStage.setTitle(menuScene.getTitle());
		primaryStage.setScene(menuScene.getScene());

		primaryStage.show();
	}
	
	public void getNLikesPostHandler(ActionEvent event) {
		// Reseting the error message
		nLikesErrorMessage.setText("");
		removeTableView();
		
		boolean wrongNumberEntered = false;
		if(nLikes.getText() == null || nLikes.getText() == "") {
			nLikesErrorMessage.setText("Please enter a valid positive number");
			wrongNumberEntered = true;
		}
		
		if(!wrongNumberEntered) {
			try {
				//If the parsing fails, means user has but something else instead of number
				Integer.parseInt(nLikes.getText());
				
				if(Integer.parseInt(nLikes.getText()) <= 0) {
					// Throwing a manual custom exception if the user input a negative number or zerio.
					throw new CustomNumberFormatException();
				}
			}
			catch(Exception e) {
				nLikesErrorMessage.setText("Please enter a valid positive number");
				return;
			}
			PostDaoImpl operations = PostDaoImpl.getInstance();
			User loggedInUser = LoggedInUser.getLoggedInUser();
			ArrayList<SocialMediaPost> mostLikedPosts = operations.getNLikesPost(Integer.parseInt(nLikes.getText()), loggedInUser.getUserId());
			this.showInTableFormat(mostLikedPosts, Integer.parseInt(nLikes.getText()));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void showInTableFormat(ArrayList<SocialMediaPost> mostLikedPosts, int numberOfRows) {
		
		// Create the table according to the data returned from the Database on the same page.
		this.tableView = new TableView<>();
        ObservableList<SocialMediaPost> data = FXCollections.observableArrayList();

        // Define the columns
        TableColumn<SocialMediaPost, String> postIdCol = new TableColumn<>("Post ID");
        postIdCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPostId()));

        TableColumn<SocialMediaPost, String> contentCol = new TableColumn<>("Content");
        contentCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getContent()));

        TableColumn<SocialMediaPost, String> authorCol = new TableColumn<>("Author");
        authorCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));

        TableColumn<SocialMediaPost, String> likesCol = new TableColumn<>("Likes");
        likesCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getLikes())));

        TableColumn<SocialMediaPost, String> sharesCol = new TableColumn<>("Shares");
        sharesCol.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getShares())));

        TableColumn<SocialMediaPost, String> dateTimeCol = new TableColumn<>("Date & Time");
        dateTimeCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDateTime()));

        // Add the columns to the table
        tableView.getColumns().addAll(postIdCol, contentCol, authorCol, likesCol, sharesCol, dateTimeCol);

        // Add data to the table
        data.addAll(mostLikedPosts);

        tableView.setItems(data);
        tableView.setLayoutY(160.0);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPrefWidth(computeTableViewWidth(postIdCol, contentCol, authorCol, likesCol, sharesCol, dateTimeCol));
//        tableView.setPrefHeight(computeTableViewHeight(data));
        tableView.setMinWidth(Region.USE_COMPUTED_SIZE);
        
        mainPane.getChildren().add(tableView);
	}
	
	private double computeTableViewWidth(TableColumn<SocialMediaPost, ?>... columns) {
	    double totalWidth = 0;
	    
	    for (TableColumn<SocialMediaPost, ?> column : columns) {
	        totalWidth += column.getWidth();
	    }
	    
	    return Math.max(totalWidth, TableView.USE_COMPUTED_SIZE);
	}
	
	private double computeTableViewHeight(ObservableList<SocialMediaPost> data) {
	    int numRows = data.size();
	    return Math.max(numRows * 24.0, TableView.USE_COMPUTED_SIZE);
	}
	
	public void removeTableView() {
        // Remove the TableView from the AnchorPane
        if (tableView != null) {
            mainPane.getChildren().remove(tableView);
            tableView = null; // Set it to null
        }
    }
	
}
