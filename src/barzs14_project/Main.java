package barzs14_project;

import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	private static String readOp; 
	private static boolean running = true;
	
	public static void main(String[] args) {
		Game b = new Game();
		while(running){
			System.out.println("Press a Number and Enter what woud you like to do! \n"
								+ "0\t-\t Draw board! \n"
								+ "1\t-\t Step with a figure. \n"
								+ "2\t-\t Write out to consol where stand the figures. \n"
								+ "3\t-\t Is this stand check? \n"
								+ "4\t-\t Write out all of possible step! \n"
								+ "5\t-\t Restart game! \n"
								+ "6\t-\t Exit.");
			readOp = sc.next();
			switch(readOp){
				case("0"):
					System.out.println(b);
					break;
				case("1"):
					b.play();
					break;
				case("2"):
					b.figurePos();
					break;
				case("3"):
					b.checkOnTheTable();
					break;
				case("4"):
					b.toStringPS();
					break;
				case("5"):
					b.reset();
					break;
				case("6"):
					System.out.println("The game has ended.");
					sc.close();
					b.closeStream();
					running = false;
					break;
				default:
					break;
			}
		}
	}

}
