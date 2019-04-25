package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import static java.lang.Math.PI;

public class GUI extends Application implements Runnable{

    private double from_x = 100;
    private double from_y = 300;
    private double to_x;
    private double to_y;
    private int line_no = 0;
    private double angle = 100;
    private double speed = 1.2;
    private Canvas canvas = new Canvas(600, 600);
    private Canvas new_line = new Canvas(600, 600);
    private GraphicsContext gc = new_line.getGraphicsContext2D();


    private AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long now) {
            update(gc);
        }
    };

    /* Späterer Konstruktor
    public GUI(){
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
    }*/

    //Override von Application
    @Override
    public void start(Stage primaryStage) {

        canvas.setFocusTraversable(true); //Damit die KeyInputs registriert wereden
        StackPane root = new StackPane();
        root.getChildren().addAll(new Canvas(), canvas);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setResizable(false);//Damit man die Grösse des Fensters nicht verändern kann.
        primaryStage.setTitle("Last Standing Ding"); //Titel setzen
        primaryStage.setScene(scene);
        primaryStage.show();


        root.getChildren().add(line_no, new_line);
        root.setStyle("-fx-background-color: BLACK;"); //Hintergrundfarbe setzen
        timer.start(); //timer starten

//        canvas.setOnMouseEntered((a) -> System.out.println("hi"));
//        canvas.setOnMousePressed((a) -> System.out.println("focus"));

        canvas.setOnKeyPressed(event -> {
            switch (event.getCode()) { //es wird die Taste ausgelesen
                case LEFT:
                        angle += 8; //Winkel der linie wird um 8 Grad erhöht
                    break;
                case RIGHT:
                        angle -= 8;//Winkel der linie wird um 8 Grad verringert
                    break;
            }
        });
      /*    canvas.setOnKeyReleased(event -> System.out.println(event.getCharacter()+ "Gtueb Tag2222"));
      canvas.setOnMousePressed((event) -> setFromPos(event));*/
    }

    //Override von runnable
    @Override
    public void run() {
        launch();
    }

    private void update(GraphicsContext gc){ //Bei jedem Timer Tick wird diese Methode ausgeführt

        drawLine(gc); //Linie zeichnen bzw. updaten.

        try {
            Thread.sleep(10); //geschwindigkeit der Linie regulieren
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
/**
 * @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
 * Diese Methode zeichnet mithilfe des speeds und des Winkels die nächste linie aus und zeichnet diese.
 * @version 1.0.0
 */
    public void drawLine(GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);

        // (angle/360)*(2*PI) --damit man den Winkel in Grad angeben kann ansonsten müsste man 2pi als 360Grad angeben.
        to_x = from_x + Math.sin((angle/360)*(2*PI))*speed; //neue X koordinate in Abhängigkeit vom Winkel berechnen
        to_y = from_y + Math.cos((angle/360)*(2*PI))*speed; //neue Y koordinate in Abhängigkeit vom Winkel berechnen
        checkOnCrash();
        gc.strokeLine(from_x, from_y, to_x, to_y); //neue Linie zeichnen

        from_x = from_x + Math.sin((angle/360)*(2*PI))*speed;
        from_y = from_y + Math.cos((angle/360)*(2*PI))*speed;
    }

    /**
     * @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
     * Diese Methode checkt, ob sich der Spieler auserhalb des Spielfeldes oder auf einer anderen Linie befindet.
     * Falls dies so sein sollte, wird der Timer abgebrochen.
     * @version 1.0.0
     */
    public void checkOnCrash(){
      //  System.out.println(Integer.toHexString(bi.getRGB(50, 550)));
        if (600 < to_x || 0 > to_x || 600 < to_y || 0 > to_y){
            timer.stop();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


}












