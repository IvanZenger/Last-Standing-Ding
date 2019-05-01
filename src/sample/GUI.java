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

	private final static int WIDTH = 600;
	private final static int HEIGHT = 600;


	private double fromX = WIDTH / 4;
	private double fromY = 300;
	private int line_no = 0;
	private static Canvas canvas = new Canvas(WIDTH, HEIGHT);
	private Canvas new_line = new Canvas(WIDTH, HEIGHT);
	private GraphicsContext gc = new_line.getGraphicsContext2D();
	private List<Player> players = new ArrayList<Player>();
	
	public  static List<String> playerName = new ArrayList<String>();
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







			Player player = new Player(fromX, fromY, 360, newColor(), playerName.get(i), gc);
			players.add(player);
			//from_x += margin;
		}
	}

    //Override von Application
    @Override
    public void start(Stage primaryStage) {

        canvas.setFocusTraversable(true); //Damit die KeyInputs registriert wereden
        StackPane root = new StackPane();
        root.getChildren().addAll(new Canvas(), canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT);
        primaryStage.setResizable(false);//Damit man die Grösse des Fensters nicht verändern kann.
        primaryStage.setTitle("Last Standing Ding"); //Titel setzen
        primaryStage.setScene(scene);
        primaryStage.show();


        root.getChildren().add(line_no, new_line);
        root.setStyle("-fx-background-color: BLACK;"); //Hintergrundfarbe setzen

		createPlayer();
        timer.start(); //timer starten


		//System.out.println(numberOfPlayer);



		for(int i = 0; i < numberOfPlayer; i++){
			new Thread(players.get(i)).run();
		}

      /*    canvas.setOnKeyReleased(event -> System.out.println(event.getCharacter()+ "Gtueb Tag2222"));
      canvas.setOnMousePressed((event) -> setFromPos(event));*/
    }








    private void update(GraphicsContext gc){ //Bei jedem Timer Tick wird diese Methode ausgeführt

		//drawLine(gc); //Linie zeichnen bzw. updaten.
		for (int i = 0; i < numberOfPlayer; i++) {
			new Thread(players.get(i)).run();
		}

		for (int i = 0; i < numberOfPlayer; i++) {
			if (players.get(i).getNextLine()){
				timer.stop();
			}

		}

		try {
			Thread.sleep(8); //geschwindigkeit der Linie regulieren
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

		int red = random.nextInt(255);
		int green = random.nextInt(255);
		int blue = random.nextInt(255);

		return Color.rgb(red, green, blue);
	}

	/*

	WritableImage writableImage = new_line.snapshot(null, null);
	//WritableImage writableImage = new WritableImage((int) new_line.getWidth(), (int) new_line.getHeight());
	// System.out.print(writableImage.getPixelReader().getColor((int) to_x, (int)to_y));
	Color x = writableImage.getPixelReader().getColor((int) to_x, (int)to_y);
//  System.out.println(x);

//  System.out.println(Integer.toHexString(bi.getRGB(50, 550)));
        if (600 < to_x || 0 > to_x || 600 < to_y || 0 > to_y || x.equals("0x0000ffff")){
		timer.stop();
	}
            */

//  System.out.println(x.getRed() + " " +x.getGreen() + " " + x.getBlue());





	public static int getWIDTH() {
		return WIDTH;
	}

	public static int getHEIGHT() {
		return HEIGHT;
	}

	public static Canvas getCanvas() {
		return canvas;
	}

	public static void setCanvas(Canvas canvas) {
		GUI.canvas = canvas;
	}

	public GraphicsContext getGc() {
		return gc;
	}

	public void setGc(GraphicsContext gc) {
		this.gc = gc;
	}


}












