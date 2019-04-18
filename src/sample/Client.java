package sample;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public void connect(String ip){
		try {
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
