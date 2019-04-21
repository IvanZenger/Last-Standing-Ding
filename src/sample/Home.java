package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.exceptions.emptyException;

import java.net.*;
import java.util.Enumeration;


/**
 * Hier wird das Spiel gestartet und die verschiedenen Scenes verwaltet
 * @author zengeri
 * @version 1.0
 */
public class Home extends Application implements EventHandler<ActionEvent> {

	Stage window;
	Scene hostStart,playerScene,playerJoin,hostScene;
	Button btnHost,btnPlayer, btnPlayerJoin, btnHostStart,btnGameStart;
	Label lblHost,lblplayer,lblFailureHost,lblFailurePlayer,lblHostIP;
	TextField txtPlayer,txtPlayerName,txtHostName;
	TextArea taPlayersHost,taPlayersPlayer;

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Hier werden die verschieden Objekte erstellt und den Scenes zugewiesen
	 * @param primaryStage
	 */
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		GridPane root = new GridPane(); //start menu
		GridPane hosterStart = new GridPane(); //Ab hier kan der Hoster das Spiel starten
		GridPane playerLayout = new GridPane(); //Name un Host-IP eingabe des Players
		GridPane playerJoinLayout = new GridPane(); //Wen der Player dem Spiel beigetreten ist
		GridPane hostLayout = new GridPane(); //Name eingabe des Hosters

		btnHost = new Button("Hoster"); //wahl des Types
		btnPlayer = new Button("Player"); //wahl des Types
		btnPlayerJoin = new Button("Beitreten"); //beitreten des Spieles
		btnHostStart = new Button("Host Starten"); //nach eingabe des Host-Name
		btnGameStart = new Button("Spiel starten"); //Start des Spieles

		lblHost = new Label("Du Hostest! yey");
		lblplayer = new Label("Du bist dem Spiel beigetreten! \n Warte bis der Host das Spiel gestartet hat");
		lblFailureHost = new Label();
		lblFailureHost.setTextFill(Color.RED);
		lblFailurePlayer = new Label();
		lblFailurePlayer.setTextFill(Color.RED);
		lblHostIP = new Label();

		txtPlayer = new TextField();
		txtPlayer.setPromptText("Host-IP");
		txtPlayerName = new TextField();
		txtPlayerName.setPromptText("Name");
		txtHostName = new TextField();
		txtHostName.setPromptText("Name: ");

		taPlayersHost = new TextArea();
		taPlayersPlayer = new TextArea();

		taPlayersHost.setEditable(false);
		taPlayersPlayer.setEditable(false);

		// Actions //
		btnHost.setOnAction(this);
		btnPlayer.setOnAction(this);
		btnPlayerJoin.setOnAction(this);
		btnHostStart.setOnAction(this);
		btnGameStart.setOnAction(this);

		// GridPane => Home //
		GridPane.setConstraints(btnHost,1,1);
		GridPane.setConstraints(btnPlayer,2,1);

		//  GridPane => HostStart //
		GridPane.setConstraints(lblHost,0,1);
		GridPane.setConstraints(lblHostIP,0,2);
		GridPane.setConstraints(taPlayersHost,0,3);
		GridPane.setConstraints(btnGameStart,0,4);


		//GridPane => HostScene //
		GridPane.setConstraints(txtHostName,0,1);
		GridPane.setConstraints(btnHostStart,0,2);
		GridPane.setConstraints(lblFailureHost,0,3);

		//GridPane => PlayerScene //
		GridPane.setConstraints(txtPlayer,0,1);
		GridPane.setConstraints(txtPlayerName, 0, 2);
		GridPane.setConstraints(btnPlayerJoin,0,3);
		GridPane.setConstraints(lblFailurePlayer,0,4);


		//GridPane => PlayerJoin //
		GridPane.setConstraints(lblplayer,0,1);
		GridPane.setConstraints(taPlayersPlayer,0,3);

		// Add => root //
		root.getChildren().addAll(btnHost,btnPlayer);

		// Add => HostStart //
		hosterStart.getChildren().addAll(lblHost,taPlayersHost,btnGameStart,lblHostIP);
		hostStart = new Scene(hosterStart,300,275);

		// Add => HostScene //
		hostLayout.getChildren().addAll(txtHostName,btnHostStart,lblFailureHost);
		hostScene = new Scene(hostLayout, 300,275);

		// Add => PlayerScene //
		playerLayout.getChildren().addAll(txtPlayer,btnPlayerJoin,txtPlayerName,lblFailurePlayer);
		playerScene = new Scene(playerLayout, 300,275);

		// Add => playerJoin //
		playerJoinLayout.getChildren().addAll(taPlayersPlayer,lblplayer);
		playerJoin = new Scene(playerJoinLayout,300,275);

		primaryStage.setTitle("Last Standing Ding");
		primaryStage.setScene(new Scene(root, 300, 275));
		primaryStage.show();
	}


	/**
	 * Hier werden die Aktionen bearbeitet
	 * @param event
	 */
	@Override
	public void handle(ActionEvent event) {


		if(event.getSource() == btnHost){ // Wen der Type, Host gewählt wurde
			window.setScene(hostScene);
		}
		else if(event.getSource() == btnHostStart){ //Wen der Host sein Nam eingegeben hat => server wird gestartet
			if(!txtHostName.getText().equals("")) { //überprüfen auf leere Felder

				/*
				Hier wird die IP-Adresse des Hosters ausgelesen
				 */
				String hostIP = null;
				try {
					hostIP = InetAddress.getLocalHost().getHostAddress();

				} catch (UnknownHostException e) {
					e.printStackTrace();
				}

				lblHostIP.setText("Deine IP-Adresse ist: " + hostIP);
				window.setScene(hostStart);
				taPlayersHost.appendText(txtHostName.getText()); //Hoster zu der Spileliste zuweisen

				Server server = new Server(taPlayersHost, taPlayersPlayer); //Server starten
				new Thread(server).start();
			}else{
				lblFailureHost.setText("Geben sie einen Namen ein!"); //Fehlermeldung, bei leeren Feldern
			}
		}
		else if(event.getSource() == btnPlayer){//Wen der Typ, Player gewählt wurde
			//Server server = new Server(taPlayersHost,taPlayersPlayer);
			//new Thread(server).start();


			System.out.println("Player");
			window.setScene(playerScene);


		}
		else if(event.getSource() == btnPlayerJoin){//Player möchte dem Spiel beitreten
			Client player = new Client();


				try {
					if(txtPlayerName.getText().equals("")){ //überprüfen ob das Feld für die Namens-eingabe leer ist
						throw new emptyException();
					}else if(txtPlayer.getText().equals("")){// überprüfen ob das Feld für die IP-eingabe leer ist
						throw new emptyException();
					}
					else{

						if(player.connect(txtPlayer.getText(), txtPlayerName.getText())) { //verbindung wird hergestellt und überprüft

							window.setScene(playerJoin);
						}else{
							lblFailurePlayer.setText("Verbindung ist Fehlgeschlagen! \nüberprüfe deine Angaben"); //Fehlermeldung, wen die Verbindung fehlgeschlagen ist
						}
					}

				}catch(emptyException ee){
					lblFailurePlayer.setText("Eines der beiden Felder ist leer!"); //Fehlermeldung, wen ein Feld leer gelassen wurde
				}


		}

	}
}
