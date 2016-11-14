package barzs14_project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application{
	
	public static Game engine = new Game();
	private BoardGUI bg = new BoardGUI();

	public static int maxSize = 60;
	public static Font f = new Font(maxSize);
	/*
	private void ConsolTest(){
		Scanner sc = new Scanner(System.in);
		String readOp; 
		boolean running = true;
		boolean end = false;
		while(running){
			System.out.println("Press a Number and Enter what woud you like to do! \n"
								+ "0\t-\t Draw board! \n"
								+ "1\t-\t Step with a figure. \n"
								+ "2\t-\t Write out to consol where stand the figures. \n"
								+ "3\t-\t Is this stand check? \n"
								+ "4\t-\t Write out all of possible step! \n"
								+ "5\t-\t Restart game! \n"
								+ "6\t-\t Exit.");
			if(!end)
				readOp = sc.next();
			else{
				readOp = "6";
			}
			switch(readOp){
				case("0"):
					System.out.println(engine);
					break;
				case("1"):
					engine.play();
					end = engine.isItEnd();
					break;
				case("2"):
					engine.figurePos();
					break;
				case("3"):
					engine.checkOnTheTable();
					break;
				case("4"):
					engine.toStringPS();
					break;
				case("5"):
					engine.reset();
					break;
				case("6"):
					System.out.println("The game has ended.");
					System.out.println(engine.getEndStr());
					sc.close();
					engine.closeStream();
					running = false;
					break;
				default:
					break;
			}
		}
	}
	*/
	public static void main(String[] args) {
		 try {
			f = Font.loadFont(new FileInputStream(new File("KINGFONT.TTF")), maxSize);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Thread th = new Thread(engine);
		th.setDaemon(true);
		th.start();
		launch();

	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene sc = new Scene(bg.get());
		primaryStage.setScene(sc);
		primaryStage.setTitle("Chess");
		primaryStage.show();
	}

}
