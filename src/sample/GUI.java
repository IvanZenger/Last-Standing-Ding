package sample;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



public class GUI extends Application {

    private double from_x = 100;
    private double from_y = 100;
    private double to_x = 101;
    private double to_y = 100;
    private int line_no = 0;

    @Override
    public void start(Stage primaryStage) {

        StackPane root = new StackPane();
        Canvas canvas = new Canvas(600, 600);


        root.getChildren().addAll(new Canvas(), canvas);

        Scene scene = new Scene(root, 600, 600);
        primaryStage.setTitle("Last Standing Ding");
        primaryStage.setScene(scene);
        primaryStage.show();




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
    canvas.setOnMousePressed(event -> {
        Canvas new_line = new Canvas(600, 600);
        GraphicsContext gc = new_line.getGraphicsContext2D();
        for (int i = 0; i < 400; i++){

         //   System.out.println("hi");
            from_x+=1;
            to_x+=1;
          //  setToPos(event);
            System.out.println(from_x + " " + to_x);
            drawLine(gc);
            root.getChildren().add(line_no, new_line);

        }
    });






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

    public static void main(String[] args) {
        launch(args);
    }

    private void drawLine(GraphicsContext gc) {
       // gc.setFill(Color.RED);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(from_x, from_y, to_x, to_y);
    }

    private void setFromPos(MouseEvent event) {
        from_x = event.getSceneX();
        from_y = event.getSceneY();

    }

    private void setToPos(MouseEvent event) {
        to_x = event.getSceneX();
        to_y = event.getSceneY();
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