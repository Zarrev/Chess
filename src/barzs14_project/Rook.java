package barzs14_project;

public final class Rook extends Figure {

	public Rook(boolean color, String name) {
		super(color, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(row > 7 || col > 7 || row < 0 || col < 0)
			return false;
		
		if (this.getX() == row && col != this.getY()){
			if (col > this.getY()){
				for (int i = this.getY()+1; i <= col; i++){
					if (f[row][i] != null && i != col){
						return false;
					}
				}
			}
			else if (col < this.getY()){
				for(int i = this.getY()-1; i >= col; i--){
					if (f[row][i] != null && i != col)
						return false;
				}
			}
			return true;
		}
		else if (this.getY() == col && row != this.getX()){
			if (row > this.getX()){
				for (int i = this.getX()+1; i <= row; i++){
					if (f[i][col] != null && i != row){
						return false;
					}
				}
			}
			else if (row < this.getX()){
				for(int i = this.getX()-1; i >= row; i--){
					if (f[i][col] != null && i != row)
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
