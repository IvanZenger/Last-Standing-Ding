package sample;


import javafx.scene.paint.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Hier werden die Anfragen, der Clients, welche während des Spieles kommen verarbeitet
 * Ist noch nicht Implementiert => Konnte nicht ganz fertig entwickelt werden => Zeitmangel
 */
public class ClientHandler implements Runnable {


	private Socket clientSocket;
	private MyCanvas canvas;
	private Player player = new Player();

	/**
	 * Konstruktor
	 *
	 * @param clientSocket
	 * @param canvas
	 */
	public ClientHandler(Socket clientSocket, MyCanvas canvas) {

		this.clientSocket = clientSocket;
		this.canvas = canvas;
	}

	// Server Listening //
	@Override
	/**
	 *  Hier wird das Objekt vom Client entgegen genommen und der Server schikt ein Objekt zum Client
	 *  Es wird eine dauernte verbindung gehalten
	 */
	public void run() {
		ObjectInputStream ois;
		ObjectOutputStream oos = null;

		System.out.println("Try read Client input");
		while (true) {
			try {

//				//canvas canvasSer = new canvas();
//				oos = new ObjectOutputStream(clientSocket.getOutputStream());
//				//System.out.println(canvasSer.getProperties());
//				oos.writeObject(this.canvas);
//				oos.flush();

				ois = new ObjectInputStream(clientSocket.getInputStream());
				//SerPlayer client = (SerPlayer) ois.readObject();
				String test = (String) ois.readObject();
				System.out.println(test);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}

				/* => Client Nachricht schiken
                oos.write((int) player.getToX());
                oos.write((int) player.getToY());
                System.out.println(player.getToX()+ " " +player.getToY());
                

				//oos.close();
				/*

			} catch (IOException e) {
				e.printStackTrace();
				return;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}


	}


}*/
