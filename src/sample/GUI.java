package sample;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.lang.reflect.Type;

import static java.lang.Math.PI;

public class GUI extends Application implements Runnable{

    private double from_x = 100;
    private double from_y = 300;
    private double to_x;
    private double to_y;
    private int line_no = 0;
    private double angle = 90;
    private double speed = 2;
    private int iterations = 0;
    private String direction = "NONE";
    private Canvas canvas = new Canvas(600, 600);
    private Canvas new_line = new Canvas(600, 600);
    private GraphicsContext gc = new_line.getGraphicsContext2D();
    private StackPane root = new StackPane();

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
                    direction = "LEFT";
                    break;
                case RIGHT:
                    direction = "RIGHT";
                    break;
            }
        });
        canvas.setOnKeyReleased(event -> {
            if (event.getCode()== KeyCode.LEFT && direction != "RIGHT" || event.getCode() == KeyCode.RIGHT && direction != "LEFT"){
                direction = "NONE";
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
        Color color;
        iterations+=1;
        updateAngle();
        calculateNewCoordinates();
        checkOnCrash(); //es wird geprüft ob sich der Spieler auserhalb des spieles und oder sich auf einer anderen Linie befindet.
        if (iterations % 140 >= 0 && iterations % 140 < 20){
            color = new Color(0,0,1,1);
            drawCirlce(color);
        }
        else{
            color = new Color(0,0,1,1);
            drawLine(gc, color); //Linie zeichnen bzw. updaten.
        }
       // System.out.println(iterations % 5000);




        from_x = to_x; //neue Anfangskoorinaten festlegen
        from_y = to_y;
        try {
            Thread.sleep(1); //geschwindigkeit der Linie regulieren
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
    public void drawLine(GraphicsContext gc, Color color) {
        gc.setStroke(color);
        gc.setLineWidth(3);
        gc.strokeLine(from_x, from_y, to_x, to_y); //neue Linie zeichnen
    }


    public void drawCirlce(Color color){
        Circle circle = new Circle(to_x, to_y, 4, color);

        root.getChildren().addAll(circle);
    }

public void calculateNewCoordinates(){
    // (angle/360)*(2*PI) --damit man den Winkel in Grad angeben kann ansonsten müsste man 2pi als 360Grad angeben.
    to_x = from_x + Math.sin((angle/360)*(2*PI))*speed; //neue X koordinate in Abhängigkeit vom Winkel berechnen
    to_y = from_y + Math.cos((angle/360)*(2*PI))*speed; //neue Y koordinate in Abhängigkeit vom Winkel berechnen
}




    /**
     * @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
     * Diese Methode checkt, ob sich der Spieler auserhalb des Spielfeldes oder auf einer anderen Linie befindet.
     * Falls dies so sein sollte, wird der Timer abgebrochen.
     * @version 1.0.0
     */
    public void checkOnCrash(){

       // WritableImage writableImage = new_line.snapshot(null, null);
       // Color x = writableImage.getPixelReader().getColor((int) to_x, (int)to_y);
       // System.out.println(x);
        // || x.equals("0x0000ffff")

        if (to_x >= 600 || to_x <= 0|| to_y >= 600 || to_y <= 0){
            timer.stop();
            System.out.println(to_x + " " + to_y);

        }
      //  System.out.println(x.getRed() + " " +x.getGreen() + " " + x.getBlue());
    }

    public void updateAngle(){
        if (direction == "LEFT"){
            angle += 6; //Winkel der linie wird um 8 Grad erhöht
        }
        else if (direction == "RIGHT"){
            angle -= 6;//Winkel der linie wird um 8 Grad verringert
        }
    }

    public static void main(String[] args) {
        launch(args);
    }


}












