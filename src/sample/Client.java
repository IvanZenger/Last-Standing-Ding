package sample;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Hier wird der Client verwaltet
 */
public class Client implements Runnable{
	private String ip;
	private String name;


	/**
	 * Hier wird eine verbindung zum Server hergestellt
	 * @param ip
	 * @param name
	 * @return
	 */
	public boolean connect(String ip, String name){
		try {
			int port = 8000;
			Socket socket = new Socket(ip,port); //neuer Socket

			OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());//Ausgabe
			PrintWriter pw = new PrintWriter(osw);//Verarebeitung der Ausgabe

			osw.write(name);//Ausgabe zum Server
		//	osw.write();
			osw.flush();

			socket.close();
			//new Thread(this).start();
			return true;

		} catch (UnknownHostException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Zum Testen des Clients
	 * @param args
	 */
	public static void main(String[] args){
		Client hallo = new Client();
		hallo.connect("localhost","Ivan");
	}

	@Override
	public void run() {
		
	}
}
