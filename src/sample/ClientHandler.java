package sample;


import javafx.scene.paint.Color;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {



    private Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }



    // Server Listening //
	@Override
	public void run() {

		System.out.println("Try read Client input");
		while (true) {
			try {


                System.out.println("ClientHandler");


                OutputStream socketoutstr = clientSocket.getOutputStream();
                OutputStreamWriter osr = new OutputStreamWriter( socketoutstr );
                BufferedWriter bw = new BufferedWriter( osr );
                PrintWriter pw = new PrintWriter(bw);
                bw.write("Test");
                bw.flush();

                InputStream socketinstr = clientSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader( socketinstr );
                BufferedReader br = new BufferedReader( isr );

                String anfrage;
                anfrage = br.readLine();
                System.out.println(anfrage);


                /*
                bw.close();
                br.close();*/

              //  serverSocket.close();











                //canvas canvasSer = new canvas();

				//System.out.println(canvasSer.getProperties());
  //              InputStream ois = clientSocket.getInputStream();
//                OutputStream oos = clientSocket.getOutputStream();


              //  oos = new ObjectOutputStream(clientSocket.getOutputStream());

//				oos.write(20);
//				oos.flush();


				//SerPlayer client = (SerPlayer) ois.readObject();
              //  ois = new ObjectInputStream(clientSocket.getInputStream());

  //              System.out.println(ois.read());
			//	String  test = (String) ois.readObject();
			//	System.out.println(test);

				// => Client Nachricht schiken
//                oos.write((int) player.getToX());
//                oos.write((int) player.getToY());
//                System.out.println(player.getToX()+ " " +player.getToY());
                

				//oos.close();


			} catch (IOException e) {
                e.printStackTrace();


            }

		}


	}


}
