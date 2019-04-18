package sample;

/**
 @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
 @version 1.0.0
 @since 18.04.2019
 */
public class Player {

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
