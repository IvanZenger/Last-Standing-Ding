package sample;

import javax.swing.*;
import java.awt.*;
/**
 @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
 @version 1.0.0
 @since 18.04.2019
 */
public class Player extends JFrame {

        //Instanzvariablen
        private String color;
        private String name;
        private String ip;
        private boolean hoster;

        /**
         @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
         @version 1.0.0
         @since 18.04.2019
         */
        public Player(String color, String name, String ip, boolean hoster){
            this.color = color;
            this.name = name;
            this.ip = ip;
            this.hoster = hoster;
        }

        /**
            @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
            @version 1.0.0
            @since 18.04.2019
            @param direction Um die Richtige Richtung der Kurve zu berechnen
            *Diese Methode rechnet mithilfe der Richtung die nächsten punkte der Linie aus.
         */
        public void drawLine(String direction){
            //drawLine




            JPanel panel;
                drawLine();
            }

            public void drawLine() {
                JPanel panel = new JPanel() {

                    public void paintComponent(Graphics g, int i) {

                        g.setColor(Color.BLACK);
                        g.fillRect(0, 0, this.getWidth(), this.getHeight());

                        g.setColor(Color.RED);
                        // g.drawLine(100,10,100,100);

                        g.drawArc(100, 100, 450, 450, i, (i + 1));


                    }
                };
                for (int i = 0; i <360; i++) {
                    add(panel);
                    this.setSize(800, 800);
                    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    this.setVisible(true);
                }


















        }

        /**
         @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
         @version 1.0.0
         @since 18.04.2019
         @description Diese Methode stellt eine Verbindung zum Hoster her oder falls man selbst Hostet wird der Port 8000 geöffnet.
         */
        public void getConnection(){
            //getCommection
        }
}
