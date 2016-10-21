package barzs14_project;

public final class King extends Figure {
	
	public King(boolean color, String name) {
		super(color, name);
	}
	//TODO
	//Sakkba ne tudjon bele lepni

	@Override
	public boolean step(int row, int col, Figure[][] f) {
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
