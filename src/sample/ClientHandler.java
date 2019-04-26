package sample;


import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.Serializable;
public class ClientHandler implements Runnable, Serializable{

	private static final long serialVersionUID = 1L;

	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public ClientHandler(Socket clientSocket, ObjectInputStream ois, ObjectOutputStream oos){

		this.clientSocket = clientSocket;
		this.ois = ois;
		this.oos = oos;
	}

	private InputStream inputStream;
	
	  // Server liest //
	@Override
	public void run() {
		while (true) {

			try {

				inputStream = clientSocket.getInputStream();
				ois = new ObjectInputStream(inputStream);
				int x = (int) ois.readObject();
				System.out.println(x);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}


	}

}
