package sample;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Math.random;

/**
 * Hier werden der Client verarbeitet(wen er einem Spiel beitretten möchte )
 */
public class WorkerRunnable implements Runnable{

	private Socket cSocket;
	private TextArea taHost;

	/**
	 * KOnstruktor
	 * @param cSocket
	 * @param taHost
	 */
	public WorkerRunnable(Socket cSocket, TextArea taHost){
		this.cSocket = cSocket;
		this.taHost = taHost;

	}

	/**
	 * Hier wird der name ausgelesen und zu der "Soielerliste" hinzugefüht
	 */
	public void run(){
		try{
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();
			
			String name = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
			taHost.appendText("\n" + name);
			GUI.playerName.add(name);

			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
