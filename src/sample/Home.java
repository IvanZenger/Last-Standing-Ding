package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Home extends Application implements EventHandler<ActionEvent> {

	Stage window;
	Scene hostScene,playerScene;
	Button btnHost,btnPlayer, btnPlayerJoin, btnPlayerJoinTest;
	Label lblHost;
	TextField txtPlayer,txtPlayerName;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		GridPane root = new GridPane();
		GridPane hostLayout = new GridPane();
		GridPane playerLayout = new GridPane();

		btnHost = new Button("Hoster");
		btnPlayer = new Button("Player");
		btnPlayerJoin = new Button("Beitreten");
		lblHost = new Label("Du Hostest! yey");
		txtPlayer = new TextField();
		txtPlayer.setPromptText("Host-IP");
		txtPlayerName = new TextField();
		txtPlayerName.setPromptText("Name");
		btnPlayerJoinTest = new Button("Beitreten");
		
		// Actions //
		btnHost.setOnAction(this);
		btnPlayer.setOnAction(this);
		btnPlayerJoin.setOnAction(this);
		btnPlayerJoinTest.setOnAction(this);

		// GridPane => Home //
		GridPane.setConstraints(btnHost,1,1);
		GridPane.setConstraints(btnPlayer,2,1);

		//  GridPane => Host //
		GridPane.setConstraints(lblHost,0,1);
		GridPane.setConstraints(btnPlayerJoinTest,1,1);

		//GridPane => Player //
		GridPane.setConstraints(txtPlayer,0,1);
		GridPane.setConstraints(txtPlayerName, 0, 2);
		GridPane.setConstraints(btnPlayerJoin,0,3);


		// Add => root //
		root.getChildren().addAll(btnHost,btnPlayer);

		// Add => Host //
		hostLayout.getChildren().addAll(lblHost,btnPlayerJoinTest);
		hostScene = new Scene(hostLayout,300,275);

		// Add => Player //
		playerLayout.getChildren().addAll(txtPlayer,btnPlayerJoin,txtPlayerName);
		playerScene = new Scene(playerLayout, 300,275);

		primaryStage.setTitle("Last Standing Ding");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}


	@Override
	public void handle(ActionEvent event) {

		if(event.getSource() == btnHost){
			window.setScene(hostScene);
			window.show();
			
			Server server = new Server();
			new Thread(server).start();


		}
		else if(event.getSource() == btnPlayer){
			System.out.println("Player");
			window.setScene(playerScene);

		}
		else if(event.getSource() == btnPlayerJoin){
			Client player = new Client();
			player.connect(txtPlayer.getText());
		}
		else if(event.getSource() == btnPlayerJoinTest){
			Client player = new Client();
			player.connect("localhost");
			System.out.println("Host tritt bei");
		}
	}
}
