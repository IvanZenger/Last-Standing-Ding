package sample;

import javafx.scene.control.TextArea;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.Socket;
import java.util.stream.Collectors;

public class WorkerRunnable implements Runnable{

	private Socket cSocket = null;
	private String server = null;
	private TextArea ta;

	public WorkerRunnable(Socket cSocket, String serverText, TextArea ta){
		this.cSocket = cSocket;
		this.server = server;
		this.ta = ta;
	}

	public void run(){
		try{
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();
			long time = System.currentTimeMillis();

			String str = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
			ta.setText("Hallo" + str);
			System.out.println("OUT: " + str);
			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
