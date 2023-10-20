import javafx.application.Application;
import javafx.stage.Stage;
import view.LoginScene;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginScene loginScene = new LoginScene(primaryStage);
		
		primaryStage.setTitle(loginScene.getTitle());
		primaryStage.setScene(loginScene.getScene());
		
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
}