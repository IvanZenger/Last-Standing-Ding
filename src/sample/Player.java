package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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




        public static void main(String[] args){
            Player test = new Player("Blue", "Nici", "IP", true);

        }


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
            Pane root = new Pane();
            root.getStyleClass().add("pane");

            final Circle circle = new Circle(10, 30, 30, Color.FIREBRICK);
            circle.setEffect(new Reflection());

            Scene scene = new Scene(root,400,400);



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
