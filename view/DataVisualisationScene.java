package view;

import controller.DatabaseOperations;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;
import model.LoggedInUser;
import model.User;

public class DataVisualisationScene extends Application {
	
	@Override
    public void start(Stage stage) {
		PieChart pieChart = new PieChart();
        pieChart.setTitle("Distribution of Shares");
        
        User loggedInUser = LoggedInUser.getLoggedInUser();
        DatabaseOperations operations = DatabaseOperations.getInstance();
        
        pieChart.getData().add(new PieChart.Data("0-99 Shares", operations.getCountPostsInRange(0, 99, loggedInUser.getUserId())));
        pieChart.getData().add(new PieChart.Data("100-999 Shares", operations.getCountPostsInRange(100, 999, loggedInUser.getUserId())));
        pieChart.getData().add(new PieChart.Data("1000 or more Shares", operations.getCountPostsInRange(1000, -1, loggedInUser.getUserId())));
        
        Scene scene = new Scene(pieChart, 800, 600);
        stage.setScene(scene);
        stage.setTitle("Shares Distribution Pie Chart");
        stage.show();
	}

}
