package sample;

import javafx.scene.control.TextArea;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Hier wird der Server für das Hosten des spieles gestartet
 */
public class Server implements Runnable{

	private boolean isStopped; //für dir überprüfung, ob der Server gestoppt wurde
	private Thread startedThread = null;
	private static ServerSocket sSocket;//Server Socket
	private TextArea taHost;
	private Socket cSocket = null; //Client Socket

	/**
	 * Konstruktor
	 * @param taHost
	 * 
	 */
	public Server(TextArea taHost, boolean isStopped){
		this.taHost = taHost;
		this.isStopped = isStopped;
	}

	public Server(){
		
	}

	
	/**
	 * Hier wird der Server gestartet
	 */
	public void run(){

		synchronized (this){
			this.startedThread = Thread.currentThread();
		}
		openSocket(); //Socket wird geöffnet

		while(!isStopped()){ //Wen der Server nicht gestoppt wurde
			System.out.println(isStopped);
			try{
				cSocket = this.sSocket.accept(); //Wartet auf Client
				OutputStream outputStream = cSocket.getOutputStream();
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
				//objectOutputStream.writeObject(GUI.getCanvas());
				//System.out.println("Gesendet");


			} catch(IOException e) {
				if (isStopped()) {
					System.out.println("IOException => Server wurde beendet");
					return;
				}

			}

				new Thread(new WorkerRunnable(cSocket,taHost)).start();//Neuer Thread => Client verarbeitung


			this.isStopped = true;

		}
	
		System.out.println("Server wurde beendet");
	
	}



	/**
	 * Hier wird der Socket für den Server geöffnet
	 */
	public void openSocket(){

		try {
			sSocket = new ServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * schaut ob der Server gestoppt wurde
	 * @return
	 */
	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public static ServerSocket getsSocket() {
		return sSocket;
	}
	
}
