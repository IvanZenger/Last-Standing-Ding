package sample;


import com.sun.javafx.image.IntPixelGetter;
import javafx.scene.canvas.Canvas;


import java.io.*;
import java.net.Socket;


public class ServerData implements Runnable{


	//private static final long serialVersionUID = 1L;


	@Override
	public void run() {
		int port = 8000;
		//Canvas canvas;

		System.out.println("run sd");

		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		OutputStream outputStream = null;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				//System.out.println("try Client");

				//System.out.println("1");
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
				Integer objServer = 333;
				objectOutputStream.writeObject(objServer);
				objectOutputStream.flush();

				//System.out.println("1.1");

				//System.out.println("2");
				objectInputStream = new ObjectInputStream(socket.getInputStream());
				MyCanvas canvas = (MyCanvas) objectInputStream.readObject();
				System.out.println(canvas.getProperties());
				//System.out.println("3");
				//objectInputStream = new ObjectInputStream(socket.getInputStream());
				//objectOutputStream = socket.getOutputStream();

				//System.out.println("4");
				

			} catch (IOException e) {
				//e.printStackTrace();       => Throws Errors
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				
			}
		}

	}

}
