package sample;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
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
    private boolean done = false;
    private double angle = 0;
    private int speed = 1;
    Canvas canvas = new Canvas(600, 600);



    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();



        root.getChildren().addAll(new Canvas(), canvas);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setResizable(false);//Damit man die Grösse des Fensters nicht verändern kann.
        primaryStage.setTitle("Last Standing Ding");
        primaryStage.setScene(scene);
        primaryStage.show();


        Canvas new_line = new Canvas(600, 600);
        GraphicsContext gc = new_line.getGraphicsContext2D();
        root.getChildren().add(line_no, new_line);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(gc);
            }
        };
        timer.start();


        canvas.setOnMouseEntered((a) -> System.out.println("hi"));
        canvas.setOnMousePressed((a) -> System.out.println("focus"));


/*
        canvas.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle (KeyEvent event){

                System.out.println(event.getCharacter());

            }
        });

   /*     canvas.setOnMousePressed((event) -> setFromPos(event));*/


    }










    private void update(GraphicsContext gc){







        drawLine(gc);

        try {
            Thread.sleep(5);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void terminate(){
        done = true;
    }


    public static void main(String[] args) {
        launch(args);
    }

    private void drawLine(GraphicsContext gc) {
       // gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);

        angle+=3;

        to_x = from_x + Math.sin((angle/360)*(2*PI))*speed;
        to_y = from_y + Math.cos((angle/360)*(2*PI))*speed;

       // System.out.println("ToX: " + to_x + " ToY: " + to_y);
        gc.strokeLine(from_x, from_y, to_x, to_y);

        from_x = from_x + Math.sin((angle/360)*(2*PI))*speed;
        from_y = from_y + Math.cos((angle/360)*(2*PI))*speed;
       // gc.strokeArc(100, 50, 50, 50, 180, 360, ArcType.OPEN);
       // gc.stroke();
    }



    private void setFromPos(MouseEvent event) {
        from_x = event.getSceneX();
        from_y = event.getSceneY();

    }

    private void setToPos(MouseEvent event) {
        to_x = event.getSceneX();
        to_y = event.getSceneY();
    }

    @Override
    public void run() {
        launch();
    }
}














/*
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



 */