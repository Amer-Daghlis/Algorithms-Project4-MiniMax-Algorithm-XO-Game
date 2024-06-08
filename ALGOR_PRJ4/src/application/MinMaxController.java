package application;

import java.io.IOException;
import java.util.Random;
import javax.swing.JOptionPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MinMaxController {
	private Stage stage;
	private Scene scene;
	private Parent root;
	Stage primaryStage = new Stage();
	Random random = new Random();
	EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
	};
	static String x = "X";
	static String o = "O";
	static String player = "O", opponent = "X";
	private static int MAX_DEPTH = 9;
	Button[] postion = new Button[9];
	private int totalRounds = 0;
	private int wins = 0;
	private int losses = 0;
	private int draws = 0;

	@FXML
	private TextField name;

	@FXML
	private RadioButton yes;

	@FXML
	private ToggleGroup FirstOrNot;

	@FXML
	private RadioButton no;

	@FXML
	private Button backbtn;
	@FXML
	private TextField roundsField;

	@FXML
	private TextField winsField;

	@FXML
	private TextField lossesField;

	@FXML
	private TextField DrawField;

	@FXML
	private TextArea Moves;

	@FXML
	void BackOnAction(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stage = new Stage();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();

		Stage stage = (Stage) backbtn.getScene().getWindow();
		// do what you have to do
		stage.close();

	}

	@FXML
	void Startbtn(ActionEvent event) {
		if (name.getText() != "" && (!yes.isSelected() || !no.isSelected())) {

			GridPane Bordroot = new GridPane();
			Bordroot.setHgap(10);
			Bordroot.setVgap(10);
		    Bordroot.setStyle("-fx-background-color: #EEE2DE;"); // This is a bright green, adjust the hex code for different shades

			GridPane pane = new GridPane();
			pane.setHgap(2);
			pane.setVgap(2);
			pane.setStyle("-fx-background-color: #EEE2DE;"); // This is a bright green, adjust the hex code for different shades

			roundsField = new TextField();
			winsField = new TextField();
			lossesField = new TextField();
			DrawField = new TextField();
			TextField[] textFields = {roundsField, winsField, lossesField, DrawField};

			for (TextField textField : textFields) {
			    textField.setEditable(false);
			    textField.setAlignment(Pos.CENTER); // Set text to be in the center
			    textField.setStyle("-fx-background-color: #A9A9A9; " + // Set background to black
			                       "-fx-text-fill: white; " + // Set text color to white
			                       "-fx-border-radius: 10; " + // Set the border radius
			                       "-fx-background-radius: 10;"); // Set the background radius to make it rounded
			}
			roundsField.setEditable(false);
			winsField.setEditable(false);
			lossesField.setEditable(false);
			DrawField.setEditable(false);

			Moves = new TextArea();
			Moves.setEditable(false);

			Moves.setPrefWidth(250);
			Moves.setPrefHeight(150);
			

			for (int i = 0; i < postion.length; i++) {
			    postion[i] = new Button(" ");
			    postion[i].setPrefSize(90, 90);
			    postion[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 34));
			    // Set the button background to black and text to white for contrast
			    postion[i].setStyle("-fx-background-color: #FF9130; " +
                        "-fx-text-fill: white; " +
                        "-fx-border-radius: 15; " +
                        "-fx-background-radius: 15;");
	   

			}
			pane.addRow(0, postion[0], postion[1], postion[2]);
			pane.addRow(1, postion[3], postion[4], postion[5]);
			pane.addRow(2, postion[6], postion[7], postion[8]);

			GridPane pane1 = new GridPane();
			pane1.setHgap(2);
			pane1.setVgap(2);
			pane1.setStyle("-fx-background-color: #EEE2DE;"); // This is a bright green, adjust the hex code for different shades

			pane1.add(new Label("Rounds:"), 2, 5);
			pane1.add(roundsField, 2, 6);
			pane1.add(new Label("Wins:"), 2, 7);
			pane1.add(winsField, 2, 8);
			pane1.add(new Label("Losses:"), 2, 9);
			pane1.add(lossesField, 2, 10);
			pane1.add(new Label("Draw:"), 2, 11);
			pane1.add(DrawField, 2, 12);

			Bordroot.add(pane1, 2, 5);
			pane.setAlignment(Pos.BASELINE_LEFT);

			GridPane pane2 = new GridPane();
			pane2.setHgap(2);
			pane2.setVgap(2);
			pane2.setStyle("-fx-background-color: #EEE2DE;"); // This is a bright green, adjust the hex code for different shades

			pane2.add(Moves, 9, 4);

			Bordroot.add(Moves, 9, 5);
			pane.setAlignment(Pos.BOTTOM_RIGHT);

			Bordroot.add(pane, 5, 5);
			pane.setAlignment(Pos.CENTER);
			Label textfeild = new Label();
			textfeild.setText("Player Name : " + name.getText());
			textfeild.setFont(Font.font("Times New Roman", 15));

			if (yes.isSelected()) {
				player = x;
			} else if (no.isSelected()) {
				player = o;
			}

			Bordroot.add(textfeild, 2, 2);
			Button exit = new Button("Exit");
			Bordroot.add(exit, 2, 10);
			exit.setAlignment(Pos.BOTTOM_CENTER);
			exit.setPrefSize(80, 30);
			exit.setFont(Font.font("Times New Roman", 14));

			EventHandler<ActionEvent> eventHandler = e -> {
				Button btn = (Button) e.getSource();
				if (btn.getText().equals(" ")) {
					btn.setText(player);
					if (player.equals(x)) {
						player = o;
						MinmaxGame();
					} else {
						if (player.equals(o))
							player = x;
					}
					checkGameStatus();
				}
			};

			for (int i = 0; i < 9; i++) {
				postion[i].setOnAction(eventHandler);
			}
			Scene scene = new Scene(Bordroot, 800, 500);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

			if (yes.isSelected()) {
				player = x;
			} else if (no.isSelected()) {
				player = o;
			}
			MinmaxGame();

			exit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
			exit.setStyle("-fx-background-color: #FF5B22; " + // Set background to red
			              "-fx-text-fill: white; " + // Set text color to white
			              "-fx-border-radius: 15; " + // Set the border radius
			              "-fx-background-radius: 15;"); // Set the background radius to make it rounded
			exit.setOnAction(e -> {
			    Stage stage = (Stage) exit.getScene().getWindow();
			    stage.close();
			});

		}

	}

	private void MinmaxGame() {
		String board[][] = { { postion[0].getText(), postion[1].getText(), postion[2].getText() },
				{ postion[3].getText(), postion[4].getText(), postion[5].getText() },
				{ postion[6].getText(), postion[7].getText(), postion[8].getText() } };

		while (player == o && isMovesLeft(postion)) {
			if (isWin(postion)) {
				for (Button boardButton : postion) {
					boardButton.removeEventHandler(ActionEvent.ACTION, eventHandler);
				}
				player = "";
				return;
			}
			int[] bestMove = getBestMove(board, Moves);

			postion[bestMove[0] * 3 + bestMove[1]].setText("O");
			if (isWin(postion)) {
				for (Button boardButton : postion) {
					boardButton.removeEventHandler(ActionEvent.ACTION, eventHandler);
				}
				player = "";
				return;
			}
			player = x;
		}
		if (!isMovesLeft(postion)) {
			for (Button boardButton : postion) {
				boardButton.removeEventHandler(ActionEvent.ACTION, eventHandler);
			}
		}
	}

	private void checkGameStatus() {
	    String finalResult = ""; // Variable to store the result message

	    if (isWin(postion)) {
	        String winner = player.equals(o) ? name.getText() : "AI";
	        updateGameStats(winner);
	        finalResult = winner + " wins!";
	        startNewGame();
	    } else if (!isMovesLeft(postion)) {
	        updateGameStats("Draw");
	        finalResult = "It's a Draw!";
	        startNewGame();
	    }

	    // Check if finalResult is not empty, then display the result
	    if (!finalResult.isEmpty()) {
	    	showMessage1(finalResult);
	    }
	}
	private void showMessage1(String message) {
	    // SwingUtilities.invokeLater is used to run the JOptionPane on the Event Dispatch Thread
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            JOptionPane.showMessageDialog(null, message);
	        }
	    });
	}

	private void updateGameStats(String winner) {
		totalRounds++;
		if (winner.equals(name.getText())) {
			wins++;
		} else if (winner.equals("Computer")) {
			losses++;
		} else if (winner.equals("Draw")) {
			draws++;
		}
		roundsField.setText(String.valueOf(totalRounds));
		winsField.setText(String.valueOf(wins));
		lossesField.setText(String.valueOf(losses));
		DrawField.setText(String.valueOf(draws));
	}

	private void startNewGame() {
		Moves.clear();

		resetBoard();

		if (yes.isSelected()) {
			player = x;
		} else if (no.isSelected()) {
			player = o;
		}
		if (player.equals(o)) {
			MinmaxGame();
		}
	}

	private void resetBoard() {
		for (Button btn : postion) {
			btn.setText(" ");
		}
	}
