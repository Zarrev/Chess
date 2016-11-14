package barzs14_project;

import java.util.Scanner;
import java.util.Vector;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

public class Game extends Thread{//implements Runnable {

	private Figure[][] table;
	private boolean whichPlayer;
	//private Scanner sc;
	private Figure choosen;
	private int row, col;
	private boolean check;
	public boolean youCanGetData = false;
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
	private boolean checkMate, checkStalemate;
	private King WK;
	private King BK;
	private String endStr = new String("The game was interrupted.");
	
	private void makeTable(){
		this.WK = new King(true,"White_King");
		this.BK = new King(false,"Black_King");
		this.table = new Figure[][]{
			{new Rook(false,"Black_Rook_1"), new Knight(false,"Black_Knight_1"), new Bishop(false,"Black_Bishop_1"), BK, new Queen(false,"Black_Queen"), new Bishop(false,"Black_Bishop_2"), new Knight(false,"Black_Knight_2"), new Rook(false,"Black_Rook_2")},
			{new Pawn(false,"Black_Pawn_1"), new Pawn(false,"Black_Pawn_2"), new Pawn(false,"Black_Pawn_3"), new Pawn(false,"Black_Pawn_4"), new Pawn(false,"Black_Pawn_5"), new Pawn(false,"Black_Pawn_6"), new Pawn(false,"Black_Pawn_7"), new Pawn(false,"Black_Pawn_8")}, 
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(true,"White_Pawn_1"), new Pawn(true,"White_Pawn_2"), new Pawn(true,"White_Pawn_3"), new Pawn(true,"White_Pawn_4"), new Pawn(true,"White_Pawn_5"), new Pawn(true,"White_Pawn_6"), new Pawn(true,"White_Pawn_7"), new Pawn(true,"White_Pawn_8")},
			{new Rook(true,"White_Rook_1"), new Knight(true,"White_Knight_1"), new Bishop(true,"White_Bishop_1"), WK, new Queen(true,"White_Queen"), new Bishop(true,"White_Bishop_2"), new Knight(true,"White_Knight_2"), new Rook(true,"White_Rook_2")}
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
		check = false;
		checkMate = false;
		checkStalemate = false;
	}
	
