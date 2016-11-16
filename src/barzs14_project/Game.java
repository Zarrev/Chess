package barzs14_project;

import java.util.ArrayList;

public class Game extends Thread{

	private Figure[][] table;
	private boolean whichPlayer;
	private Figure choosen;
	private int row, col;
	private boolean check;
	public boolean youCanGetData = false;
	public class PS{
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
		public String getName() {
			return name;
		}
		
	}
	private ArrayList<PS> possibleSteps = new ArrayList<>();
	private boolean checkMate, checkStalemate;
	private King WK;
	private King BK;
	private String endStr = new String("The game was interrupted.");
	
	public ArrayList<PS> getPossibleSteps() {
		return possibleSteps;
	}

	private void makeTable(){
		this.WK = new King(true,"White_King");
		this.BK = new King(false,"Black_King");
		this.table = new Figure[][]{
			{new Rook(false,"Black_Rook_1"), new Knight(false,"Black_Knight_1"), new Bishop(false,"Black_Bishop_1"), new Queen(false,"Black_Queen"), BK, new Bishop(false,"Black_Bishop_2"), new Knight(false,"Black_Knight_2"), new Rook(false,"Black_Rook_2")},
			{new Pawn(false,"Black_Pawn_1"), new Pawn(false,"Black_Pawn_2"), new Pawn(false,"Black_Pawn_3"), new Pawn(false,"Black_Pawn_4"), new Pawn(false,"Black_Pawn_5"), new Pawn(false,"Black_Pawn_6"), new Pawn(false,"Black_Pawn_7"), new Pawn(false,"Black_Pawn_8")}, 
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(true,"White_Pawn_1"), new Pawn(true,"White_Pawn_2"), new Pawn(true,"White_Pawn_3"), new Pawn(true,"White_Pawn_4"), new Pawn(true,"White_Pawn_5"), new Pawn(true,"White_Pawn_6"), new Pawn(true,"White_Pawn_7"), new Pawn(true,"White_Pawn_8")},
			{new Rook(true,"White_Rook_1"), new Knight(true,"White_Knight_1"), new Bishop(true,"White_Bishop_1"), new Queen(true,"White_Queen"),WK,  new Bishop(true,"White_Bishop_2"), new Knight(true,"White_Knight_2"), new Rook(true,"White_Rook_2")}
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
		//System.out.println("The game has begun!");
		this.makeTable();
		//this.sc = new Scanner(System.in);

	}
	
	public void reset(){
		//System.out.println("New Game has begun!");
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
		//System.out.println("Give a row coord: ");
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
		//System.out.println(row);
		//System.out.println("Give a col coord: ");
		//System.out.println(col);
		//while(row > 7 || row < 0){
			//System.out.println("Wrong row coord! Try Again!");
		//}
		//while(col > 7 || col < 0){
			//System.out.println("Wrong col coord! Try Again!");
		//}
		youCanGetData = false;
	}
	
	private void choose(){
		this.isItCheck();
		//System.out.println("Which figure is your choosen?");
		boolean correct = false;
		while(!correct){
			readRowCol();
			if (table[row][col] != null && whichPlayer == table[row][col].isColor()){
				correct = true;
				choosen = table[row][col];
			}
			/*else{
				System.out.println("Wrong choose! Try again!");
			}*/
		}
		//System.out.println("The choosen figure is " + this.choosen.getName());
	}

	public void play(){//TODO Play func
		/*if (this.whichPlayer){
			System.out.println("White Player turn!");
		}
		else{
			System.out.println("Black Player turn!");
		}
		System.out.println(this);*/
		this.choose();
		whereCanStep();
		this.step();
		endTest();
		//System.out.println(this);
		
		
	}
	
	public String getEndStr(){
		return this.endStr;
	}
	
	public boolean isItEnd(){
		endTest();
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
		//System.out.println("Where would you like to step?");
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
					//System.out.println("Your King is in check! Wrong step! You can choose a figure again!");
					choosen = table[row][col];
					table[oldX][oldY] = choosen;
					table[oldX][oldY].setXY(oldX, oldY);
					table[row][col] = null;
					correct = false;
				}
				else{
					this.check = false;
				}
				
			}
			else{
				if(table[row][col] != null && table[row][col].isColor() == whichPlayer)
					this.choosen = table[row][col];
				//System.out.println("Wrong step! Try again!");
			}
		}
		choosen = null;
		whichPlayer = !whichPlayer;
		this.isItCheck();
	}

	public void figurePos(){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j]!=null){
					//System.out.println(table[i][j].getName() + " : (" + table[i][j].getX() + " , " + table[i][j].getY() + ")");
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
	
	public String checkOnTheTable(){
		this.isItCheck();
		if(WK.getCheck()){
			return ("White King is in check!");
		}
	
		if(BK.getCheck()){
			return ("Black King is in check!");
		}
			return ("Nobody is in check!");
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
			
			if(BK.getCheck()){
				checkMate = true;
				return;
			}
			else if (!BK.getCheck() && !WK.getCheck()){
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
	
	public boolean isCheck() {
		return check;
	}

	public boolean isCheckMate() {
		return checkMate;
	}

	public boolean isCheckStalemate() {
		return checkStalemate;
	}
	

	public King getWK() {
		return WK;
	}

	public King getBK() {
		return BK;
	}

	@Override
	public void run() {
		while(!this.isItEnd()){
			this.play();
		}
		//System.out.println(endStr);
	}
}