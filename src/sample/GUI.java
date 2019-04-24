package sample;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


public class GUI extends Application implements Runnable{

    private double from_x = 100;
    private double from_y = 500;
    private double to_x;
    private double to_y;
    private int line_no = 0;
    private boolean done = false;
    private final double DIAMATER = 50;
    private double angle = 0;
    private int speed = 10;

    private final static double CORRCTION = (Math.sqrt(2)-1)/Math.sqrt(2);


    @Override
    public void start(Stage primaryStage) {



        StackPane root = new StackPane();
        Canvas canvas = new Canvas(600, 600);


        root.getChildren().addAll(new Canvas(), canvas);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setResizable(false);//Damit man die Grösse des Fensters nicht verändern kann.
        primaryStage.setTitle("Last Standing Ding");
        primaryStage.setScene(scene);
        primaryStage.show();




        Canvas new_line = new Canvas(600, 600);
        GraphicsContext gc = new_line.getGraphicsContext2D();
        root.getChildren().add(line_no, new_line);


        System.out.println("Hiiiii");



        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                update(gc);

            }
        };
        timer.start();









   /*     canvas.setOnMousePressed((event) -> setFromPos(event));


        canvas.setOnMouseReleased((event) -> {
            Canvas new_line = new Canvas(600, 600);
            GraphicsContext gc = new_line.getGraphicsContext2D();
            setToPos(event);
            drawLine(gc);
            //final new stright line
            root.getChildren().add(++line_no, new_line);
        });
*/



                /*canvas.setOnMouseDragged((event) -> {
            System.out.println("Test");
            root.getChildren().remove(0);
            Canvas temp_canvas = new Canvas(600, 600);
            GraphicsContext gc = temp_canvas.getGraphicsContext2D();
            this.setToPos(event);
            this.drawLine(gc);
            root.getChildren().add(0, temp_canvas);
        });*/
    }
    private void update(GraphicsContext gc){



        drawLine(gc);

        try {
            Thread.sleep(1);
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

        angle++;

        to_x = from_x + Math.sin(angle)*speed;
        to_y = from_y + Math.cos(angle)*speed;

        System.out.println("ToX: " + to_x + " ToY: " + to_y);
        gc.strokeLine(from_x, from_y, to_x, to_y);

        from_x = from_x + Math.sin(angle)*speed;
        from_y = from_y + Math.cos(angle)*speed;
       // gc.strokeArc(100, 50, 50, 50, 180, 360, ArcType.OPEN);
       // gc.stroke();
    }
    private int[] drawCirlceLine(GraphicsContext gc, double x, double y, double startAngle, double angleToDraw){


        /**
         * Zentrum des Kreises ausrechnen fromel:   rm
         *                                        ------        = DeltaX2
         *                                        ________
         *                                       J ( 1 + m**2)
         *
         *                                       r = radius = DIAMETER
         *                                       m = Steigung = DeltaY / DeltaX
         **
         *                                       J = wurzelbeginn
         *
         *                                       r**2 + DeltaX2**2 = DeltaY2**2
         */
        /*
        double m = (to_x-from_x) / (to_y-from_y);

        double numerator  = (DIAMATER/2)* m; //
        double denominator = 1 + m*m;
        denominator = Math.sqrt(denominator);
        double x2 = numerator  / denominator;


        double y2 = -x2/m;


        double powDiameter = DIAMATER*DIAMATER;
        double powX2 = x2 * x2;

        double powY2 = powDiameter + powX2;
        double y2 = Math.sqrt(powY2);
*/




       //System.out.println("X2: " + x2 + " Y2: " + y2 );
        System.out.println();
        System.out.println();


        gc.strokeArc(x - CORRCTION*(DIAMATER/2), y - CORRCTION*(DIAMATER/2), DIAMATER, DIAMATER, startAngle, -angleToDraw, ArcType.OPEN);


        int[] test = new int[2];
        return test;
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