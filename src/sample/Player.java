package sample;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static java.lang.Math.PI;
import static sample.GameTestIvan.Direction.LEFT;
import static sample.GameTestIvan.Direction.RIGHT;

/**
 @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
 @version 1.0.0
 @since 18.04.2019
 */
public class Player implements Runnable{

        //Instanzvariablen
		private double fromX;
		private double fromY;
		private double angle;
        private Color color;
        private String name;


		private double toX;
		private double toY;

		private double speed = 1.2;

		private GraphicsContext gc;

        public static void main(String[] args){
            //Player test = new Player("Blue", "Nici", "IP", true);

        }


        /**
         @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
         @version 1.0.0
         @since 18.04.2019
         */
        public Player(double fromX, double fromY, double angle, Color color, String name, GraphicsContext gc){
        	this.fromX = fromX;
        	this.fromY = fromY;
        	this.angle = angle;
        	this.color = color;
            this.name = name;
            this.gc = gc;
 
        }

        /**
            @author Nicola Zurbügg / zurbrueggn / NiciAlmighty
            @version 1.0.0
            @since 18.04.2019
            
            *Diese Methode rechnet mithilfe der Richtung die nächsten punkte der Linie aus.
         */
		public void getNextLine(String direction){
			gc.setStroke(color);
			gc.setLineWidth(5);

			if(direction.equals("LEFT")){
				this.angle += 8; //Winkel der linie wird um 8 Grad erhöht
			}
			else if(direction.equals("RIGHT")){
				this.angle -= 8;//Winkel der linie wird um 8 Grad verringert
			}


			this.toX = this.fromX + Math.sin((this.angle/360)*(2*PI))*speed;//neue X koordinate in Abhängigkeit vom Winkel berechnen
			this.toY = this.fromY + Math.cos((this.angle/360)*(2*PI))*speed; //neue Y koordinate in Abhängigkeit vom Winkel berechnen

			//checkOnCrash();

			gc.strokeLine(this.fromX, this.fromY, toX, toY); //neue Linie zeichnen

			this.fromX = this.fromX + Math.sin((this.angle/360)*(2*PI))*speed;
			this.fromY = this.fromY + Math.cos((this.angle/360)*(2*PI))*speed;

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

	public double getFromX() {
		return fromX;
	}

	public void setFromX(double fromX) {
		this.fromX = fromX;
	}


	public double getFromY() {
		return fromY;
	}

	public void setFromY(double fromY) {
		this.fromY = fromY;
	}

	public double getAngle() {
		return angle;
	}

	public Color getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		GUI.getCanvas().setOnKeyPressed(event -> {
			switch (event.getCode()) { //es wird die Taste ausgelesen
				case LEFT:
					this.getNextLine("LEFT");
					break;
				case RIGHT:
					this.getNextLine("RIGHT");
					break;
			}
		});
	}
}
