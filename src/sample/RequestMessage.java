package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;

/**
 * Hier werden die Anfragen der Clients verarbeitet
 * => Wird nicht verwendet 
 */
public class RequestMessage implements Runnable {



	private Socket clientSocket;
	private ServerSocket serverSocket = Server.getsSocket();

	private MyCanvas canvas = GUI.getCanvas();




	@Override
	/**
	 * Hier wird der Client weiter in einen neuen Thread geworfen
	 */
	public void run() {



			System.out.println("Server Listening Startet");
			try {
				clientSocket = serverSocket.accept(); //Wartet auf Client

				//Neuer Thread wo der Client behandelt wird
				new Thread(new ClientHandler(clientSocket)).start();
				System.out.println(clientSocket);;

			} catch (IOException e) {
				e.printStackTrace();
			}



	}

}
