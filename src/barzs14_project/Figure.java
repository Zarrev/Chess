package barzs14_project;

public abstract class Figure {
	protected boolean color;
	protected String name;
	protected int x, y;
	
	public Figure(boolean color, String name) {
		super();
		this.color = color;
		this.name = name;
		x = -1;
		y = -1;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setXY(int x, int y){
		this.x = x;
		this.y = y;
	}

	public abstract boolean step(int row, int col, Figure[][] f);

	public boolean isColor() {
		return color;
	}

	public String getName() {
		return name;
	}
}
