package barzs14_project;

public final class Queen extends Figure {

	private final Bishop B;
	private final Rook R;
	
	public Queen(boolean color, String name) {
		super(color, name);
		B = new Bishop(color,name + "_Bishop");
		R = new Rook(color,name + "_Rook");
	}

	@Override
	public boolean step(int row, int col, Figure[][] f) {
		B.setXY(this.getX(),this.getY());
		R.setXY(this.getX(),this.getY());
		return B.step(row, col, f) || R.step(row, col, f);
	}



}
