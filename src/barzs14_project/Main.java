package barzs14_project;

public class Main {

	public static void main(String[] args) {
		Game b = new Game();
		while(true){
			if (b.isWhichPlayer()){
				System.out.println("White Player turn!");
			}
			else{
				System.out.println("Black Player turn!");
			}
			System.out.println(b);
			b.choose();
			System.out.println("The choosen pupet is " + b.getChoosen().getName());
			b.step();
		}
	}

}
