package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.PI;

/**
 * @version 1.0.0
 * @since 18.04.2019
 */
public class Player implements Runnable {

	//Instanzvariablen
	private double fromX;
	private double fromY;
	private double angle = 90;
	private Color color;
	private String name;
	private String direction = "NONE";
	private int lineCounter = 0;
	private double changeAngle = 4.4;
	private double toX = 120;
	private double toY = 100;
	private boolean emptyLine = false;
	private double speed = 2.8;
	private GraphicsContext gc;

	//private static int[][] saveWay = new int[600][600];
	private int searchField = 3;
	private static int[][] saveWay = new int[GUI.getWIDTH()][GUI.getHEIGHT()];

	// 4-> speed: min: 3.5	max: 5
	// 3-> speed: min: 2.8
	// 2-> speed: min: 2.1

	// Bei einer Queue Liste rutschten die nächsten Elemente nach, wenn man ein Element löscht
	private Queue<Integer> queue = new LinkedList<>();

	public Player(){}

	/**
	 * @version 1.0.0
	 * @since 18.04.2019
	 */
	public Player(double fromX, double fromY, double angle, Color color, String name, String direction) {
		this.fromX = fromX;
		this.fromY = fromY;
		this.angle = angle;
		this.color = color;
		this.name = name;
		this.direction = direction;
	}
	/**
	 * @version 1.0.0
	 * @since 18.04.2019
	 * Diese Methode rechnet mithilfe der Richtung die nächsten punkte der Linie aus.
	 */
	public boolean getNextLine(GraphicsContext gc) {

		lineCounter++;
		//Vorletzte Koordinate wird gepeichert um in einem grösserem Feld suchen zu kömmen
		queue.add((int) toX);
		queue.add((int) toY);

		gc.setStroke(color);
		gc.setLineWidth(5);

		if (direction.equals("LEFT")) {
		//	System.out.println("LEFT");
			this.angle += changeAngle; //Winkel der linie wird um 8 Grad erhöht
		} else if (direction.equals("RIGHT")) {
			this.angle -= changeAngle;//Winkel der linie wird um 8 Grad verringert
		}

		this.toX = this.fromX + Math.sin((this.angle / 360) * (2 * PI)) * speed;//neue X koordinate in Abhängigkeit vom Winkel berechnen
		this.toY = this.fromY + Math.cos((this.angle / 360) * (2 * PI)) * speed; //neue Y koordinate in Abhängigkeit vom Winkel berechnen

		if (lineCounter % 120 > 20) {  //Bei jeder Iteration des Timers wird der Counter erhöht und dann wird in regelmässigen Zeitabständen wird dann keine Linie gezeichntet
			gc.strokeLine(this.fromX, this.fromY, toX, toY); //neue Linie zeichnen

			emptyLine = false;
		} else {       //Wenn man keine Linie zeichnet:
			Paint color = gc.getStroke();

			//Die vorletzten Koordinaten aus der Queue herauslesen und wieder hinein speichern
			int x = queue.peek();
			queue.remove(); //
			int y = queue.peek();
			queue.remove();
			queue.add(x);
			queue.add(y);
			//alter Kreis übermalen
			gc.setStroke(Color.BLACK);
			gc.strokeArc(x - 1, y - 1, 3, 3, 0, 360, ArcType.ROUND);
			//neuer Kreis zeichnen
			gc.setStroke(color);
			gc.strokeArc(toX - 1, toY - 1, 1, 1, 0, 360, ArcType.ROUND);

			emptyLine = true;
		}
		
		if (checkOnCrash(emptyLine)) {
			return true;
		}

		this.fromX = toX;
		this.fromY = toY;

		return false;
	}
	// Diese Methode ändert bei Key Events die Richtung der Linie
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
			if (event.getCode() == KeyCode.LEFT && this.direction != "RIGHT" || event.getCode() == KeyCode.RIGHT && this.direction != "LEFT") {
				this.direction = "NONE";

			}
		});
	}
	/**
	Diese Methode überprüft mithilfe eines 2d Arrays, ob man sich in nahe (so nahe dass sich die Linien überlappen) eines bereits gezeichneten Pixels befindet.
	@param emptyLine wenn diese Variable true ist, werden die Prüfungen und das speichern des weges ausgesetzt.
	 */
	public boolean checkOnCrash(boolean emptyLine) {
		//  System.out.println(Integer.toHexString(bi.getRGB(50, 550)));

		int secondLastX = queue.peek();
		queue.remove();
		int secondLastY = queue.peek();
		queue.remove();

		//	System.out.println(secondLastX + " " + secondLastY + "     " + toX + " " + toY);

		if (toX >= GUI.getWIDTH() || toX <= 0 || toY >= GUI.getHEIGHT() || toY <= 0) {// Prüfung, ob man ausserhalb des Spielfeldes ist.
			return true;
		} else if (saveWay[(int) toX][(int) toY] == 1 && !emptyLine) {  //einfach Prüfung ob man einen abgespeicherten Pixel getroffen hat.
			return true;
		}


		/*
		Da es unawahrscheinlich ist, dass die neue Kordinate genau auf den Belegten Platz im Array
		triff, wird von dem Pixel aus in 3x3 felder im array nach abgesepeicherten Pixeln gesucht.
		 */
		//oben Links suchen
		try {

			for (int i = 1; i <= searchField; i++) {
				for (int j = 1; j <= searchField; j++) {
					if (saveWay[(int) toX - i][(int) toY - j] == 1 && (int) toX - i != fromX && (int) toY - j != fromY && (int) toX - i != secondLastX && (int) toY - j != secondLastY && !emptyLine) {
						return true;
					}
				}
			}
			//oben rechts suchen
			for (int i = 1; i <= searchField; i++) {
				for (int j = 1; j <= searchField; j++) {

					if (saveWay[(int) toX + i][(int) toY - j] == 1 && (int) toX + i != fromX && (int) toY - j != fromY && (int) toX + i != secondLastX && (int) toY + j != secondLastY && !emptyLine) {
						return true;
					}
				}
			}
			//unten links suchen
			for (int i = 1; i <= searchField; i++) {
				for (int j = 1; j <= searchField; j++) {

					if (saveWay[(int) toX - i][(int) toY + j] == 1 && (int) toX - i != fromX && (int) toY + j != fromY && (int) toX - i != secondLastX && (int) toY - j != secondLastY && !emptyLine) {
						return true;
					}
				}
			}
//unten rechts suchen
			for (int i = 1; i <= searchField; i++) {
				for (int j = 1; j <= searchField; j++) {

					if (saveWay[(int) toX + i][(int) toY + j] == 1 && (int) toX + i != fromX && (int) toY + j != fromY && (int) toX + i != secondLastX && (int) toY + j != secondLastY && !emptyLine) {
						return true;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) { //wenn man zu nahe an den Rand fährt
			return true;
		}
		if (saveWay[(int) toX][(int) toY] == 0 && !emptyLine) {  //Wenn man die Linie am Zeichnen ist und die nächste errechnete Koordinate leer ist.
			saveWay[(int) toX][(int) toY] = 1;                   // wird sie als belegt gepeichert
			return false;
		}
		return false; // Java Konform
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

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public void setToX(double toX) {
		this.toX = toX;
	}

	public void setToY(double toY) {
		this.toY = toY;
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
