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

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public abstract boolean step(int row, int col, Figure n);

	public boolean isColor() {
		return color;
	}

	public String getName() {
		return name;
	}
}
