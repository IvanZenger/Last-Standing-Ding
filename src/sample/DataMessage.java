package sample;

import javafx.stage.Stage;

import javax.tools.JavaCompiler;

public class DataMessage implements java.io.Serializable {
	private static final long serialVersionUID = 1L;


	public final Stage window;

	public DataMessage(Stage window){
		this.window = window;
	}

}
