package barzs14_project;

public final class Pawn extends Figure {
	
	public Pawn(boolean color, String name) {
		super(color, name);
	}
	public boolean hit(int row, int col, Figure f){
		if (f != null && this.isColor()){
			if (this.getX()-row == 1 && f.isColor() != this.isColor() && (this.getY()-col == 1
					|| this.getY()-col == - 1)){
				return true;
			}
		}
		if (f != null && !this.isColor()){
    		if (this.getX()-row == - 1 &&  f.isColor() != this.isColor() &&
    				(this.getY()-col == 1 || this.getY()-col == - 1)){
    			return true;
    		}
    	}
		return false;
	}
	
	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(row > 7 || col > 7 || row < 0 || col < 0)
			return false;
		
		if(this.isColor())
	    {
			if(this.getX() == 6 && row+2 == this.getX() && col == this.getY() && f[row][col] == null){
				return true;
			}
			
			if(this.getX()-row == 1 && f[row][col] == null && this.getY() == col) {
				return true;
			}
			else{
				return this.hit(row,col,f[row][col]);
			}
	    }
	    else
	    {
	    	if(this.getX() == 1 && row-2 == this.getX() && col == this.getY() && f[row][col] == null){
				return true;
			}
	    	
	    	if (this.getX()-row == - 1 &&  f[row][col] == null && this.getY() == col) {
	    		return true;
	    	}
	    	else{
	    		return this.hit(row,col,f[row][col]);
	    	}
	    }
	}
}
