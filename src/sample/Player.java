package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import java.util.LinkedList;
import java.util.Queue;

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
		private double angle = 90;
        private Color color;
        private String name;
        private String direction = "NONE";
        private double changeAngle = 4.8;
		private double toX = 120;
		private double toY = 100;
		private double speed = 2.9;
		private GraphicsContext gc;
		private static int[][] saveWay = new int[GUI.getWIDTH()][GUI.getHEIGHT()];
		private int searchField = 2;
		// 4-> speed: min: 3.5	max: 5
		// 3-> speed: min: 2.8	max:
		// 2-> speed: min: 2.1	max:

		private Queue<Integer> queue = new LinkedList<>();


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



			queue.add((int)toX);
			queue.add((int)toY);


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

			gc.strokeLine(this.fromX, this.fromY, toX, toY); //neue Linie zeichnen
			//gc.strokeArc(toX-2, toY-2, 1, 1, 0,360, ArcType.ROUND);

			if (checkOnCrash()){
				return true;
			}

			this.fromX = toX;
			this.fromY = toY;

			return false;
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

		int secondLastX = queue.peek();
		queue.remove();
		int secondLastY = queue.peek();
		queue.remove();

	//	System.out.println(secondLastX + " " + secondLastY + "     " + toX + " " + toY);

		if (toX >= GUI.getWIDTH() || toX <= 0 || toY >= GUI.getHEIGHT() || toY <= 0){
			return true;
		}

		else if (saveWay[(int)toX][(int) toY] == 1){
			return true;
		}

		//oben Links suchen
	try {

	for (int i = 1; i <= searchField; i++) {
		for (int j = 1; j <= searchField; j++) {
			if (saveWay[(int) toX - i][(int) toY - j] == 1 && (int) toX - i != fromX && (int) toY - j != fromY && (int) toX - i != secondLastX && (int) toY - j != secondLastY) {
				return true;
			}
		}
	}
//oben rechts suchen
	for (int i = 1; i <= searchField; i++) {
		for (int j = 1; j <= searchField; j++) {

			if (saveWay[(int) toX + i][(int) toY - j] == 1 && (int) toX + i != fromX && (int) toY - j != fromY && (int) toX + i != secondLastX && (int) toY + j != secondLastY) {
				return true;
			}
		}
	}
//unten links suchen
	for (int i = 1; i <= searchField; i++) {
		for (int j = 1; j <= searchField; j++) {

			if (saveWay[(int) toX - i][(int) toY + j] == 1 && (int) toX - i != fromX && (int) toY + j != fromY && (int) toX - i != secondLastX && (int) toY - j != secondLastY) {
				return true;
			}
		}
	}
//unten rechts suchen
	for (int i = 1; i <= searchField; i++) {
		for (int j = 1; j <= searchField; j++) {

			if (saveWay[(int) toX + i][(int) toY + j] == 1 && (int) toX + i != fromX && (int) toY + j != fromY && (int) toX + i != secondLastX && (int) toY + j != secondLastY) {
				return true;
			}
		}
	}
	}
	catch (ArrayIndexOutOfBoundsException e){ //wenn man zu nahe an den Rand fährt
		return true;
	}
	if (saveWay[(int)toX][(int) toY] == 0){
		saveWay[(int)toX][(int) toY] = 1;
		return false;
	}

	return false;

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
