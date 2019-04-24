package sample;

import javafx.scene.control.TextArea;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

/**
 * Hier wird der Server für das Hosten des spieles gestartet
 */
public class Server implements Runnable{

	private boolean isStopped = false; //für dir überprüfung, ob der Server gestoppt wurde
	private Thread startedThread = null;
	private ServerSocket sSocket = null;//Server Socket
	private TextArea taHost;
	private TextArea taPlayer;

	/**
	 * Konstruktor
	 * @param taHost
	 * @param taPlayer
	 */
	public Server(TextArea taHost, TextArea taPlayer){

		this.taHost = taHost;
		this.taPlayer = taPlayer;

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
			Socket cSocket = null; //Client Socket
			try{
				cSocket = this.sSocket.accept(); //Wartet auf Client
				
			} catch(IOException e) {
				if (isStopped()) {
					System.out.println("IOException => Server wurde beendet");
					return;
				}

			}

				new Thread(new WorkerRunnable(cSocket,taHost,taPlayer)).start();//Neuer Thread => Client verarbeitung


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
	
}
