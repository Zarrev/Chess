package barzs14_project;

public final class King extends Figure {
	
	public King(boolean color, String name) {
		super(color, name);
	}
	//TODO
	//Sakkba ne tudjon bele lepni
	
	public boolean check(Figure[][] f){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(f[i][j] != null){
					if(f[i][j] instanceof Pawn){
						if(((Pawn)f[i][j]).hit(this.getX(),this.getY(),this)){
							return false;
						}
					}
					if(f[i][j].step(this.getX(), this.getY(), f)){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	public boolean checkScan(int row, int col, Figure[][] f){
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(f[i][j] != null){
					if(f[i][j] instanceof Pawn){
						if(((Pawn)f[i][j]).hit(row,col,this)){
							return false;
						}
					}
					if(f[i][j].step(row, col, f)){
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		
		checkScan(row,col,f);
		
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
