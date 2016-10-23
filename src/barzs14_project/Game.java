package barzs14_project;

import java.util.Scanner;
import java.util.Vector;

public class Game {

	private Figure[][] table;
	private boolean whichPlayer = true;
	private Scanner sc;
	private Figure choosen;
	private int row, col;
	private boolean check = false;
	private class PS{
		public int x;
		public int y;
		public String name;
		public PS(int x, int y, String name) {
			super();
			this.x = x;
			this.y = y;
			this.name = name;
		}
		@Override
		public String toString() {
			return this.name + "\t:\t" + "(" + this.x + ", " + this.y + ")";
		}
		
	}
	private Vector<PS> possibleSteps = new Vector<>();
	
	private King WK;
	private King BK;
	
	private void makeTable(){
		this.WK = new King(true,"White_King");
		this.BK = new King(false,"Black_King");
		this.table = new Figure[][]{
			{new Rook(false,"Black_Rook_1"), new Knight(false,"Black_Knight_1"), new Bishop(false,"Black_Bishop_1"), null, null, new Bishop(false,"Black_Bishop_2"), new Knight(false,"Black_Knight_2"), new Rook(false,"Black_Rook_2")},
			{new Pawn(false,"Black_Pawn_1"), new Pawn(false,"Black_Pawn_2"), new Pawn(false,"Black_Pawn_3"), new Pawn(false,"Black_Pawn_4"), new Pawn(false,"Black_Pawn_5"), new Pawn(false,"Black_Pawn_6"), new Pawn(false,"Black_Pawn_7"), new Pawn(false,"Black_Pawn_8")}, 
			{null, null, null, null, new Queen(false,"Black_Queen"), BK, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, WK, new Bishop(true,"White_Bishop_1"), null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(true,"White_Pawn_1"), new Pawn(true,"White_Pawn_2"), new Pawn(true,"White_Pawn_3"), new Pawn(true,"White_Pawn_4"), new Pawn(true,"White_Pawn_5"), new Pawn(true,"White_Pawn_6"), new Pawn(true,"White_Pawn_7"), new Pawn(true,"White_Pawn_8")},
			{new Rook(true,"White_Rook_1"), new Knight(true,"White_Knight_1"), null, null, new Queen(true,"White_Queen"), new Bishop(true,"White_Bishop_2"), new Knight(true,"White_Knight_2"), new Rook(true,"White_Rook_2")}
		};
		this.whichPlayer = true;
		this.choosen = null;
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(this.table[i][j] != null){
					this.table[i][j].setXY(i,j);
				}
				
			}
		}
	}
	
	public Game() {
		System.out.println("The game has begun!");
		this.makeTable();
		this.sc = new Scanner(System.in);
		isItCheck();

	}
	
	public void reset(){
		System.out.println("New Game has begun!");
		this.makeTable();
		check = false;
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
		System.out.println("Give a row coord: ");
		row = sc.nextInt();
		System.out.println("Give a col coord: ");
		col = sc.nextInt();
		while(row > 7 || row < 0){
			System.out.println("Wrong row coord! Try Again!");
			row = sc.nextInt();
		}
		while(col > 7 || col < 0){
			System.out.println("Wrong col coord! Try Again!");
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
		System.out.println("The choosen figure is " + this.getChoosen().getName());
	}

	public void play(){
		if (this.isPlayer()){
			System.out.println("White Player turn!");
		}
		else{
			System.out.println("Black Player turn!");
		}
		System.out.println(this);
		this.choose();
		this.step();
		this.isItCheck();
		while(this.check && choosen != null){
			this.choose();
			this.step();
			this.isItCheck();
		}
		System.out.println(this);
	}
	
	public void step(){
		this.isItCheck();
		System.out.println("Where would you like to step?");
		boolean correct = false;
		
		while(!correct){
			readRowCol();
			if ((table[row][col] == null || whichPlayer != table[row][col].isColor())
					&& choosen.step(row, col, table)){
				correct = true;
				int oldX = choosen.getX();
				int oldY = choosen.getY();
				
				table[row][col] = choosen;
				table[choosen.getX()][choosen.getY()] = null;
				table[row][col].setXY(row,col);
				
				this.isItCheck();
				if(this.check){
					System.out.println("Your King is still check! Wrong step! You can choose a figure again!");
					choosen = table[row][col];
					table[oldX][oldY] = choosen;
					table[oldX][oldY].setXY(oldX, oldY);
					table[row][col] = null;
					correct = false;
				}
				else{
					this.check = false;
				}
				choosen = null;
			}
			else{
				System.out.println("Wrong step! Try again!");
			}
			if(!correct){
				this.choose();
			}
		}
		if(!this.check)
			whichPlayer = !whichPlayer;
		this.isItCheck();
	}

	public boolean isPlayer() {
		return whichPlayer;
	}

	public Figure getChoosen() {
		return choosen;
	}
	
	public void closeStream(){
		sc.close();
	}
	
	public void figurePos(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j]!=null){
					System.out.println(table[i][j].getName() + " : (" + table[i][j].getX() + " , " + table[i][j].getY() + ")");
				}
			}
		}
	}

	private void virtualStep(int i, int j, int k, int l){
		Figure tmp = table[i][j];
		table[i][j] = table[k][l];
		table[k][l] = tmp;
		table[k][l].setXY(k, l);
	}
	
	private void whereCanStep(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j] != null){
					for(int k = 0; k < 8; k++){
						for(int l = 0; l < 8; l++){
							if(table[i][j].isColor() == whichPlayer && (table[k][l] == null || table[i][j].isColor() != table[k][l].isColor()) && table[i][j].step(k, l, table))
							{	
								boolean tmpcheck = false;
								virtualStep(i, j, k, l);
								if(table[k][l].isColor()){
									if(!((King)this.WK).checkScan(WK.getX(), WK.getY(), table)){
										tmpcheck = true;
									}
									else{
										tmpcheck = false;
									}
								}
								else{
									if (!((King)this.BK).checkScan(WK.getX(), WK.getY(), table)){
										tmpcheck = true;
									}
									else{
										tmpcheck = false;
									}
								}
								virtualStep(k, l, i, j);
								if(!tmpcheck){
									possibleSteps.add(new PS(k,l,table[i][j].getName()));
								}
								//System.out.println(table[i][j].getName() + "\t:\t" + "(" + k + ", " + l + ")");
							}
						}
					}
				}
			}
		}
	}

	private void isItCheck(){
		if(whichPlayer){
			if(!((King)this.WK).checkScan(WK.getX(), WK.getY(), table)){
				this.check = true;
			}
			else{
				this.check = false;
			}
		}
		else{
			if (!((King)this.BK).checkScan(WK.getX(), WK.getY(), table)){
				this.check = true;
			}
			else{
				this.check = false;
			}
		}
	}
	
	public void checkOnTheTable(){
		this.isItCheck();
		if(this.whichPlayer){
			if(this.check){
				System.out.println("White King is in check!");
			}
		}
		else{
			if(this.check){
				System.out.println("Black King is in check!");
			}
		}
		
		if(!this.check){
			System.out.println("Nobody is in check!");
		}
	}

	public void toStringPS(){
		if(!this.possibleSteps.isEmpty()){
			this.possibleSteps.clear();
		}
		this.whereCanStep();
		for (PS ps : possibleSteps) {
			System.out.println(ps);
		}
	}
}