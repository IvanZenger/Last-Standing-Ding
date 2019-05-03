package sample;

import java.io.*;
import java.net.Socket;

/**
 * Hier wird der Client verwaltet
 */
public class Client{
	private String ip = "172.16.2.139";
    private int port = 8000;

    ObjectOutputStream oos;


	/**
	 * Zum Testen des Clients
	 */
	/**
     * Hier wird wird eine verbindung vom Client hergestellt
	 */
    public boolean connect(String ip, String name) {

        try {
            int port = 8000;
            Socket socket = new Socket(ip,port); //neuer Socket

            OutputStreamWriter osw = new OutputStreamWriter(socket.getOutputStream());//Ausgabe
            PrintWriter pw = new PrintWriter(osw);//Verarebeitung der Ausgabe

            osw.write(name);//Ausgabe zum Server
            osw.flush();

            socket.close();
            return true;

        } catch (IOException e) {
            return false;
        }

    }


    /**
     *
     * @param gui
     */
    public void communicateServer(GUI gui){
    	
        Socket socket = null;
        try {
            socket = new Socket(ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Player player = gui.playerArr.get(gui.playerName);

        new Thread(new ServerData()).start();

	}

}
