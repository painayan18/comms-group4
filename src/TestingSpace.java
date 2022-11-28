import clientGUI.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import requestResponse.*;
import server.*;
import sharedData.*;

public class TestingSpace extends Application {
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/Login.fxml"))));
		primaryStage.setTitle("Play Tech Live Chat");
		primaryStage.show();
	}
}
