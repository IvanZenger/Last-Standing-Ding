package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;

public class RequestMessage implements Runnable {



	private Socket clientSocket = null;
	private ServerSocket serverSocket = Server.getsSocket();

	private Canvas canvas = GUI.getCanvas();
	/*
	public RequestMessage(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	} */
	@Override
	public void run() {

		
		while(true){
			System.out.println("Server Listening Startet");
			try {
				clientSocket = serverSocket.accept();

				ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

				System.out.println(clientSocket);
				//oos.close();
				new Thread(new ClientHandler(clientSocket,ois,oos,canvas)).run();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
	
}
