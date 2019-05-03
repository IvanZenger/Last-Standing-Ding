package sample;

import java.io.*;
import java.net.Socket;

/**
 * Hier wird der Client verwaltet
 */
public class Client{
	private String ip = "127.0.0.1";
    private int port = 8000;

    ObjectOutputStream oos;


	/**
	 * Zum Testen des Clients
	 */
	public void start(GUI gui){

	//	SerPlayer player = new SerPlayer(200, 200, 180, Color.BLUE, "Ivan","LEFT"); //Anders machen
	//	GUI.playerArr.put(player.getName(),player);
     //   System.out.println(GUI.playerArr);

		//System.out.println(player);

		//new Thread(new ServerData(player)).start();
		//new Thread((Runnable) new GUI()).start(window);

		//	gui.start();
        communicateServer(gui);

	}


	/**
     * Hier wird wird eine verbindung vom Client hergestellt
	 */
    public boolean connect(String ip, String name) {

        try {
            Socket socket = new Socket(ip, port);
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(name);
            oos.flush();
            System.out.println("Ferti" + name);
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

       // Socket socket = null; //neuer Socket
        //try {
         //   socket = new Socket(ip,port);
        //} catch (IOException e) {
         //   e.printStackTrace();
        //}

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