	public Game() {
		System.out.println("The game has begun!");
		this.makeTable();
		//this.sc = new Scanner(System.in);

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
	
	public Figure[][] getTable() {
		return table;
	}

	private void readRowCol(){
		System.out.println("Give a row coord: ");
		while(!youCanGetData){
			if(youCanGetData){
				break;
			}
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		//row = sc.nextInt();
		System.out.println(row);
		System.out.println("Give a col coord: ");
		//col = sc.nextInt();
		System.out.println(col);
		while(row > 7 || row < 0){
			System.out.println("Wrong row coord! Try Again!");
			//row = sc.nextInt();
		}
		while(col > 7 || col < 0){
			System.out.println("Wrong col coord! Try Again!");
			//col = sc.nextInt();
		}
		youCanGetData = false;
	}
	
	private void choose(){
		
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
		System.out.println("The choosen figure is " + this.choosen.getName());
		
	}

	public void play(){//TODO WHERECANSTEP
		if (this.whichPlayer){
			System.out.println("White Player turn!");
		}
		else{
			System.out.println("Black Player turn!");
		}
		System.out.println(this);
		this.choose();
		whereCanStep();
		this.step();
		System.out.println(this);
		
	}
	
	public String getEndStr(){
		return this.endStr;
	}
	
	public boolean isItEnd(){
		if (checkMate){
			if(WK.getCheck()){
				endStr = "The winner is the Black Player!";
				return true;
			}
			
			if(BK.getCheck()){
				endStr = "The winner is the White Player!";
				return true;
			}
			
		}
		else if(checkStalemate){
			endStr = "Draw!";
			return true;
		}
		
		return false;
	}
	
	private void step(){
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
					System.out.println("Your King is in check! Wrong step! You can choose a figure again!");
					choosen = table[row][col];
					table[oldX][oldY] = choosen;
					table[oldX][oldY].setXY(oldX, oldY);
					table[row][col] = null;
					if (table[oldX][oldY] instanceof Pawn) {
						((Pawn) table[oldX][oldY]).resetFirstStep();;
					}
					correct = false;
				}
				else{
					this.check = false;
				}
				
			}
			else{
				System.out.println("Wrong step! Try again!");
			}
			if(!correct){
				this.choose();
			}
		}
		choosen = null;
		whichPlayer = !whichPlayer;
		this.isItCheck();
		endTest();
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
		Figure tmp = table[k][l];
		table[k][l] = table[i][j];
		table[i][j] = null;
		table[k][l].setXY(k, l);
		isItCheck();
		table[i][j] = table[k][l];
		table[k][l] = tmp;
		table[i][j].setXY(i, j);
		if (table[i][j] instanceof Pawn) {
			if((((Pawn) table[i][j]).getX() == 1 && !((Pawn) table[i][j]).isColor()) ||  (((Pawn) table[i][j]).getX() == 6 && ((Pawn) table[i][j]).isColor()))
				((Pawn) table[i][j]).resetFirstStep();
		}
	}
	
	public Figure getChossen() {
		if (choosen instanceof Pawn) {
			return (Pawn)choosen;			
		}
		if (choosen instanceof King) {
			return (King)choosen;
			
		}
		if (choosen instanceof Queen) {
			return (Queen)choosen;
			
		}
		if (choosen instanceof Rook) {
			return (Rook)choosen;
			
		}
		if (choosen instanceof Bishop) {
			return (Bishop)choosen;
			
		}
		if (choosen instanceof Knight) {
			return (Knight)choosen;
			
		}
		return null;
	}
	
	private void whereCanStep(){
		if(!this.possibleSteps.isEmpty()){
			this.possibleSteps.clear();
		}
		
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j] != null && !table[i][j].getChossenPS().isEmpty())
					table[i][j].clearPS();
			}
		}
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j] != null && table[i][j].isColor() == whichPlayer){
					for(int k = 0; k < 8; k++){
						for(int l = 0; l < 8; l++){
							if((table[k][l] == null || table[i][j].isColor() != table[k][l].isColor()) && table[i][j].step(k, l, table))
							{	
								
								virtualStep(i, j, k, l);
								if(!check){
									possibleSteps.add(new PS(k,l,table[i][j].getName()));
									table[i][j].add(k,l);
									System.out.println(k+","+l+","+table[i][j].getName());
								}
							}
						}
					}
				}
			}
		}
	}

	private void isItCheck(){
		if(whichPlayer){
			this.WK.checkScan(WK.getX(), WK.getY(), table);
			if(WK.getCheck())
				this.check = true;
			else
				this.check = false;
		}
		else{		
			this.BK.checkScan(BK.getX(), BK.getY(), table);
			if(BK.getCheck())
				this.check = true;
			else
				this.check = false;
		}
	}
	
	public void checkOnTheTable(){
		this.isItCheck();
		if(WK.getCheck()){
			System.out.println("White King is in check!");
		}
	
		if(BK.getCheck()){
			System.out.println("Black King is in check!");
		}
		
		if(!this.check){
			System.out.println("Nobody is in check!");
		}
	}
	
	public void toStringPS(){

				
		this.whereCanStep();
		
		for (PS ps : possibleSteps) {
			System.out.println(ps);
		}
	}

	public void endTest(){
		whereCanStep();
		if(possibleSteps.isEmpty()){
			if(WK.getCheck()){
				checkMate = true;
				return;
			}
			else if (!WK.getCheck()){
				checkStalemate = true;
				return;
			}
			
			if(BK.getCheck()){
				checkMate = true;
				return;
			}
			else if (!BK.getCheck()){
				checkStalemate = true;
				return;
			}
		}
	}
	public void setRC(Integer row, Integer col){
		this.row = row;
		this.col = col;
	}

	public boolean canYouGetData() {
		return youCanGetData;
	}

	public void setYouCanGetData(boolean e) {
		youCanGetData = e;
	}
	//@Override
	/*public void run() {
		while(!this.isItEnd())
			this.play();
		System.out.println("The game has ended.");
		System.out.println(this.getEndStr());
		
	}
	*/

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(!this.isItEnd()){
			this.play();
			checkOnTheTable();
		}
	}
}