package sample;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args){
		try {
			String ip = "172.16.2.139";
			int port = 8000;
			Socket sock = new Socket(ip,port);

			String str = "Hallo";
			
			OutputStreamWriter osw = new OutputStreamWriter(sock.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);

			osw.write(str);
			osw.write(1234567);
			osw.flush();

			sock.close();


		}
		catch(Exception e){

		}
	}
}
