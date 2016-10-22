package barzs14_project;
import java.lang.Math;

public final class King extends Figure {
	
	public King(boolean color, String name) {
		super(color, name);
	}
	
	public boolean checkScan(int row, int col, Figure[][] f){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(f[i][j] != null && f[i][j].isColor() != this.isColor()){
					if(f[i][j] instanceof Pawn && ((Pawn)f[i][j]).hit(row,col,this)){
						return false;
					}
					else if(f[i][j] instanceof King && (((Math.abs(i-row) + Math.abs(j-col)) <= 1 )
							|| (Math.abs(i-row) == 1 && Math.abs(j-col) == 1))){
						return false;
					}
					else if(!(f[i][j] instanceof King) &&  !(f[i][j] instanceof Pawn) && f[i][j].step(row, col, f)){
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		
		if(!checkScan(row,col,f)){
			return false;
		}
		
		if(row > 7 || col > 7 || row < 0 || col < 0)
			return false;
		
		if(f[row][col] != null){
			if(f[row][col].isColor() == this.isColor()){
				return false;
			}
		}
		
		int diffX = row-this.getX();
		int diffY = col-this.getY();
		if((diffX == 1) && (diffY == 1 || diffY == 0 || diffY == -1)){
			return true;
		}
		else if((diffX == 0) && (diffY == 1 || diffY == -1)){
			return true;
		}
		else if((diffX == -1) && (diffY == 1 || diffY == 0 || diffY == -1)){
			return true;
		}
		
		return false;
	}



}
