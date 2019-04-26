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

public class WorkerRunnable implements Runnable{

	private Socket cSocket = null;
	private TextArea taHost;
	private int numberOfPlayers;

	private List<String> playerName = new ArrayList<String>();


	
	public WorkerRunnable(Socket cSocket, TextArea taHost){
		this.cSocket = cSocket;
		this.taHost = taHost;

	}
	public WorkerRunnable(){

	}

	public void run(){
		try{
			InputStream is = cSocket.getInputStream();
			OutputStream os = cSocket.getOutputStream();


			String name = new BufferedReader(new InputStreamReader(is)).lines().collect(Collectors.joining("\n"));
			taHost.appendText("\n" + name);
			System.out.println("OUT: " + name);
			GUI.playerName.add(name);

			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public List<String> getPlayerName() {
		return playerName;
	}

	public void setPlayerName(List<String> playerName) {
		this.playerName = playerName;
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}
}
