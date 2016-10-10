package barzs14_project;

public final class Pawn extends Figure {
	
	public Pawn(boolean color, String name) {
		super(color, name);
	}	
	
	@Override
	public boolean step(int row, int col, Figure f) {
		if(color)
	    {
			if(x-row == 1 && f == null && y == col) {
				return true;
			}
			else if (f != null){
				if (x-row == 1 && f.isColor() != color && (y-col == 1 || y-col == - 1)){
					return true;
				}
			}
	    }
	    else
	    {
	    	if (x-row == - 1 &&  f == null && y == col) {
	    		return true;
	    	}
	    	else if (f != null){
	    		if (x-row == - 1 &&  f.isColor() != color && (y-col == 1 || y-col == - 1)){
	    			return true;
	    		}
	    	}
	    }
		return false;
	}
}
