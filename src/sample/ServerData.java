package sample;

import javafx.scene.canvas.Canvas;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static com.sun.org.apache.xml.internal.serializer.utils.Utils.messages;

public class ServerData implements Runnable, Serializable{

	private static final long serialVersionUID = 1L;


	@Override
	public void run() {
		int port = 8000;
		Canvas canvas;
		
		while (true) {
			Socket socket = null;


			try {
				socket = new Socket("172.16.1.139", port);
			} catch (IOException e) {
				e.printStackTrace();
			}
			OutputStream outputStream = null;
			try {
				outputStream = socket.getOutputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectOutputStream.writeObject(2);
			} catch (IOException e) {
				e.printStackTrace();
			}


			InputStream inputStream = null;
			try {
				inputStream = socket.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
			ObjectInputStream objectInputStream = null;
			try {
				objectInputStream = new ObjectInputStream(inputStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				canvas = (Canvas) objectInputStream.readObject();


			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
}
