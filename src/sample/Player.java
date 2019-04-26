package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

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

		private double speed = 3;

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
		public void getNextLine(){
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

			//checkOnCrash();


			gc.strokeLine(this.fromX, this.fromY, toX, toY); //neue Linie zeichnen

			this.fromX = this.fromX + Math.sin((this.angle/360)*(2*PI))*speed;
			this.fromY = this.fromY + Math.cos((this.angle/360)*(2*PI))*speed;

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
