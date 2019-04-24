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

import static java.lang.Math.cos;
import static java.lang.Math.sin;


public class GameTestIvan extends Application implements EventHandler<KeyEvent> {

	int count = 5;
	

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

	double fromx = 50;
	double fromy = 50;
	double tox = 50;
	double toy = 50;


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
		gc.setLineWidth(10);

		//GameTestIvan Hallo = new GameTestIvan(gc,Color.GREEN);

		StackPane root = new StackPane();


		Timeline tl = new Timeline();

		KeyFrame kf = new KeyFrame(Duration.seconds(0.2),event ->{
			switch(direction){
				case LEFT:
					System.out.println("LEFT");
					update(gc,Direction.LEFT);
					gc.strokeLine(fromx,fromy,tox,toy);
					gc.stroke();
					//gc.strokeLine(x,y,x+10,y+10);
					break;
				case RIGHT:
					System.out.println("RIGHT");
					//gc.strokeLine(x,y,x+10,y+10);
					gc.stroke();
					break;

				default:
					//System.out.println("DEFAULT");
					update(gc,Direction.DEFAULT);
					gc.strokeLine(fromx,fromy,tox,toy);
					gc.stroke();
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

	double angle = 3.1415926535897932384626433832795;
	public void update(GraphicsContext gc, Direction direction){


		switch(direction){

			case LEFT:

				angle += 0.12;
				tox += 5 * cos(angle);
				toy += 5 * sin(angle);
				break;
			case RIGHT:

				break;

			case DEFAULT:

				toy = toy + (count);
				System.out.println(toy);
				break;

		}

	};

       
}
