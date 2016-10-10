package barzs14_project;

public final class Knight extends Figure {

	public Knight(boolean color, String name) {
		super(color, name);
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(f[row][col] != null){
			if(f[row][col].isColor() == this.isColor()){
				return false;
			}
		}
		
		if((row - this.getX())*(row - this.getX()) + (col - this.getY())*(col - this.getY()) == 5){
			return true;
		}
		
		return false;
	}

}
