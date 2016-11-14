package barzs14_project;

import java.util.ArrayList;

public abstract class Figure {
	
	protected boolean color;
	protected String name;
	protected int x, y;
	private class PoSt{
		public int x;
		public int y;
		public PoSt(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
	private ArrayList<PoSt> possibleSteps = new ArrayList<>();	
	public Figure(boolean color, String name) {
		super();
		this.color = color;
		this.name = name;
		x = -1;
		y = -1;
	}

	public void clearPS(){
		possibleSteps.clear();
	}
	
	public void add(int x, int y){
		possibleSteps.add(new PoSt(x,y));
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
	public ArrayList<Integer[]> getChossenPS(){
		ArrayList<Integer[]> tmp = new ArrayList<>();
		for (PoSt e : possibleSteps) {
			Integer[] o = {e.x,e.y};
			tmp.add(o);
		}
		return tmp;
	}
}
