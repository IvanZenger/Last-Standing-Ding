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

	List<String> playerName = new ArrayList<String>();

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

			playerName.add(name);
			
			

			os.close();
			is.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public Color newColor(){
		Random random = new Random();

		int red = random.nextInt(255);
		int green = random.nextInt(255);
		int blue = random.nextInt(255);
		
		return Color.rgb(red, green, blue);
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
