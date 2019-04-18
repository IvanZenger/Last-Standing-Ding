package sample;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public void connect(String ip, String name){
		try {
			int port = 8000;
			Socket sock = new Socket(ip,port);


			
			OutputStreamWriter osw = new OutputStreamWriter(sock.getOutputStream());
			PrintWriter pw = new PrintWriter(osw);

			osw.write(name);
			osw.flush();

			sock.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
