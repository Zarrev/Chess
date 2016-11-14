package barzs14_project;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BoardGUI {
	private GridPane board = new GridPane();
	private String blackOp = "";
	private String whiteOp = "";
	//figure.setFill(Color.RED); TODO
	FigureGUI fg[][] ={{new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")},
			   {new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI(""),new FigureGUI("")}};
	
	public BoardGUI() {
		makeBoard();
	}
	private void makeBoard() {
		for(int i = 0; i < 10; i++){
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.CENTER);
			cc.setMinWidth(25);
			cc.setMaxWidth(Main.maxSize);
			board.getColumnConstraints().add(cc);
		}
		for(int j = 0; j < 10; j++){
			RowConstraints rc = new RowConstraints();
			rc.setValignment(VPos.CENTER);
			rc.setMinHeight(25);
			rc.setMaxHeight(Main.maxSize);
			board.getRowConstraints().add(rc);
		}
		makeFrame();
	}
	
	private void makeFrame() {
		for(int i = 1; i < 9; i++){
		Text r = new Text();
        r.setFont(Main.f);
        r.setText("");
        board.add(r,i,0);
		}
		for(int i = 1; i < 9; i++){
		Text r = new Text();
        r.setFont(Main.f);
        r.setText("");
        board.add(r,i,9);
		}
		for(int i = 1; i < 9; i++){
		Text r = new Text();
        r.setFont(Main.f);
        r.setText("");
        board.add(r,9,i);
		}
		for(int i = 1; i < 9; i++){
		Text r = new Text();
        r.setFont(Main.f);
        r.setText("");
        board.add(r,0,i);
		}
		Text r = new Text();
		r.setFont(Main.f);
        r.setText("");
        board.add(r,0,0);
        Text r1 = new Text();
        r1.setFont(Main.f);
        r1.setText("");
        board.add(r1,9,0);
        Text r2 = new Text();
        r2.setFont(Main.f);
        r2.setText("");
        board.add(r2,0,9);
        Text r3 = new Text();
        r3.setFont(Main.f);
        r3.setText("");
        board.add(r3,9,9);
        fillWithFigures();
	}
	
	public GridPane get() {
		return board;
	}
	
	private void fillWithFigures() {
		// TODO Auto-generated method stub
		
		for(int k = 1; k < 9; k++){
			for(int i = 1; i < 9; i++){
				board.add(fg[k-1][i-1].get(), i, k);
				final Integer x = i-1;
				final Integer y = k-1;
				Label tmp = fg[k-1][i-1].get();
				tmp.setOnMouseClicked(new EventHandler<MouseEvent>(){
					@Override
					public void handle(MouseEvent event) {
						Main.engine.setRC(y, x);
						Main.engine.setYouCanGetData(true);
						//event.consume();
						tmp.setTextFill(Color.RED);
						tmp.setText("");
						BoardGUI.refresh();
						
						/*new Thread(new Runnable(){ //kulon szallon probaltam de null pointer exceptiont kapok
							@Override
							public void run() {
								BoardGUI.refresh();
							}
						}).start();*/
					}
				});
			}
		}
	}
	//TODO
	private void setGridPaneE(int row, int col){ ///ennek kellene megvaltoztatnia a a figureGUI elemeket lepes elott utan stb, de valamiert nem jo...
		for (Node node : board.getChildren()) {
	        if(GridPane.getRowIndex(node) == row+1 && GridPane.getColumnIndex(node) == col+1) {
	        	if((row+col)%2 == 0){
	        		System.out.println("feher");
	        		
	        		Platform.runLater(new Runnable(){
						@Override
						public void run() {
							// TODO Auto-generated method stub
							((Label) node).setTextFill(Color.RED);
							((Label) node).textProperty().bind(new SimpleStringProperty(fg[row][col].getBlackS()));		
						}
					});
					//((Label) node).setText(fg[row][col].getBlackS());
					//((Label) node).textProperty().bind(new SimpleStringProperty(fg[row][col].getBlackS()));
				}
				else{
					//((Label) node).setText(fg[row][col].getBlackS());
					System.out.println("feka");
				}
	        }
		}
	}
	
	public static void refresh() {//nincs kesz
		BoardGUI tmp = new BoardGUI();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(Main.engine.getTable()[i][j] != null){
					if(Main.engine.getTable()[i][j].getName().contains("White")){
						if(Main.engine.getTable()[i][j].getName().contains("Pawn")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Knight")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Rook")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("King")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Queen")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Bishop")){
							tmp.setGridPaneE(i,j);
							return;
						}
					}
					else if (Main.engine.getTable()[i][j].getName().contains("Black")){
						if(Main.engine.getTable()[i][j].getName().contains("Pawn")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Knight")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Rook")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("King")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Queen")){
							tmp.setGridPaneE(i,j);
							return;
						}
						if(Main.engine.getTable()[i][j].getName().contains("Bishop")){
							tmp.setGridPaneE(i,j);
							return;
						}
					}
				}
			}
		}

	}
	
}
