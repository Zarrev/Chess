package barzs14_project;

import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class FigureGUI{

								//pawn,knight,bishop,rook,king,queen
	private String blacksSqB[] = {"","","","","",""};
	private String blacksSqW[] = {"","","","","",""};
	private String whiteSqB[] = {"","","","","",""};
	private String whiteSqW[] = {"","","","","",""};
	private Label figure = new Label();
	private String actS = new String();
	private String otherS = new String();
	private String whiteS = new String();
	private String blackS = new String();

	public FigureGUI(String s) {
		actS = s;
		figure.setFont(Main.f);
		figure.setText(s);
		whichIsOtherS();
		setSs();
	}
	
	public String getWhiteS() {
		return whiteS;
	}

	public String getBlackS() {
		return blackS;
	}

	public void setSs(){
		for(int i = 0; i < blacksSqB.length; i++){
			if(actS.equals(blacksSqB[i])){
				blackS = actS;
			}
		}
		for(int i = 0; i < blacksSqW.length; i++){
			if(actS.equals(blacksSqW[i])){
				whiteS = actS;
			}
		}
		for(int i = 0; i < whiteSqB.length; i++){
			if(actS.equals(whiteSqB[i])){
				blackS = actS;
			}
		}
		for(int i = 0; i < whiteSqW.length; i++){
			if(actS.equals(whiteSqW[i])){
				whiteS = actS;
			}
		}
	}
	
	public Label get() {
		return figure;
	}

	public void set(String s) {
		this.figure.setText(s);
	}
	
	private void whichIsOtherS() {
		for(int i = 0; i < blacksSqB.length; i++){
			if(actS.equals(blacksSqB[i])){
				otherS = blacksSqW[i];
				return;
			}
		}
		for(int i = 0; i < blacksSqW.length; i++){
			if(actS.equals(blacksSqW[i])){
				otherS = blacksSqB[i];
				return;
			}
		}
		for(int i = 0; i < whiteSqB.length; i++){
			if(actS.equals(whiteSqB[i])){
				otherS = whiteSqW[i];
				return;
			}
		}
		for(int i = 0; i < whiteSqW.length; i++){
			if(actS.equals(whiteSqW[i])){
				otherS = whiteSqB[i];
				return;
			}
		}
	}
	
	public String getActS() {
		return actS;
	}

	public void setActS(String actS) {
		this.actS = actS;
		whichIsOtherS();
		
	}

	public String getOtherS() {
		return otherS;
	}

	@Override
	public String toString() {
		return "FigureGUI [actS=" + actS + "]";
	}	
	
}
