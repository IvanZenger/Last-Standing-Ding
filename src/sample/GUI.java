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

/**
 * Hier wird das Spiel auf dem Server verwaltet
 */
public class GUI extends Application{

	
	private final static int WIDTH = 900; //Spielfeld breite
	private final static int HEIGHT = 600; //Speilfeld weite


	private double fromX = WIDTH / 4; //Wird für die Start-X-Jordinaten verwendet
	private double fromY = 300;
	private static MyCanvas canvas = new MyCanvas(WIDTH, HEIGHT);
	private Canvas new_line = new Canvas(WIDTH, HEIGHT);
	private GraphicsContext gc = new_line.getGraphicsContext2D();
	//private List<SerPlayer> players = new ArrayList<SerPlayer>();

	public Map<String, SerPlayer> playerArr = new HashMap<String,SerPlayer>();
	public static List<String> playerName = new ArrayList<String>();
	private int numberOfPlayer = playerName.size();

	private AnimationTimer timer = new AnimationTimer() {
		@Override
		public void handle(long now) {
			update(gc);
		}
	};
	


	public void createPlayer(String name){
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

			SerPlayer player = new SerPlayer(fromX, fromY, 180, newColor(), name,"NONE");
			playerArr.put(name, player);


	}

    //Override von Application
    @Override
	/**
	 * Hier wird ein Spielfeld erstellt das Spiel gestartet
	 */
    public void start(Stage primaryStage) {

        canvas.setFocusTraversable(true); //Damit die KeyInputs registriert werden
        StackPane root = new StackPane();
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


		//Hier wird für jeden Spieler einen neuen Thread gestartet, inwelchem eine verbindung zum Server hergestellt wird
		for(int i = 0; i < numberOfPlayer; i++){
			new Thread(playerArr.get(playerName.get(i))).start();
		}

    }
    

    /**
	 * Hier werden die Daten Akutalisiert => Linien werden auf dem Canvas gezeichnet
     */
    private void update(GraphicsContext gc){ //Bei jedem Timer Tick wird diese Methode ausgeführt

		//drawLine(gc); //Linie zeichnen bzw. updaten.
//		for (int i = 0; i < /*numberOfPlayer*/ playerName.size(); i++) {
//
//		}

		new Thread(playerArr.get(playerName.get(0))).start();

//		    System.out.println(playerArr);
//            System.out.println(playerName);
			if (playerArr.get(playerName.get(0)).getNextLine(gc)){
				this.timer.stop();
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


	/**
	 * Hier wird eine random generiert Farbe zurück gegeben für die Schlangen-farben
	 * @return
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

	// Getter und Setter //

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

