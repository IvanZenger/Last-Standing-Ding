package sample;

import javafx.scene.input.KeyCode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;

public class RequestMessage implements Runnable {



	private Socket clientSocket;
	private ServerSocket serverSocket;


	public RequestMessage(Socket clientSocket, ServerSocket serverSocket){

		this.serverSocket = serverSocket;
		this.clientSocket = clientSocket;

	}

	@Override
	public void run() {
		while(true){
			System.out.println("Server Listening Startet");
			try {
				clientSocket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

				new Thread(new ClientHandler(clientSocket,ois,oos)).run();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
