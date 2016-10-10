package barzs14_project;

public final class Bishop extends Figure {

	public Bishop(boolean color, String name) {
		super(color, name);
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if ((x-row == y-col) || (row-x == col-y)){
			if (y < col){
				if(x < row){
					int i = x+1;
					for(int j = y+1; j <= col && i <= row; j++){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i++;
					}
				}
				else if (x > row){
					int i = x-1;
					for (int j = y+1; j <= col && i >= row; j++){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i--;
					}
				}
				return true;
			}
			else if (y > col){
				if (x < row){
					int i = x+1;
					for(int j = y-1; j >= col && i <= row; j--){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i++;
					}
				}
				else if (x > row){
					int i = x-1;
					for(int j = y-1; j >= col && i >= row; j--){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i--;
					}
				}
				return true;
			}
		}
		return false;
	}



}
