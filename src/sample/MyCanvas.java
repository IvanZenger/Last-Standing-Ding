package sample;

import javafx.scene.canvas.Canvas;

import java.io.Serializable;

public class MyCanvas extends Canvas implements Serializable {
	public MyCanvas(int x, int y){
		super(x,y);
	}

}
