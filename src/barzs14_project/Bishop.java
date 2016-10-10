package barzs14_project;

public final class Bishop extends Figure {

	public Bishop(boolean color, String name) {
		super(color, name);
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if ((this.getX()-row == this.getY()-col) || (row-this.getX() == col-this.getY())){
			if (this.getY() < col){
				if(this.getX() < row){
					int i = this.getX()+1;
					for(int j = this.getY()+1; j <= col && i <= row; j++){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i++;
					}
				}
				else if (this.getX() > row){
					int i = this.getX()-1;
					for (int j = this.getY()+1; j <= col && i >= row; j++){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i--;
					}
				}
				return true;
			}
			else if (this.getY() > col){
				if (this.getX() < row){
					int i = this.getX()+1;
					for(int j = this.getY()-1; j >= col && i <= row; j--){
						if(f[i][j] != null && j != col && i != row){
							return false;
						}
						i++;
					}
				}
				else if (this.getX() > row){
					int i = this.getX()-1;
					for(int j = this.getY()-1; j >= col && i >= row; j--){
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
