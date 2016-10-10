package barzs14_project;

import java.util.Scanner;

public class Game {

	private Figure[][] table;
	private boolean whichPlayer;
	private Scanner sc;
	private Figure choosen;
	private int row, col;
	
	public Game() {
		System.out.println("The game has begun!");
		table = new Figure[][]{
			{new Rook(false,"Black_Rook_1"), new Knight(false,"Black_Knight_1"), new Bishop(false,"Black_Bishop_1"), new King(false,"Black_King"), new Queen(false,"Black_Queen"), new Bishop(false,"Black_Bishop_2"), new Knight(false,"Black_Knight_2"), new Rook(false,"Black_Rook_2")},
			{new Pawn(false,"Black_Pawn_1"), new Pawn(false,"Black_Pawn_2"), new Pawn(false,"Black_Pawn_3"), new Pawn(false,"Black_Pawn_4"), new Pawn(false,"Black_Pawn_5"), new Pawn(false,"Black_Pawn_6"), new Pawn(false,"Black_Pawn_7"), new Pawn(false,"Black_Pawn_8")}, 
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(true,"White_Pawn_1"), new Pawn(true,"White_Pawn_2"), new Pawn(true,"White_Pawn_3"), new Pawn(true,"White_Pawn_4"), new Pawn(true,"White_Pawn_5"), new Pawn(true,"White_Pawn_6"), new Pawn(true,"White_Pawn_7"), new Pawn(true,"White_Pawn_8")},
			{new Rook(true,"White_Rook_1"), new Knight(true,"White_Knight_1"), new Bishop(true,"White_Bishop_1"), new King(true,"White_King"), new Queen(true,"White_Queen"), new Bishop(true,"White_Bishop_2"), new Knight(true,"White_Knight_2"), new Rook(true,"White_Rook_2")}
		};
		whichPlayer = true;
		sc = new Scanner(System.in);
		choosen = null;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j] != null){
					table[i][j].setXY(i,j);
				}
			}
		}
	}

	public void reset(){
		System.out.println("New Game has begun!");
		table = new Figure[][]{
			{new Rook(false,"Black_Rook_1"), new Knight(false,"Black_Knight_1"), new Bishop(false,"Black_Bishop_1"), new King(false,"Black_King"), new Queen(false,"Black_Queen"), new Bishop(false,"Black_Bishop_2"), new Knight(false,"Black_Knight_2"), new Rook(false,"Black_Rook_2")},
			{new Pawn(false,"Black_Pawn_1"), new Pawn(false,"Black_Pawn_2"), new Pawn(false,"Black_Pawn_3"), new Pawn(false,"Black_Pawn_4"), new Pawn(false,"Black_Pawn_5"), new Pawn(false,"Black_Pawn_6"), new Pawn(false,"Black_Pawn_7"), new Pawn(false,"Black_Pawn_8")}, 
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(true,"White_Pawn_1"), new Pawn(true,"White_Pawn_2"), new Pawn(true,"White_Pawn_3"), new Pawn(true,"White_Pawn_4"), new Pawn(true,"White_Pawn_5"), new Pawn(true,"White_Pawn_6"), new Pawn(true,"White_Pawn_7"), new Pawn(true,"White_Pawn_8")},
			{new Rook(true,"White_Rook_1"), new Knight(true,"White_Knight_1"), new Bishop(true,"White_Bishop_1"), new King(true,"White_King"), new Queen(true,"White_Queen"), new Bishop(true,"White_Bishop_2"), new Knight(true,"White_Knight_2"), new Rook(true,"White_Rook_2")}
		};
		whichPlayer = true;
		choosen = null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		sb.append("\n------------------------------------------------------------------------------------------------------------------------\n");
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if (j == 0)
					sb.append("|");
				if (table[i][j] != null){
					sb.append(table[i][j].getName());
					if (table[i][j].getName().length() != 14){
						int plusWs = 14 - table[i][j].getName().length();
						for(int k = 0; k < plusWs; k++){
							sb.append(" ");
						}
					}
					sb.append("|");
				}
				else{
					sb.append("              |");
				}
			}
			sb.append("\n------------------------------------------------------------------------------------------------------------------------\n");
		}
		
		return sb.toString();
	}
	
	private void readRowCol(){
		row = sc.nextInt();
		col = sc.nextInt();
		if(row == 100 && col == 100){
			System.out.println("Game Over!");
			closeStream();
			System.exit(0);
		}
		while(row > 7 || row < 0){
			row = sc.nextInt();
		}
		while(col > 7 || col < 0){
			col = sc.nextInt();
		}
	}
	
	public void choose(){
		System.out.println("Which figure is your choosen?");
		boolean correct = false;
		
		while(!correct){
			readRowCol();
			if (table[row][col] != null && whichPlayer == table[row][col].isColor()){
				correct = true;
				choosen = table[row][col];
			}
			else{
				System.out.println("Wrong choose! Try again!");
			}
		}
	}
	
	public void step(){
		if(choosen.getName().equals("END")){
			return;
		}
		System.out.println("Where would you like to step?");
		boolean correct = false;
		
		while(!correct){
			readRowCol();
			if ((table[row][col] == null || whichPlayer != table[row][col].isColor()) && choosen.step(row, col, table)){
				correct = true;
				table[row][col] = choosen;
				table[choosen.getX()][choosen.getY()] = null;
				table[row][col].setXY(row,col);
				choosen = null;
			}
			else{
				System.out.println("Wrong step! Try again!");
			}
		}
		whichPlayer = !whichPlayer;
	}

	public boolean isPlayer() {
		return whichPlayer;
	}

	public Figure getChoosen() {
		return choosen;
	}
	
	private void closeStream(){
		sc.close();
	}
	
}
