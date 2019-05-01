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

		System.out.println("run sd");

		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		OutputStream outputStream = null;
		Socket socket = null;
		try {
			socket = new Socket("localhost", port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				System.out.println("try Client");

				inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();

				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				objectInputStream = new ObjectInputStream(inputStream);

				int x = (int) objectInputStream.readObject();
				System.out.println(x);

				outputStream = socket.getOutputStream();
				outputStream.write(3);
				outputStream.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}

}