//هذول الميثودين برجعنلي اذا البورد فاظي او لا بس اشي من البوتون نفسها واشي من السترينج للبورد تبعية التو دايمنشال ارري 
	static Boolean isMovesLeft(Button buttons[]) {
		for (int i = 0; i < 9; i++)
			if (buttons[i].getText().equals(" "))
				return true;
		return false;
	}

	static Boolean isMovesLeft(String board[][]) {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (board[i][j] == " ")
					return true;
		return false;
	}

	public static int miniMax(String[][] board, int depth, boolean isMax) {
		int boardVal = evaluateBoard(board, depth);// هاي حتبكا الفاليو تبعية البورد اذا والي هي عدد الاكسس و الاو الي
													// في البورد

		if (Math.abs(boardVal) > 0 || depth == 0 || !isMovesLeft(board)) {// هاي الميثود حتفحصلي اذا القيمة اكبر من صفر
																			// يعني انو في خسارة وفوز او انو الديبث تبع
																			// الريكيرشن ديبث صفر او انو لس مفش اي بوتون
																			// مكبوسة رجعلي نفس القيمة
			return boardVal;
		}
		// هاي الجزئية رح تعطيني البيست سكور للكل الاحتمالات الممكنة الي ممكن يسويها
		// اللاعب
		if (isMax) {// هاي انو ماكسمايزينج يعني اللاعب الي بلعب يعني الاكس
			int highestVal = Integer.MIN_VALUE;// حتنحط فيها اكبر قيمة ممكن انو اللاعب يحققها
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {// رح يفحصلي البوتونز الي عندي
					if (board[row][col] == " ") {// اذا البوتون فاظية
						board[row][col] = player;// والدور عاللاعب الي عليه ماكمايزينج
						highestVal = Math.max(highestVal, miniMax(board, depth - 1, false));// نقص من الديبث واحد
						board[row][col] = " ";
					}
				}
			}
			return highestVal;
			// هاي الجزئية
		} else {
			int lowestVal = Integer.MAX_VALUE;
			for (int row = 0; row < 3; row++) {
				for (int col = 0; col < 3; col++) {
					if (board[row][col] == " ") {
						board[row][col] = opponent;
						lowestVal = Math.min(lowestVal, miniMax(board, depth - 1, true));
						board[row][col] = " ";
					}
				}
			}
			return lowestVal;
		}
		// الماكسيمازينج دايما رح اتوقع اعلى قيمة والي هي الي رح تكون موجبة والي هي في
		// صالحي دائما وفي المينيمايزينج رح اتوقع القيمة الي دايما اسوأ الي وممكن تخسرني
		// الجيم
	}

	// ها الميثود حترجعلي احسن تحررك ممكن اتحركو
	public static int[] getBestMove(String[][] board, TextArea moves) {
	    int[] bestMove = new int[] { -1, -1 };
	    int bestValue = Integer.MIN_VALUE;

	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 3; col++) {
	            if (board[row][col].equals(" ")) {
	                board[row][col] = player;
	                int moveValue = miniMax(board, MAX_DEPTH, false);
	                board[row][col] = " ";

	                // Interpret the move value
	                String moveResult = (moveValue > 0) ? "win" : (moveValue < 0) ? "loss" : "draw";

	                // Append the status of the button and the result of the move
	                moves.appendText("Button at (" + (row + 1) + "," + (col + 1) + "): empty, " +
	                                 "if played, result: " + moveResult + " (" + moveValue + ")\n");

	                if (moveValue > bestValue) {
	                    bestMove[0] = row;
	                    bestMove[1] = col;
	                    bestValue = moveValue;
	                }
	            } else {
	                // Append the status of the button as not empty
	                moves.appendText("Button at (" + (row + 1) + "," + (col + 1) + "): not empty, " +
	                                 "current value: " + board[row][col] + "\n");
	            }
	        }
	    }
	    return bestMove;
	}

	private static int evaluateBoard(String[][] board, int depth) {
		int rowSum = 0;// هاي القيمة رح نستخدمها عشان نجمع قيمة الصف او العمود
		int bWidth = 3;// هان الويدث تاع الللعبة الي يهو بس ثلث ازرار
		int Xwin = 'X' * 3;// هان يعني انو عدد الاكسات اللازمة عشان تفوز هني ثلاث
		int Owin = 'O' * 3;// نفس الي فوق

		// Check rows for winner.
		for (int row = 0; row < bWidth; row++) {// ببلش افحص في الصفوف كلهن اول اشي
			for (int col = 0; col < bWidth; col++) {
				rowSum += ((board[row][col]).toCharArray()[0]);// هاي اللوب بتجمعلي الفاليو تاعية القيم الي الموجودات في
																// الزر
			}
			if (rowSum == Owin) {
				return 10 + depth; // اذا فاز الاو اعطيني نتيجة بوسيتيف
			} else if (rowSum == Xwin) {
				return -10 - depth; // اذا فاز الاكس اعطيني نتيجة نتيجاتيف
			} // البوسيتيف والنيجاتيف بس نتيجة عشان افررق بين الاكس والاو ممكن ارجعه كلمات
				// اواي اشي بتفرقش
			rowSum = 0;// صفرلي اياه
		}

		// Check columns for winner.
		rowSum = 0;
		for (int col = 0; col < bWidth; col++) {
			for (int row = 0; row < bWidth; row++) {
				rowSum += ((board[row][col]).toCharArray()[0]);
			}
			if (rowSum == Owin) {
				return 10 + depth;
			} else if (rowSum == Xwin) {
				return -10 - depth;
			}
			rowSum = 0;
		}

		// Check diagonals for winner.
		rowSum = 0;
		for (int i = 0; i < bWidth; i++) {
			rowSum += ((board[i][i]).toCharArray()[0]);
		}
		if (rowSum == Owin) {
			return 10 + depth;
		} else if (rowSum == Xwin) {
			return -10 - depth;
		}

		rowSum = 0;
		int indexMax = bWidth - 1;
		for (int i = 0; i <= indexMax; i++) {
			rowSum += ((board[i][indexMax - i]).toCharArray()[0]);
		}
		if (rowSum == Owin) {
			return 10 + depth;
		} else if (rowSum == Xwin) {
			return -10 - depth;
		}

		return 0;
	}

	// هاي الميثود بتطلعلي انو الي فاز
	static Boolean isWin(Button buttons[]) {
		for (int i = 0; i < 7; i = i + 3) {
			if (!buttons[i].getText().equals(" ") && buttons[i].getText().equals(buttons[i + 1].getText())
					&& buttons[i].getText().equals(buttons[i + 2].getText())) {
				return true;
			}
		}
		for (int i = 0; i < 3; i++) {
			if (!buttons[i].getText().equals(" ") && buttons[i].getText().equals(buttons[i + 3].getText())
					&& buttons[i].getText().equals(buttons[i + 6].getText())) {
				return true;
			}
		}

		if ((!buttons[0].getText().equals(" ") && buttons[0].getText().equals(buttons[4].getText())
				&& buttons[0].getText().equals(buttons[8].getText()))
				|| (!buttons[2].getText().equals(" ") && buttons[2].getText().equals(buttons[4].getText())
						&& buttons[2].getText().equals(buttons[6].getText()))) {
			return true;
		}
		return false;
	}

	public void newSence(String finalresult) {
		GridPane pane = new GridPane();
		pane.setHgap(3);
		pane.setVgap(3);
		Button Exit = new Button("Exit");
		Exit.setFont(Font.font("Times New Roman", 15));
		Button Menu = new Button("Go To Menu");
		Menu.setFont(Font.font("Times New Roman", 15));
		Label Final = new Label(finalresult);
		Final.setFont(Font.font("Times New Roman", 25));
		pane.add(Exit, 6, 6);
		pane.add(Menu, 7, 6);
		pane.add(Final, 2, 2);

		Exit.setOnAction(e -> {
			Stage stage = (Stage) Exit.getScene().getWindow();
			stage.close();
		});

		Menu.setOnAction(e -> {
			try {
				root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			} catch (IOException r) {
				r.printStackTrace();
			}
			stage = new Stage();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			Stage currentStage = (Stage) Menu.getScene().getWindow();
			currentStage.close();
			primaryStage.close();
		});

		Scene scene = new Scene(pane, 450, 120);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
