package sample;

import javafx.scene.canvas.Canvas;

import java.io.Serializable;

public class MyCanvas extends Canvas implements Serializable {

	private static final long serialVersionUID = -432432354454545L;

	public MyCanvas(double x, double y){
		super(x,y);
	}

}
