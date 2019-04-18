package sample;

import javafx.scene.control.TextArea;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server implements Runnable{

	private boolean isStopped = false;
	private Thread startedThread = null;
	private ServerSocket sSocket = null;
	private TextArea ta;

	public Server(TextArea ta){
		this.ta = ta;
	}
	
	public void run(){

		synchronized (this){
			this.startedThread = Thread.currentThread();
		}
		openSocket();
		while(!isStopped()){
			Socket cSocket = null;
			try{
				cSocket = this.sSocket.accept();
				
			} catch(IOException e) {
				if (isStopped()) {
					System.out.println("IOException => Server wurde beendet");
					return;
				}
				throw new RuntimeException(
						"Error accepting client connection", e);
			}

				new Thread(new WorkerRunnable(cSocket,"Server",ta)).start();


		}
		System.out.println("Server wurde beendet");
	
	}
	

	public void openSocket(){

		try {
			sSocket = new ServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private synchronized boolean isStopped() {
		return this.isStopped;
	}
	class ClientHandler extends Thread{
		
			final DataInputStream dis;
			final DataOutputStream dos;
			final Socket s;
			
			public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
			{
				this.s = s;
				this.dis = dis;
				this.dos = dos;
				try {
					String str = dis.readUTF();
					System.out.println(str);
				}catch(Exception e){
					e.getStackTrace();
				}
			}




	}
	
}
