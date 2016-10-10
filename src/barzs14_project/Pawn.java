package barzs14_project;

public final class Pawn extends Figure {
	
	public Pawn(boolean color, String name) {
		super(color, name);
	}	
	
	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(this.isColor())
	    {
			if(this.getX()-row == 1 && f[row][col] == null && this.getY() == col) {
				return true;
			}
			else if (f[row][col] != null){
				if (this.getX()-row == 1 && f[row][col].isColor() != this.isColor() && (this.getY()-col == 1
						|| this.getY()-col == - 1)){
					return true;
				}
			}
	    }
	    else
	    {
	    	if (this.getX()-row == - 1 &&  f[row][col] == null && this.getY() == col) {
	    		return true;
	    	}
	    	else if (f[row][col] != null){
	    		if (this.getX()-row == - 1 &&  f[row][col].isColor() != this.isColor() &&
	    				(this.getY()-col == 1 || this.getY()-col == - 1)){
	    			return true;
	    		}
	    	}
	    }
		return false;
	}
}
