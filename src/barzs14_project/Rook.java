package barzs14_project;

public final class Rook extends Figure {

	public Rook(boolean color, String name) {
		super(color, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean step(int row, int col, Figure f) {
		if(x-row == 0)
	    {
	        if(col>y)
	        {
	            for(int i= ++y; i < col; i++)
	            {
	                if(Pieces[i][row] != null) return false;
	            }
	        }
	        else
	        {
	            for(int i= ++col;i < y; i++)
	            {
	                if(Pieces[i][row] != null) return false;
	            }
	        }

	        return true;
	    }
	    else if(y-col == 0)
	    {
	        if(row>x)
	        {
	            for(int i= ++x; i < row; i++)
	            {
	                if(Pieces[col][i] != null) return false;
	            }
	        }
	        else
	        {
	            for(int i = ++row; i < x; i++)
	            {
	                if(Pieces[col][i] != null) return false;
	            }
	        }

	        return true;
	    }
	    else return false;
	}



}
