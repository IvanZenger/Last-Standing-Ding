package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.lang.reflect.Type;

import static java.lang.Math.PI;

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
        private String direction = "NONE";
        private double changeAngle = 6;
		private double toX;
		private double toY;
		private double speed = 2.5;
		private GraphicsContext gc;
		private int[][] saveWay = new int[600][600];

        /**
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

            @version 1.0.0
            @since 18.04.2019
            
            *Diese Methode rechnet mithilfe der Richtung die nächsten punkte der Linie aus.
         */
		public boolean getNextLine(){
			gc.setStroke(color);
			gc.setLineWidth(5);

			if(direction.equals("LEFT")){
				this.angle += changeAngle; //Winkel der linie wird um 8 Grad erhöht
			}
			else if(direction.equals("RIGHT")){
				this.angle -= changeAngle;//Winkel der linie wird um 8 Grad verringert
			}


			this.toX = this.fromX + Math.sin((this.angle/360)*(2*PI))*speed;//neue X koordinate in Abhängigkeit vom Winkel berechnen
			this.toY = this.fromY + Math.cos((this.angle/360)*(2*PI))*speed; //neue Y koordinate in Abhängigkeit vom Winkel berechnen

			//gc.strokeLine(this.fromX, this.fromY, toX, toY); //neue Linie zeichnen
			gc.strokeArc(toX-2, toY-2, 1, 1, 0,360, ArcType.ROUND);

			if (saveWay[(int)toX][(int) toY] != 1){
				saveWay[(int)toX][(int) toY] = 1;
			}
			else{
				return true;
			}
			this.fromX = toX;
			this.fromY = toY;

			if (checkOnCrash()){
				return true;
			}
			else {
				return false;
			}
        }

	@Override
	public void run() {
		GUI.getCanvas().setOnKeyPressed(event -> {
			switch (event.getCode()) { //es wird die Taste ausgelesen
				case LEFT:
					this.direction = "LEFT";
					break;
				case RIGHT:
					this.direction = "RIGHT";
					break;
			}
		});
		GUI.getCanvas().setOnKeyReleased(event -> {
			if (event.getCode() == KeyCode.LEFT && this.direction != "RIGHT" || event.getCode() == KeyCode.RIGHT && this.direction != "LEFT"){
				this.direction = "NONE";

			}
		});
	}

	public boolean checkOnCrash(){
		//  System.out.println(Integer.toHexString(bi.getRGB(50, 550)));
		if (600 < toX || 0 > toX || 600 < toY || 0 > toY){
			return true;
		}
//		else if (toX){
//
//		}
		else {
			return false;
		}
	}




	//Getter / Setter

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


	public void setDirection(String direction) {
		this.direction = direction;
	}

	public double getToX() {
		return toX;
	}

	public double getToY() {
		return toY;
	}
}
