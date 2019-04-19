package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;

public class GUI extends Application {
    @Override
    public void start(Stage stage) {


        Line line = new Line();
        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(100);
        line.setEndY(100);

        line.setStrokeWidth(10);
        line.setStroke(Color.RED);

        TranslateTransition trans = new TranslateTransition();
        trans.setByX(100);
        trans.setDuration(Duration.millis(1000));
        trans.setCycleCount(100);
        trans.setAutoReverse(true);
        trans.setNode(line);
        trans.play();






        Circle circle = new Circle();

        //Startposition setzen
        circle.setCenterX(150.0);
        circle.setCenterY(135.0);

        //Radius
        circle.setRadius(100.0);

        //Farbe des Kreises auswählen
        circle.setFill(Color.BROWN);


        TranslateTransition translateTransition = new TranslateTransition();

        //Dauer der Animation bestimmen
        translateTransition.setDuration(Duration.millis(300));

        //referenz auf circle herstellen
        translateTransition.setNode(circle);

        //verschiebung bestimmen
        translateTransition.setByX(300);

        //Wiederholungen bestimmen //optional
        translateTransition.setCycleCount(500);

        //die Animation automatisch umkehren, wenn der eine Weg fertig ist.
        translateTransition.setAutoReverse(true);

        //Animtation beginnen
        translateTransition.play();


        Group root = new Group(circle, line);

        Scene scene = new Scene(root, 600, 300);
        scene.setFill(Color.BLACK);
        stage.setTitle("Animationen Test");
        stage.setScene(scene);
        stage.show();














    }
    public static void main(String args[]){
        launch(args);
    }
}