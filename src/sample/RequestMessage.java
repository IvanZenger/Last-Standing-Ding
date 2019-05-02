package sample;
/*
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Key;

public class RequestMessage implements Runnable {



	private Socket clientSocket;
	private ServerSocket serverSocket = Server.getsSocket();

	private MyCanvas canvas = GUI.getCanvas();
	/*
	public RequestMessage(ServerSocket serverSocket){
		this.serverSocket = serverSocket;
	}
	@Override
	public void run() {



			System.out.println("Server Listening Startet");
			try {
				clientSocket = serverSocket.accept();

				//ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
				//ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

				System.out.println(clientSocket);
				//oos.close();
				new Thread(new ClientHandler(clientSocket, canvas)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}



	}

}
*/