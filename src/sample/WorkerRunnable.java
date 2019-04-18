package sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WorkerRunnable implements Runnable{

	private Socket cSocket = null;
	private String server = null;

	public WorkerRunnable(Socket cSocket, String serverText){
		this.cSocket = cSocket;
		this.server = server;
	}

	public void run(){
		try{
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();
			long time = System.currentTimeMillis();

			os.close();
			is.close();
			System.out.println("Spieler tritt bei => [" + time + "]");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
