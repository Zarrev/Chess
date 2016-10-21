package barzs14_project;

import java.util.Scanner;
import java.util.Vector;

public class Game {

	private Figure[][] table;
	private boolean whichPlayer;
	private Scanner sc;
	private Figure choosen;
	private int row, col;
	
	private class possbileSteps{
		public int row;
		public int col;
		public String name;
		
		public possbileSteps(int row, int col, String name){
			this.row = row;
			this.col = col;
			this.name = name;
		}
	}
	
	Vector<possbileSteps> posStep = new Vector<>();
	
	public Game() {
		System.out.println("The game has begun!");
		table = new Figure[][]{
			{new Rook(false,"Black_Rook_1"), new Knight(false,"Black_Knight_1"), new Bishop(false,"Black_Bishop_1"), new King(false,"Black_King"), new Queen(false,"Black_Queen"), new Bishop(false,"Black_Bishop_2"), new Knight(false,"Black_Knight_2"), new Rook(false,"Black_Rook_2")},
			{new Pawn(false,"Black_Pawn_1"), new Pawn(false,"Black_Pawn_2"), new Pawn(false,"Black_Pawn_3"), new Pawn(false,"Black_Pawn_4"), new Pawn(false,"Black_Pawn_5"), new Pawn(false,"Black_Pawn_6"), new Pawn(false,"Black_Pawn_7"), new Pawn(false,"Black_Pawn_8")}, 
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, new King(true,"White_King"), null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{new Pawn(true,"White_Pawn_1"), new Pawn(true,"White_Pawn_2"), new Pawn(true,"White_Pawn_3"), new Pawn(true,"White_Pawn_4"), new Pawn(true,"White_Pawn_5"), new Pawn(true,"White_Pawn_6"), new Pawn(true,"White_Pawn_7"), new Pawn(true,"White_Pawn_8")},
			{new Rook(true,"White_Rook_1"), new Knight(true,"White_Knight_1"), new Bishop(true,"White_Bishop_1"), null, new Queen(true,"White_Queen"), new Bishop(true,"White_Bishop_2"), new Knight(true,"White_Knight_2"), new Rook(true,"White_Rook_2")}
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
	}
	//TODO
	// a kiraly tuti leutheto :'D
	public boolean checkStep(int x, int y, int row, int col){
		if (this.table[x][y] instanceof King) {
			for (possbileSteps e : this.posStep) {
				if(!this.table[x][y].getName().equals(e.name)){
					// a gond abbol adodik, hogy a paraszt 1-et vagy 2-t lephet elore ami kicsit megneheziti a vizsgalatot (az elso ket if nelkul jobb eredmenyt de nem jot kapok)
					// a leheteseges lepesbol siman kilehet szamolni hogy a paraszt hol al ami segitsegevel megtudjuk mondani hova nem(!) lephet a királyunk
					//tehat pl a table[e.row-1][e.col+1] az egyenlo lehet a paraszttal es igy mar vizsgalhato hogy hova utlephetne (sajat kif)
					//asszem ezzel megoldottam a problemat
					//ezzel vizsgalhato, hogy mikor van sakkban a kiraly, mert ha valamelyik babu ra lephet a helyere az sakkot jelent
					//ezzel vizsgalhato a patt is, mert a kiraly helyere igy nem lephet senki, viszont o sem lephet sehova es o van egyedul az asztalon a sajat szinebol
					//ezzel vizsgalhato a sakkmatt is, mert ha sakkba van a kiraly es nincs lehetosege lepni akkor vege :)
					if(e.row+1 == row && (e.col+1 == col || e.col-1 == col) && e.name.contains("Pawn") &&  e.name.contains("Black") == this.table[x][y].getName().contains("White")){
						if(this.table[row-2][col-1] instanceof Pawn){
							if(((Pawn)table[row-2][col-1]).hit(x,y,table))
								return false;
						}
						if(this.table[row-2][col-+1] instanceof Pawn){
							if(((Pawn)table[row-2][col+1]).hit(x,y,table))
								return false;
						}
					}
					if(e.row-1 == row && (e.col+1 == col || e.col-1 == col) && e.name.contains("Pawn") && e.name.contains("White") == this.table[x][y].getName().contains("Black")){
						if(this.table[row+2][col-1] instanceof Pawn){
							if(((Pawn)table[row-2][col-1]).hit(x,y,table))
								return false;
						}
						if(this.table[row+2][col-+1] instanceof Pawn){
							if(((Pawn)table[row-2][col+1]).hit(x,y,table))
								return false;
						}
					}
					if(e.row == row && e.col == col && e.name.contains("Black") != this.table[x][y].getName().contains("Black") 
							&& !e.name.contains("Pawn")){
						return false;
					}
				}
			}	
		}
		
		return true;
	}
	
	public void step(){
		if(choosen.getName().equals("END")){
			return;
		}
		System.out.println("Where would you like to step?");
		boolean correct = false;
		
		while(!correct){
			readRowCol();
			if ((table[row][col] == null || whichPlayer != table[row][col].isColor()) && choosen.step(row, col, table) && checkStep(choosen.getX(),choosen.getY(),this.row,this.col)){
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
	 //TODO
	private void allPossibleStep(){
		if(!posStep.isEmpty())
			posStep.clear();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(table[i][j] != null){
					for(int k = 0; k < 8; k++){
						for(int l = 0; l < 8; l++){
							if((table[k][l] == null || table[i][j].isColor() != table[k][l].isColor()) && table[i][j].step(k, l, table) && checkStep(i,j,k,l)){
								posStep.add(new possbileSteps(k, l, table[i][j].getName()));
							}
						}
					}
				}
			}
		}
	}
	
	public void printPosStep(){
		allPossibleStep();
		for (possbileSteps e : posStep) {
			System.out.println(e.name + " : (" + e.row + " , " + e.col + ")");
		}
	}
}
