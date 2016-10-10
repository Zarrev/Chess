package barzs14_project;

public final class Bishop extends Figure {

	public Bishop(boolean color, String name) {
		super(color, name);
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		/*if(((col-y) == (row-x) )|| ((col-y) == (x-row)) ){
		    if(y > col){
		        if(x>row){
		            int j=1;
		            for(int i= col+1; i < y; i++) {
		            	if(f[i][row+j] != null && i != col) {
		            		return false;
		            		} 
		            	j++;
		            	}
		        }
		        else{
		            int j=-1;
		            for(int i= col+1; i < y; i++){
		            	if(f[i][row+j] != null && i != col) {
		            		return false;
		            		} 
		            	j--;
		            	}
		        }
		    }
		    else{
		        if(x>row){
		            int j=-1;
		            for(int i= y+1; i < col; i++){
		            	if(f[i][x+j] != null && i != row) {
		            		return false;
		            		} 
		            	j--;
		            	}
		        }
		        else{
		            int j=1;
		            for(int i= y+1; i < col; i++){
		            	if(f[i][x+j] != null && i != row) {
		            		return false;
		            	}
		            	j++;
		            	}
		        }
		    }
		    return true;
	    }
	    else return false;*/
		if ((x-row == y-col) || (row-x == col-y)){
			if (y < col){
				if(x < row){
					
				}
				else if (x > row){
					
				}
			}
			else if (y > col){
				if (x < row){
					
				}
				else if (x > row){
					
				}
			}
		}
		return false;
	}



}
