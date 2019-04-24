package sample;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
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
    private double angle = 300;
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

        canvas.setFocusTraversable(true);
        canvas.setOnMouseEntered((a) -> System.out.println("hi"));
        canvas.setOnMousePressed((a) -> System.out.println("focus"));

        canvas.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case LEFT:
                    angle+=5;
                    break;
                case RIGHT:
                    angle-=5;
                    break;

            }



        });
        canvas.setOnKeyReleased(event -> System.out.println(event.getCharacter()+ "Gtueb Tag2222"));
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












