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

	private InputStream inputStream;
	private ObjectInputStream objectInputStream;
	  // Server liest //
	@Override
	public void run() {
		while (true) {

			try {

				inputStream = clientSocket.getInputStream();
				objectInputStream = new ObjectInputStream(inputStream);
				int x = (int) objectInputStream.readObject();
				System.out.println(x);

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}


	}

}
