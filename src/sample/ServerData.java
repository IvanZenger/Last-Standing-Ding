package sample;


import com.sun.javafx.image.IntPixelGetter;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
public class ServerData implements Runnable{

	private SerPlayer player;
	
	public ServerData(SerPlayer player){

		this.player = player;
	}

	@Override
	public void run(){
		int port = 8000;

		System.out.println("run sd");
		ObjectInputStream ois;
		ObjectOutputStream oos;
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1", port);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		while (true) {
			try {
//				ois = new ObjectInputStream(socket.getInputStream());
//				MyCanvas canvas = (MyCanvas) ois.readObject();
//				System.out.println(canvas.getWidth());

				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject("Test");
				oos.flush();
		
			} catch (IOException e) {
				e.printStackTrace();       
			} /*catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e){
				
			}
		}

	}

}*/
