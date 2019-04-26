package sample;



import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.io.Serializable;
public class ClientHandler extends Thread implements Serializable{

	private static final long serialVersionUID = 1L;
	private Socket clientSocket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public ClientHandler(Socket clientSocket, ObjectInputStream ois, ObjectOutputStream oos){
		this.clientSocket = clientSocket;
		this.ois = ois;
		this.oos = oos;
	}

	@Override
	public void run() {
		Socket socket = null;
		try {
			//sSocket = new Socket("localhost", 8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true) {


		}
	}
	
}
