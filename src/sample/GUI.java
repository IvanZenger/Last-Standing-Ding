package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;


public class GUI extends Application{

	
	private final static int WIDTH = 900;
	private final static int HEIGHT = 600;


	private double fromX = WIDTH / 4;
	private double fromY = 300;
	private static MyCanvas canvas = new MyCanvas(WIDTH, HEIGHT);
	private Canvas new_line = new Canvas(WIDTH, HEIGHT);
	private GraphicsContext gc = new_line.getGraphicsContext2D();
	//private List<SerPlayer> players = new ArrayList<SerPlayer>();

	public static Map<String, SerPlayer> playerArr = new HashMap<String,SerPlayer>();
	public static List<String> playerName = new ArrayList<String>();
	private int numberOfPlayer = playerName.size();
	private AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			update(gc);
		}
	};
	


	public void createPlayer(){
		if(numberOfPlayer == 1){
			fromX = WIDTH/2;
			fromY = HEIGHT/2;
		}
		else if(numberOfPlayer == 2){
			fromX = WIDTH/3;
			fromY = HEIGHT/2;
		}
		else if(numberOfPlayer == 3){
			fromX = WIDTH/3;
			fromY = HEIGHT/1.5;
		}
		else if(numberOfPlayer == 4){
			fromX = WIDTH/3;
			fromY = HEIGHT/3;
		}
		for(int i = 0; i < numberOfPlayer; i++){


			if(numberOfPlayer == 2 && i == 1){
				fromX = WIDTH/1.5;
			}
			else if(numberOfPlayer == 3){
				if(i == 1){
					fromX = WIDTH/1.5;
				}
				else if(i == 2){
					fromX = WIDTH/2;
					fromY = HEIGHT/3;
				}
			}
			else if(numberOfPlayer == 4){
				if(i == 1){
					fromX = WIDTH/1.5;
				}
				else if(i == 2){
					fromY = HEIGHT/1.5;
				}else if(i == 3){
					fromX = WIDTH/3;
				}
			}

			SerPlayer player = new SerPlayer(fromX, fromY, 180, newColor(), playerName.get(i),"NONE");
			playerArr.put(player.getName(), player);
			
			
		}
	}

    //Override von Application
    @Override
    public void start(Stage primaryStage) {

        canvas.setFocusTraversable(true); //Damit die KeyInputs registriert wereden
        StackPane root = new StackPane();
        //root.getChildren().addAll(new Canvas(), canvas);
        root.getChildren().addAll(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setResizable(false);//Damit man die Grösse des Fensters nicht verändern kann.
        primaryStage.setTitle("Last Standing Ding"); //Titel setzen
        primaryStage.setScene(scene);
        primaryStage.show();


        root.getChildren().add(0, new_line);
        root.setStyle("-fx-background-color: BLACK;"); //Hintergrundfarbe setzen

		System.out.println(numberOfPlayer);
		//createPlayer();
        timer.start(); //timer starten


		//System.out.println(numberOfPlayer);


		for(int i = 0; i < numberOfPlayer; i++){
			new Thread(playerArr.get(playerName.get(i))).start();
		}

    }
    

    private void update(GraphicsContext gc){ //Bei jedem Timer Tick wird diese Methode ausgeführt

		//drawLine(gc); //Linie zeichnen bzw. updaten.
		for (int i = 0; i < numberOfPlayer; i++) {
			new Thread(playerArr.get(playerName.get(i))).start();
		}
		
		for (int i = 0; i < numberOfPlayer; i++) {
			if (playerArr.get(playerName.get(i)).getNextLine(gc)){
				this.timer.stop();
			}


		}

		try {
			Thread.sleep(4); //geschwindigkeit der Linie regulieren
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

    /**
     * Diese Methode checkt, ob sich der Spieler auserhalb des Spielfeldes oder auf einer anderen Linie befindet.
     * Falls dies so sein sollte, wird der Timer abgebrochen.
     * @version 1.0.0
     */


    
	public Color newColor(){
		Random random = new Random();

		int red;
		int green;
		int blue;

		int counter = 0;

		do {
			red = random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			counter++;
			
		}while(red + green + blue < 400 && counter < 20);

		return Color.rgb(red, green, blue,1);
	}


	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static MyCanvas getCanvas() {
		return canvas;
	}

	public static void setCanvas(MyCanvas canvas) {
		GUI.canvas = canvas;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}


}

