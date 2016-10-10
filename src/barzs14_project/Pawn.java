package barzs14_project;

public final class Pawn extends Figure {
	
	private int firstStep;
	
	public Pawn(boolean color, String name) {
		super(color, name);
		firstStep = 0;
	}
	
	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(this.isColor())
	    {
			if(firstStep == 0 && row+2 == this.getX() && col == this.getY()){
				firstStep++;
				return true;
			}
			
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
	    	if(firstStep == 0 && row-2 == this.getX() && col == this.getY()){
				firstStep++;
				return true;
			}
	    	
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
