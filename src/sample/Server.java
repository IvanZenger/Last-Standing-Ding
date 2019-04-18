package sample;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args){
		try {
			System.out.println("Server ist gestartet");
			ServerSocket ssock = new ServerSocket(8000);
			System.out.println("Socket erstellt");

			Socket sock = ssock.accept();
			
			System.out.println("Client ist beigetreten");

			BufferedInputStream br = new BufferedInputStream(sock.getInputStream());


			for(int i=0; (i = br.read()) != -1; ) //Schleife läuft so lange bis die Verbindung geschlossen wird
			{
				System.out.print((char)i); //Ausgeben
			}

			

		}
		catch(Exception e){
			      System.out.println(e.getMessage());
		}
	}
}
