package barzs14_project;

public final class Bishop extends Figure {

	public Bishop(boolean color, String name) {
		super(color, name);
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		if(row > 7 || col > 7 || row < 0 || col < 0)
			return false;

		if(((col-this.getY()) == (row-this.getX()) )|| ((col-this.getY()) == (this.getX()-row))){
			if(this.getY() > col){
				if(this.getX() > row){
					int j = 1;
					for(int i = col+1; i < col; i++){
						if(f[i][row+j] != null){
							return false;
						}
						j++;
					}
				}
				else{
					int j=-1;
		            for(int i= col+1; i < this.getY(); i++){
		            	if(f[i][row+j] != null) {
		            		return false;
	            		} 
		            	j--;
            		}
				}
			}
			 else{
					if(this.getX()>row){
					    int j=-1;
					    for(int i= this.getY()+1; i < col; i++){
					    	if(f[i][this.getX()+j] != null) {
					    		return false;
				    		} 
					    	j--;
				    	}
					}
					else{
					    int j=1;
					    for(int i= this.getY()+1; i < col; i++){
					    	if(f[i][this.getX()+j] != null) {
					    		return false;
				    		}
					    	j++;
				    	}
					}
			 }
			return true;
		}
		
		return false;
	}	
		
		/*
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
*/


}
