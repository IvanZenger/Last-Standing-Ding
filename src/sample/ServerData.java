package sample;


import com.sun.javafx.image.IntPixelGetter;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ServerData implements Runnable{

	private SerPlayer player;
	

	@Override
	public void run() {
        int port = 8000;
        String host = "localhost";

        System.out.println("run sd");
       // ObjectInputStream ois;
        //ObjectOutputStream oos;
        Socket socket = null;
        Socket meinEchoSocket = null;
        try {
            //socket = new Socket("127.0.0.1", port);
            meinEchoSocket = new Socket(host,8000);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
		/*
		while (true) {
			try {
			ois = new ObjectInputStream(socket.getInputStream());				MyCanvas canvas = (MyCanvas) ois.readObject();
				System.out.println(canvas.getWidth());

				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject("Test");
				oos.flush();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {

            }
		}
		*/

        while (true) {
            try {

                System.out.println("naja");

                InputStream socketinstr = meinEchoSocket.getInputStream();
                InputStreamReader isr = new InputStreamReader( socketinstr );
                BufferedReader br = new BufferedReader( isr );
                String antwort = br.readLine();

                OutputStream socketoutstr = meinEchoSocket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter( socketoutstr );
                BufferedWriter bw = new BufferedWriter( osw );
                PrintWriter pw = new PrintWriter(bw);
                bw.write("hgahhuehufehu");
                bw.flush();

                System.out.println("Host = "+host);
                System.out.println("Echo = "+antwort);

                /*
                bw.close();
                br.close();
                meinEchoSocket.close();*/







                //           InputStream ois = socket.getInputStream();
   //             OutputStream oos = socket.getOutputStream();


            //    ois = new ObjectInputStream(socket.getInputStream());
              //  String string = (String) ois.readObject();
                //System.out.println(string);
                //  System.out.println(ip + " " + port);
  //              System.out.println(ois.read());

              //  oos = new ObjectOutputStream(socket.getOutputStream());
               // String name = "Hallo";
  //              oos.write(203);
 //               oos.flush();


            } catch (IOException e) {
                e.printStackTrace();
            } /*catch (ClassNotFoundException e) {
				e.printStackTrace();
			}*/ catch (NullPointerException e) {
                e.printStackTrace();
            }

        }
    }
}
