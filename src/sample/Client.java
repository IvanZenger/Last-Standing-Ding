package sample;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {


	public static void main(String[] args){
		try {
			Scanner scan = new Scanner(System.in);
			String ip = "localhost";
			int port = 8000;
			Socket sock = new Socket(ip,port);


			String str = "Hallo";
			
			OutputStreamWriter osw = new OutputStreamWriter(sock.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);

			osw.write(str);
			osw.flush();

			sock.close();


		}
		catch(Exception e){

		}
	}
}
