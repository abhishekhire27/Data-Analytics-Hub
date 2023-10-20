package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dao.UserDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.SocialMediaPost;
import model.User;
import view.AddPostScene;
import view.DataVisualisationScene;
import view.EditProfileScene;
import view.ExportPostScene;
import view.LoginScene;
import view.MostLikesScene;
import view.RemovePostScene;
import view.RetrievePostScene;

// The class of the main menu of the user
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
	@FXML
	private Button makeVipMemberButton;
	
	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
		User loggedInUser = LoggedInUser.getLoggedInUser();
		
		// If the user is VIP user, then make the VIP functionality visible
		if(loggedInUser.isVipMember()) {
			this.subscriptionText.setText("");
			this.makeVipMemberButton.setVisible(false);
		}
		else {
			this.importCsvText.setText("");
			this.dataVisualisationText.setText("");
			this.importButtonId.setVisible(false);
			this.dataVisualisationButtonId.setVisible(false);
			
		}
		
		this.welcomeMessage.setText("Welcome " + loggedInUser.getFirstName() + ",");
	}
	
	// Function handler to go to update profile page
	@FXML
	public void updateProfileButtonHandler(ActionEvent event) {
		EditProfileScene editProfileScene = new EditProfileScene(primaryStage);
		primaryStage.setTitle(editProfileScene.getTitle());
		primaryStage.setScene(editProfileScene.getScene());

		primaryStage.show();
	}

	// Function handler to go to add post page
	@FXML
	public void addPostButtonHandler(ActionEvent event) {
		AddPostScene addPostScene = new AddPostScene(primaryStage);
		primaryStage.setTitle(addPostScene.getTitle());
		primaryStage.setScene(addPostScene.getScene());

		primaryStage.show();
	}

	// Function handler to go to retrieve post page
	@FXML
	public void retrievePostButtonHandler(ActionEvent event) {
		RetrievePostScene retrievePostScene = new RetrievePostScene(primaryStage);
		
		primaryStage.setTitle(retrievePostScene.getTitle());
		primaryStage.setScene(retrievePostScene.getScene());
		
		primaryStage.show();
	}

	// Function handler to go to remove post page
	@FXML
	public void removePostButtonHandler(ActionEvent event) {
		RemovePostScene removePostScene = new RemovePostScene(primaryStage);
		
		primaryStage.setTitle(removePostScene.getTitle());
		primaryStage.setScene(removePostScene.getScene());
		
		primaryStage.show();
	}

	// Function handler to go to n most liked post page
	@FXML
	public void nLikesButtonHandler(ActionEvent event) {
		MostLikesScene mostLikesScene = new MostLikesScene(primaryStage);
		
		primaryStage.setTitle(mostLikesScene.getTitle());
		primaryStage.setScene(mostLikesScene.getScene());
		
		primaryStage.show();
	}
	
	// Function handler to go to export post page
	@FXML
	public void exportButtonHandler(ActionEvent event) {
		ExportPostScene exportPostScene = new ExportPostScene(primaryStage);
		
		primaryStage.setTitle(exportPostScene.getTitle());
		primaryStage.setScene(exportPostScene.getScene());
		
		primaryStage.show();
	}
	
	// Function handler to go to logout post page
	@FXML
	public void logoutButtonHandler(ActionEvent event) {
		LoggedInUser.logout();
		LoginScene loginScene = new LoginScene(primaryStage);
		
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());
		
		primaryStage.show();
	}
	
	// Function handler to make the user VIP member
	@FXML
	public void makeVipMemberHandler(ActionEvent event) {
		UserDaoImpl operations = UserDaoImpl.getInstance();
		boolean isSuccess = operations.addVipMember(LoggedInUser.getLoggedInUser().getUserId());
		
		if(isSuccess) {
			Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle("VIP Member");
	        alert.setHeaderText("Success");
	        alert.setContentText("You can now enjoy VIP member benefits. Please Login again");
	        
	        alert.showAndWait();
	        
			LoginScene loginScene = new LoginScene(primaryStage);
			
			primaryStage.setTitle(loginScene.getTitle());
			primaryStage.setScene(loginScene.getScene());
			
			primaryStage.show();
		}
	}
	
	// Function handler to import the post from the CSV
	@FXML
	public void importCsvHandler(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File file = fileChooser.showOpenDialog(null);
        List<SocialMediaPost> data = new ArrayList<>();
        if (file != null) {
            try {
                List<SocialMediaPost> posts = readCSV(file);
                data.addAll(posts);
            } catch (IOException e) {
//                displayError("Error Reading CSV", "An error occurred while reading the CSV file.");
            } catch (Exception e) {
//                displayError("CSV Format Error", "The CSV file does not follow the correct format.");
            }
        }
	}
	
	// Function to read the CSV file selected by the user.
	private List<SocialMediaPost> readCSV(File file) throws IOException {
        List<SocialMediaPost> posts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    // Assuming your CSV format has 6 columns
                    SocialMediaPost post = new SocialMediaPost();
                    post.setPostId(parts[0]);
                    post.setContent(parts[1]);
                    post.setAuthor(parts[2]);
                    post.setLikes(Long.parseLong(parts[3]));
                    post.setShares(Long.parseLong(parts[4]));
                    post.setDateTime(parts[5]);
                    posts.add(post);
                } else {
//                    throw new CSVFormatException("Invalid CSV format");
                }
            }
        }
        return posts;
    }
	
	// Function to open the visualization page
	@FXML
	public void postDataVisualisationHandler(ActionEvent event) {
		Stage dataVisualizationStage = new Stage();
        DataVisualisationScene dataVisualisationScene = new DataVisualisationScene();
        dataVisualisationScene.start(dataVisualizationStage);
        dataVisualizationStage.setTitle("Data Visualization");
        dataVisualizationStage.show();
		
	}

}
