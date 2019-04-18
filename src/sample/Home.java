package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Home extends Application {

	Button host,player;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();

		host = new Button("Hoster");
		player = new Button("Player");
		primaryStage.setTitle("Last Standing Ding");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}
}
