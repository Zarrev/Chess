package barzs14_project;

public final class Rook extends Figure {

	public Rook(boolean color, String name) {
		super(color, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if (x == row && col != y){
			if (col > y){
				for (int i = y+1; i <= col; i++){
					if (f[row][i] != null && i != col){
						return false;
					}
				}
			}
			else if (col < y){
				for(int i = y-1; i >= col; i--){
					if (f[row][i] != null && i != col)
						return false;
				}
			}
			return true;
		}
		else if (y == col && row != x){
			if (row > x){
				for (int i = x+1; i <= row; i++){
					if (f[i][col] != null && i != row){
						return false;
					}
				}
			}
			else if (row < x){
				for(int i = x-1; i >= row; i--){
					if (f[i][col] != null && i != row)
						return false;
				}
			}
			return true;
		}
		return false;
	}
}
