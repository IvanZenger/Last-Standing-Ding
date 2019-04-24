package sample;

import javafx.scene.control.TextArea;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class WorkerRunnable implements Runnable{

	private Socket cSocket = null;
	private TextArea taHost;
	private TextArea taPlayer;

	public WorkerRunnable(Socket cSocket, TextArea taHost, TextArea taPlayer){
		this.cSocket = cSocket;
		this.taHost = taHost;
		this.taPlayer = taPlayer;
	}

	public void run(){
		try{
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();


			String str = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
			taHost.appendText(str + "\n");
			taPlayer.appendText(str + "\n");
			System.out.println("OUT: " + str);
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
