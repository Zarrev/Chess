package barzs14_project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BoardGUI {
	private int mouseClick = 0;
	private GridPane board;
	private BorderPane surface = new BorderPane();
	private Scene sc;
	private Stage stage;
	public BoardGUI() {
		draw();
		sc = new Scene(surface);
	}

	private void draw() {
		board = new GridPane();
		makeBorder();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		refill();
		showPS();
		surface.setCenter(board);
		makeMenu();
	}
	
	private void showPS(){
		if(mouseClick > 0 && Main.engine.getChossen()!=null){
			for (Integer[] e : Main.engine.getChossen().getChossenPS()) {
				for (Node node : board.getChildren()) {
					if(GridPane.getRowIndex(node) == Main.engine.getChossen().getX()+1 && GridPane.getColumnIndex(node) == Main.engine.getChossen().getY()+1){
						((Label) node).setTextFill(Color.BLUE);
					}
					
			        if(GridPane.getRowIndex(node) == e[0]+1 && GridPane.getColumnIndex(node) == e[1]+1) {
			        	((Label) node).setTextFill(Color.BLACK);
			        	if(((Label) node).getText().equals("")){
			        		((Label) node).setText("");
			        	}
			        	if(((Label) node).getText().equals("")){
			        		((Label) node).setText("");
			        	}
			        	((Label) node).setTextFill(Color.RED);
			        }
				}
			}
			mouseClick = 0;
		}
	}
	
	private void makeMenu() {
		MenuBar mb = new MenuBar();
		Menu file = new Menu("File");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		MenuItem nG = new MenuItem("New Game");
		nG.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Main.engine.reset();
				surface.getChildren().clear();
				draw();
				stage.getScene().setRoot(surface);
			}
		});
		MenuItem info = new MenuItem("Info");
		info.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Information about MineSweeper");
				alert.setHeaderText(null);
				alert.setContentText("I have a big secret!\n *Whisper* It is a Chess Game...*Whisper*\nI hope I helped to you!");
				alert.showAndWait();
			}
		});
		file.getItems().add(nG);
		file.getItems().add(info);
		file.getItems().add(exit);
		mb.getMenus().add(file);
		surface.setTop(mb);

	}
	
	private void makeBorder() {
		for (int i = 0; i < 10; i++) {
			ColumnConstraints cc = new ColumnConstraints();
			cc.setHalignment(HPos.CENTER);
			cc.setMinWidth(25);
			cc.setMaxWidth(Main.maxSize);
			board.getColumnConstraints().add(cc);
		}
		for (int j = 0; j < 10; j++) {
			RowConstraints rc = new RowConstraints();
			rc.setValignment(VPos.CENTER);
			rc.setMinHeight(25);
			rc.setMaxHeight(Main.maxSize);
			board.getRowConstraints().add(rc);
		}
		for (int i = 1; i < 9; i++) {
			Text r = new Text();
			r.setFont(Main.f);
			r.setText("");
			board.add(r, i, 0);
		}
		for (int i = 1; i < 9; i++) {
			Text r = new Text();
			r.setFont(Main.f);
			r.setText("");
			board.add(r, i, 9);
		}
		for (int i = 1; i < 9; i++) {
			Text r = new Text();
			r.setFont(Main.f);
			r.setText("");
			board.add(r, 9, i);
		}
		for (int i = 1; i < 9; i++) {
			Text r = new Text();
			r.setFont(Main.f);
			r.setText("");
			board.add(r, 0, i);
		}
		Text r = new Text();
		r.setFont(Main.f);
		r.setText("");
		board.add(r, 0, 0);
		Text r1 = new Text();
		r1.setFont(Main.f);
		r1.setText("");
		board.add(r1, 9, 0);
		Text r2 = new Text();
		r2.setFont(Main.f);
		r2.setText("");
		board.add(r2, 0, 9);
		Text r3 = new Text();
		r3.setFont(Main.f);
		r3.setText("");
		board.add(r3, 9, 9);
	}

	private void refill() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Label tmp = new Label();
				tmp.setFont(Main.f);
				if (Main.engine.getTable()[i][j] != null) {
					if (Main.engine.getTable()[i][j].getName().contains("White")) {
						if (Main.engine.getTable()[i][j].getName().contains("Pawn")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Knight")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Rook")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("King")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Queen")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Bishop")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						}
					} else if (Main.engine.getTable()[i][j].getName().contains("Black")) {
						if (Main.engine.getTable()[i][j].getName().contains("Pawn")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Knight")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Rook")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("King")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Queen")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						} else if (Main.engine.getTable()[i][j].getName().contains("Bishop")) {
							if ((i + j) % 2 == 0) { /// FEHER MEZO
								tmp.setText("");
							} else { /// FEKETE MEZO
								tmp.setText("");
							}

						}
					} 
				}else {
					if ((i + j) % 2 == 0) {
						tmp.setText("");
					} else {
						tmp.setText("");
					}
				}
				int x = i;
				int y = j;
				tmp.setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						mouseClick++;
						Main.engine.setRC(x, y);
						Main.engine.setYouCanGetData(true);
						surface.getChildren().clear();
						draw();
						stage.getScene().setRoot(surface);
					}
					
				});
				board.add(tmp, j + 1, i + 1);
			}
		}
	}

	public void setTheStage(Stage stage){
		this.stage = stage;
		stage.setScene(sc);
	}
	
	public void set(Stage pStage) {
		pStage.setScene(sc);
	}
}