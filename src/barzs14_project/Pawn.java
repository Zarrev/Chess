package barzs14_project;

public final class Pawn extends Figure {
	
	private int firstStep;
	
	public Pawn(boolean color, String name) {
		super(color, name);
		firstStep = 0;
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
			if(firstStep == 0 && row+2 == this.getX() && col == this.getY() && f[row+1][col] == null){
				firstStep++;
				return true;
			}
			
			if(this.getX()-row == 1 && f[row][col] == null && this.getY() == col) {
				return true;
			}
			else{
				this.hit(row,col,f[row][col]);
			}
	    }
	    else
	    {
	    	if(firstStep == 0 && row-2 == this.getX() && col == this.getY() && f[row-1][col] == null){
				firstStep++;
				return true;
			}
	    	
	    	if (this.getX()-row == - 1 &&  f[row][col] == null && this.getY() == col) {
	    		return true;
	    	}
	    	else{
	    		this.hit(row,col,f[row][col]);
	    	}
	    }
		return false;
	}
}
