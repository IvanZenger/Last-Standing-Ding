package sample;




import javafx.scene.canvas.Canvas;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.Serializable;
public class ClientHandler implements Runnable{
	

	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private MyCanvas canvas;

	public ClientHandler(Socket clientSocket, ObjectInputStream ois, ObjectOutputStream oos, MyCanvas canvas){

		this.clientSocket = clientSocket;                                                            
		this.ois = ois;
		this.oos = oos;
		this.canvas = canvas;
	}
	
	  // Server Listening //
	@Override
	public void run() {
		System.out.println("Try read Client input");
		while (true) {
			try {

				//canvas canvasSer = new canvas();
				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				//System.out.println(canvasSer.getProperties());
				MyCanvas hallo = new MyCanvas(600,900);
				oos.writeObject(hallo);
				oos.flush();
				
				ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
				Integer xclient = (Integer) in.readObject();
				System.out.println(xclient.toString());

			   /* => Client Nachricht schiken  */
				


				//oos.close();
                   /* */

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}


	}

	public class canvas extends Canvas implements Serializable{
		
	}
}
