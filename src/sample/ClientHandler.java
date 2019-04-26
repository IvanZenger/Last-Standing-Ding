package sample;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.Serializable;
public class ClientHandler implements Runnable, Serializable{

	private static final long serialVersionUID = 1L;
	
	
	@Override
	public void run() {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {

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
				String listOfMessages = (String) objectInputStream.readObject();
				if (listOfMessages.equals("Hallo")) {
					Socket backSocket = null;
					try {
						socket = new Socket("172.16.1.139", 8000);
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
						objectOutputStream.writeObject(GUI.getCanvas());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
