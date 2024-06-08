package application;

import java.awt.Dimension;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EasyController {
	Stage primaryStage = new Stage();

	private Stage stage;
	private Scene scene;
	private Parent root;
	Button[] map = new Button[9];
	boolean player_turn;
	Random random = new Random();
	final ArrayList<Integer> PLayer = new ArrayList<>();
	final ArrayList<Integer> Computer = new ArrayList<>();
	GridPane Bordroot = new GridPane();

	EventHandler<ActionEvent> eventHandler = (ActionEvent e) -> {
	};
	int XOCounter = 0;
	private Label outcomeLabel;

	@FXML
	private TextField name;

	@FXML
	private RadioButton yes;

	@FXML
	private Button start;

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
	private TextField numRounds; // TextField for number of rounds

	private int totalRounds; // Variable to store the total number of rounds
	private int currentRound = 0;

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
		if (!name.getText().isEmpty() && (yes.isSelected() || no.isSelected())) {
			Bordroot.setHgap(10);
			Bordroot.setVgap(10);
			Bordroot.setStyle("-fx-background-color: #EEE2DE;");
			roundsField = new TextField("0");
			winsField = new TextField("0");
			lossesField = new TextField("0");
			DrawField = new TextField("0");
			TextField[] textFields = { roundsField, winsField, lossesField, DrawField };

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

			GridPane pane = new GridPane();
			pane.setHgap(3);
			pane.setVgap(3);
			pane.setStyle("-fx-background-color: #EEE2DE; -fx-padding: 10; -fx-hgap: 10; -fx-vgap: 10;"); // Set the
																											// background
																											// color and
																											// padding

			GridPane pane1 = new GridPane();
			pane1.setHgap(2);
			pane1.setVgap(2);

			for (int i = 0; i < map.length; i++) {
				map[i] = new Button();
				map[i].setPrefSize(90, 90);
				map[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 34));
				map[i].setStyle("-fx-background-color: #FF9130; " + "-fx-text-fill: white; " + "-fx-border-radius: 15; "
						+ "-fx-background-radius: 15;");
				map[i].setFont(Font.font("Times New Roman", 34)); // Black background and white text
				int j = i;
				map[i].setOnAction(e -> {
					handleButtonClick(j);
				});
			}
			for (int i = 0; i < map.length; i++) {
				pane.add(map[i], i % 3, i / 3);
			}
			try {
				totalRounds = Integer.parseInt(numRounds.getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Please enter a valid number of rounds");
				return;
			}
			currentRound = 0;
			Bordroot.add(pane, 7, 5);
			pane.setAlignment(Pos.CENTER);

			pane1.add(new Label("Rounds:"), 2, 5);
			pane1.add(roundsField, 2, 6);
			pane1.add(new Label("Wins:"), 2, 7);
			pane1.add(winsField, 2, 8);
			pane1.add(new Label("Losses:"), 2, 9);
			pane1.add(lossesField, 2, 10);
			pane1.add(new Label("Draw:"), 2, 11);
			pane1.add(DrawField, 2, 12);

			Bordroot.add(pane1, 2, 5);
			pane1.setAlignment(Pos.BASELINE_LEFT);

			Label playerNameLabel = new Label("Player Name: " + name.getText());
			playerNameLabel.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 15));
			Bordroot.add(playerNameLabel, 2, 2);

			player_turn = yes.isSelected();

			Button exit = new Button("Exit");
			exit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
			exit.setStyle("-fx-background-color: #FF5B22; " + // Set background to red
					"-fx-text-fill: white; " + // Set text color to white
					"-fx-border-radius: 15; " + // Set the border radius
					"-fx-background-radius: 15;"); // Set the background radius to make it rounded
			exit.setOnAction(e -> {
				Stage stage = (Stage) exit.getScene().getWindow();
				stage.close();
			});
			Bordroot.add(exit, 2, 13);

			scene = new Scene(Bordroot, 600, 500);
			primaryStage.setScene(scene);
			primaryStage.show();

			// Call computerTurn() if the computer is supposed to start
			if (!player_turn) {
				computerTurn();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Please Set All Data");
		}
	}

	// هاي الميثود هي الي بتبلشلي الجيم
	private void handleButtonClick(int index) {
		if (map[index].getText().equals("")) {// السطر ها بفحصلي انو كل البوتونز فاظيات عادي
			map[index].setText(player_turn ? "X" : "O");// هاي انو دور اللاعب ببكا اكس اور اوو في بعض حالات مش ممكن تتحق
			if (player_turn) {
				PLayer.add(index + 1);// اذا بكا دور اللاعب واللاعب ضغط بضيف الاندكس تاعة الزرر لتحركات اللاعب
			} else {
				Computer.add(index + 1);// اذا بكا دور الكومبيوتر والكومبيوتر ضغط بضيف الاندكس تاعة الزرر لتحركات
										// الكومبيوتر
			}
			player_turn = !player_turn;// هان بغيرلي الدور من اللاعب للكمبيوتر او العكس حسب ],v ldk
			checkRoundOutcome();// بستدعي الميثود
		}
	}

	// هاي الميثود حتفحصلي اذا بكا في فايز او انهم يكملو لعب عادي
	private void checkRoundOutcome() {
		String winner = checkWinner();
		if (!winner.isEmpty()) {
			updateCounters(winner);
			resetBoard();
			currentRound++; // Increment the round counter

			// Check if the number of rounds is completed
			if (currentRound >= totalRounds) {
				JOptionPane.showMessageDialog(null, "End of the number of rounds.");
				closeStage(); // Close the stage
			}
		} else if (!player_turn) {
			computerTurn();
		}
	}

	private void closeStage() {
		// Assuming 'primaryStage' is the Stage you want to close and is a member of
		// EasyController
		// If 'primaryStage' is not available, you might need to find the current stage
		// from any control like 'name' or 'Bordroot'
		Platform.runLater(() -> {
			if (primaryStage != null) {
				primaryStage.close();
			} else {
				// If primaryStage is not available, use any control to get the stage
				Stage stage = (Stage) name.getScene().getWindow();
				stage.close();
			}
		});
	}

	// هاي الميثود بتخلي الكومبيوتر يكبس عالبوتون بطريقة عشوائية
	private void computerTurn() {
		while (true) {// طول ما في فراغات والكومبيوتر بقدر انو يكبس زر ظلك شغال
			int randomNumber = random.nextInt(9);// بجبلي رقم عشوائي عشان تحركات الكومبيوتر من 1-9
			if (map[randomNumber].getText().equals("")) {// بعملي تشيك اذا الرقم الي اختارو المربع الي في الرقم مليان او
															// فاظي
				map[randomNumber].setText("O");// اذا فاظي حط اوو
				Computer.add(randomNumber + 1);// زيد واحد على الليست تبعة تحركات الكومبيوتر عشان يجيب قيم اكبر من المرة
												// السابقة
				player_turn = true;// خلي الي بلعب هو الشخص
				checkRoundOutcome();
				break;// خلص اطلع من اللوب
			}
		}
	}

	// هاي الميثود بتعملي تحدييث لقيم التيكست فيلد الي عندي بعد كل راوند
	private void updateCounters(String result) {
		int rounds = Integer.parseInt(roundsField.getText()) + 1;
		roundsField.setText(String.valueOf(rounds));

		if (result.equals("Player One Win")) {
			int wins = Integer.parseInt(winsField.getText()) + 1;
			winsField.setText(String.valueOf(wins));
		} else if (result.equals("Player Two Win")) {
			int losses = Integer.parseInt(lossesField.getText()) + 1;
			lossesField.setText(String.valueOf(losses));
		} else if (result.equals("CAT")) {
			int draws = Integer.parseInt(DrawField.getText()) + 1;
			DrawField.setText(String.valueOf(draws));
		}
	}

	// هاي الميثود بتعملي رستارت للبوتنوز بعد ما يخللص الراوند وبتعملي كليير لليست
	// التحركات تبعية اللاعب والكومبيوتر
	private void resetBoard() {
		// Clear all buttons
		for (Button btn : map) {
			btn.setText("");
		}
		// Clear player and computer moves
		PLayer.clear();
		Computer.clear();

		// Reset the turn based on the initial choice
		// If 'no' is selected (i.e., computer starts), then invoke computerTurn()
		if (no.isSelected()) {
			computerTurn();
		} else {
			player_turn = true; // If 'yes' is selected, player starts
		}
	}

	// هاي الميثود بتفحصلي مين الي فاز
	public String checkWinner() {
		List<List<Integer>> winningCombinations = Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6),
				Arrays.asList(7, 8, 9), Arrays.asList(1, 4, 7), Arrays.asList(2, 5, 8), Arrays.asList(3, 6, 9),
				Arrays.asList(1, 5, 9), Arrays.asList(3, 5, 7));

		for (List<Integer> combination : winningCombinations) {
			if (PLayer.containsAll(combination)) {
				showMessage1("Player One Wins");
				scheduleReset();
				return "Player One Wins";
			} else if (Computer.containsAll(combination)) {
				showMessage1("Player Two Wins");
				scheduleReset();
				return "Player Two Wins";
			}
		}

		if (PLayer.size() + Computer.size() == 9) {
			showMessage1("It's a Draw");
			scheduleReset();
			return "It's a Draw";
		}

		return "";
	}

	private void showMessage1(String message) {
		// SwingUtilities.invokeLater is used to run the JOptionPane on the Event
		// Dispatch Thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JOptionPane.showMessageDialog(null, message);
			}
		});
	}

	private void scheduleReset() {
		new java.util.Timer().schedule(new java.util.TimerTask() {
			@Override
			public void run() {
				javax.swing.SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						resetBoard();
					}
				});
			}
		}, 1000// Delay in milliseconds
		);
	}

	private void showMessage(String message) {
		// You can use a simple JOptionPane or a custom JavaFX dialog
		JOptionPane.showMessageDialog(null, message);
	}

	private void closeGameWindow() {
	    // Calculate the winner of the series
	    int playerWins = Integer.parseInt(winsField.getText());
	    int computerWins = Integer.parseInt(lossesField.getText());
	    String seriesWinner;

	    if (playerWins > computerWins) {
	        seriesWinner = "Player";
	    } else if (computerWins > playerWins) {
	        seriesWinner = "Computer";
	    } else {
	        seriesWinner = "It's a Draw";
	    }

	    // Construct the final message
	    String finalMessage = String.format("Final Results:\n\nPlayer Wins: %d\nComputer Wins: %d\n\nSeries Winner: %s",
	                                        playerWins, computerWins, seriesWinner);

	    // Customize JOptionPane to make it larger and more detailed
	    JTextArea textArea = new JTextArea(finalMessage);
	    textArea.setEditable(false);

	    JScrollPane scrollPane = new JScrollPane(textArea);  
	    scrollPane.setPreferredSize(new Dimension(350, 150));

	    JOptionPane.showMessageDialog(null, scrollPane, "Game Statistics", JOptionPane.INFORMATION_MESSAGE);

	    // Close the game window
	    Stage stage = (Stage) numRounds.getScene().getWindow();
	    stage.close();
	}

}