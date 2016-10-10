package barzs14_project;

public final class Pawn extends Figure {
	
	public Pawn(boolean color, String name) {
		super(color, name);
	}	
	
	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(color)
	    {
			if(x-row == 1 && f[row][col] == null && y == col) {
				return true;
			}
			else if (f[row][col] != null){
				if (x-row == 1 && f[row][col].isColor() != color && (y-col == 1 || y-col == - 1)){
					return true;
				}
			}
	    }
	    else
	    {
	    	if (x-row == - 1 &&  f[row][col] == null && y == col) {
	    		return true;
	    	}
	    	else if (f[row][col] != null){
	    		if (x-row == - 1 &&  f[row][col].isColor() != color && (y-col == 1 || y-col == - 1)){
	    			return true;
	    		}
	    	}
	    }
		return false;
	}
}
