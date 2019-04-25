package sample;

import com.sun.javafx.geom.Line2D;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.Math.PI;


public class GameTestIvan extends Application implements EventHandler<KeyEvent> {

	int count = 5;


	private double from_x = 50;
	private double from_y = 50;
	private double to_x;
	private double to_y;
	private int line_no = 0;
	private boolean done = false;
	private double angle = 0;
	private int speed = 1;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void handle(KeyEvent event) {
		switch (event.getCode()){
			case LEFT:
				direction = Direction.LEFT;
				break;
			case RIGHT:
				direction = Direction.RIGHT;
				break;
		}
		
	}




	public enum Direction{
		LEFT,RIGHT,DEFAULT
	}
	private Direction direction = Direction.DEFAULT;
	
	@Override
	public void start(Stage primaryStage) {

		Canvas canvas = new Canvas(500,500);
		GraphicsContext gc;
		gc = canvas.getGraphicsContext2D();

		gc.setStroke(Color.BLUE);
		gc.setLineWidth(5);

		//GameTestIvan Hallo = new GameTestIvan(gc,Color.GREEN);

		StackPane root = new StackPane();


		Timeline tl = new Timeline();

		KeyFrame kf = new KeyFrame(Duration.seconds(0.2),event ->{
			switch(direction){
				case LEFT:
					//System.out.println("LEFT");
					update(gc,Direction.LEFT);
					//gc.strokeLine(from_x,from_y,to_x,to_y);
					
					//gc.strokeLine(x,y,x+10,y+10);
					break;
				case RIGHT:
					System.out.println("RIGHT");
					//gc.strokeLine(x,y,x+10,y+10);
					
					break;

				default:
					//System.out.println("DEFAULT");
					update(gc,Direction.DEFAULT);
					gc.strokeLine(from_x,from_y,to_x,to_y);;
			}
			
			direction = direction.DEFAULT;

		});


	

	   
		tl.getKeyFrames().add(kf);
		tl.setCycleCount(Timeline.INDEFINITE);

		tl.play();
		
		root.getChildren().addAll(canvas);

		Scene scene = new Scene(root, 500, 500);
		scene.setOnKeyPressed(this);
		primaryStage.setScene(scene);
		primaryStage.show();
	}


	public void update(GraphicsContext gc, Direction direction){


		switch(direction){

			case LEFT:

				angle += 3.6;

				to_x = from_x + sin((angle/360)*(2*PI))*speed;
				to_y = from_y + cos((angle/360)*(2*PI))*speed;

				// System.out.println("ToX: " + to_x + " ToY: " + to_y);
				gc.strokeLine(from_x, from_y, to_x, to_y);

				from_x = from_x + sin((angle/360)*(2*PI))*speed;
				from_y = from_y + cos((angle/360)*(2*PI))*speed;

				System.out.println("[LEFT] from_x: " + from_x +" from_y: "+from_y+" to_x: "+to_x+" to_y: "+to_y);
				break;
			case RIGHT:

				break;

			case DEFAULT:

				angle = 3.6;

				to_x = to_x + (count);
				from_x = from_x + (count);
				System.out.println("[DEFAULT] from_x: " + from_x +" from_y: "+from_y+" to_x: "+to_x+" to_y: "+to_y);
				break;

		}

	}

       
}






         





