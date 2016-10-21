package barzs14_project;

import java.util.Vector;

public abstract class Figure {
	protected class PS{
		public int x;
		public int y;
		public PS(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
	}
	protected boolean color;
	protected String name;
	protected int x, y;
	protected Vector<PS> possibleSteps;
	
	public Figure(boolean color, String name) {
		super();
		this.color = color;
		this.name = name;
		x = -1;
		y = -1;
		possibleSteps = new Vector<>();
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
	
	public Vector<PS> getSteps(){
		return possibleSteps;
	}
	
	public void addSteps(int k, int l){
		this.possibleSteps.add(new PS(k,l));
	}
	
	public void delSteps(PS e){
		this.possibleSteps.remove(e);
	}
	
	public void setSteps(Vector<PS> o){
		this.possibleSteps = o;
	}
	
	public String stepToString(){
		StringBuilder sb = new StringBuilder();
		for (PS ps : possibleSteps) {
			sb.append(this.getName() + ": (");
			sb.append(ps.x + ", ");
			sb.append(ps.y + ")\n");
		}
		return sb.toString();
	}
	
	public void clearSteps(){
		this.possibleSteps.clear();
	}
}
