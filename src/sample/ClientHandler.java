package sample;


import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {


	private Socket clientSocket;
	private MyCanvas canvas;

	public ClientHandler(Socket clientSocket, MyCanvas canvas) {

		this.clientSocket = clientSocket;
		this.canvas = canvas;
	}

	// Server Listening //
	@Override
	public void run() {
		ObjectInputStream ois;
		ObjectOutputStream oos;



		System.out.println("Try read Client input");
		while (true) {
			try {

				//canvas canvasSer = new canvas();
				oos = new ObjectOutputStream(clientSocket.getOutputStream());
				//System.out.println(canvasSer.getProperties());
				oos.writeObject(this.canvas);
				oos.flush();

				ois = new ObjectInputStream(clientSocket.getInputStream());
				SerPlayer client = (SerPlayer) ois.readObject();
				doSomething(client);
				System.out.println(client.getName());

				/* => Client Nachricht schiken  */


				//oos.close();
				/* */

			} catch (IOException e) {
				e.printStackTrace();
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}


	}

	private void doSomething(SerPlayer p){
		System.out.println(p.getName());
	}

}
