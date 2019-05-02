package sample;

import javafx.scene.paint.Color;

import java.io.Serializable;

public class SerPlayer extends Player implements Serializable{

	private static final long serialVersionUID = -432432354454545L;

	public SerPlayer(double fromX, double fromY, double angle, Color color, String name, String direction){
		super(fromX,fromY,angle,color, name,direction);
	}


}
