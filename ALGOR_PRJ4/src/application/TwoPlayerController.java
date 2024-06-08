package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TwoPlayerController {

	private Stage stage;
	private Scene scene;
	private Parent root;
	boolean player_turn;
	Stage primaryStage = new Stage();

	ArrayList<Integer> PLayerOne = new ArrayList<>();
	ArrayList<Integer> PLayerTwo = new ArrayList<>();
	private int totalRounds = 0;
	private int playerOneWins = 0;
	private int playerOneLosses = 0;
	private int playerTwoWins = 0;
	private int playerTwoLosses = 0;
	private int draws = 0;

	private TextField roundsField;
	private TextField playerOneWinsField;
	private TextField playerOneLossesField;
	private TextField playerTwoWinsField;
	private TextField playerTwoLossesField;
	
	private TextField DrawField;

	
	Label textfeild = new Label();
	Label textFirs = new Label();
	Label textSeco = new Label();

	@FXML
	private TextField NamePlayerOne;

	@FXML
	private TextField NamePlayerTwo;

	@FXML
	private RadioButton PlayerOne;

	@FXML
	private ToggleGroup HowPlay;

	@FXML
	private RadioButton PlayerTwo;

	@FXML
	private Button backbtn;

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
		if (NamePlayerOne.getText() != "" && NamePlayerTwo.getText() != ""
				&& (!PlayerOne.isSelected() || !PlayerTwo.isSelected())) {

			GridPane Bordroot = new GridPane();
			Bordroot.setHgap(10);
			Bordroot.setVgap(10);
			GridPane pane = new GridPane();
			pane.setHgap(3);
			pane.setVgap(3);
		    Bordroot.setStyle("-fx-background-color: #EEE2DE;"); // Adjust the hex code for different shades of green
		    pane.setStyle("-fx-background-color: #EEE2DE;"); // Adjust the hex code for different shades of green

			roundsField = new TextField("0");
			playerOneWinsField = new TextField("0");
			playerOneLossesField = new TextField("0");
			playerTwoWinsField = new TextField("0");
			playerTwoLossesField = new TextField("0");
			DrawField = new TextField("0");
			 TextField[] textFields = {roundsField, playerOneWinsField, playerOneLossesField, playerTwoWinsField, playerTwoLossesField, DrawField};
			    for (TextField textField : textFields) {
			        textField.setEditable(false);
			        textField.setAlignment(Pos.CENTER); // Center the text
			        // Set the style for text fields
			        textField.setStyle("-fx-background-color: #A9A9A9; " +
			                           "-fx-text-fill: white; " +
			                           "-fx-border-radius: 10; " +
			                           "-fx-background-radius: 10;");
			    }


			roundsField.setEditable(false);
			playerOneWinsField.setEditable(false);
			playerOneLossesField.setEditable(false);
			playerTwoWinsField.setEditable(false);
			playerTwoLossesField.setEditable(false);
			DrawField.setEditable(false);


			GridPane pane1 = new GridPane();
			pane1.setHgap(2);
			pane1.setVgap(2);
			pane1.setStyle("-fx-background-color: #EEE2DE;"); // Adjust the hex code for different shades of green

			GridPane pane2 = new GridPane();
			pane2.setHgap(4);
			pane2.setVgap(4);
			pane2.setStyle("-fx-background-color:#EEE2DE;"); // Adjust the hex code for different shades of green

			GridPane pane3 = new GridPane();
			pane3.setHgap(1);
			pane3.setVgap(1);
			pane3.setStyle("-fx-background-color: #EEE2DE;"); // Adjust the hex code for different shades of green

			Button[] map = new Button[9];
			for (int i = 0; i < map.length; i++) {
		        map[i] = new Button();
		        map[i].setPrefSize(90, 90);
		        map[i].setFont(Font.font("Times New Roman", FontWeight.BOLD, 34));
		        // Set the button background to black and text to white for contrast
		        map[i].setStyle("-fx-background-color: #FF9130; " +
		                        "-fx-text-fill: white; " +
		                        "-fx-border-radius: 15; " +
		                        "-fx-background-radius: 15;");
			    map[i].setFont(Font.font("Times New Roman", 34));
			    map[i].setId(Integer.toString(i + 1));  // Setting a numeric ID for each button
			}
			pane.addRow(2, map[0], map[1], map[2]);
			pane.addRow(3, map[3], map[4], map[5]);
			pane.addRow(4, map[6], map[7], map[8]);

			pane1.add(textFirs, 2, 6);
			pane1.add(new Label("Wins:"), 2, 7);
			pane1.add(playerOneWinsField, 2, 8);
			pane1.add(new Label("Losses:"), 2, 9);
			pane1.add(playerOneLossesField, 2, 10);

			pane2.add(textSeco, 2, 6);
			pane2.add(new Label("Wins:"), 2, 7);
			pane2.add(playerTwoWinsField, 2, 8);
			pane2.add(new Label("Losses:"), 2, 9);
			pane2.add(playerTwoLossesField, 2, 10);
			
			pane3.add(new Label("Rounds:"), 4, 2);
			pane3.add(roundsField, 4, 3);
			pane3.add(new Label("Draw:"), 4, 4);
			pane3.add(DrawField, 4, 5);

			Bordroot.add(pane, 5, 5);
			pane.setAlignment(Pos.BOTTOM_CENTER);

			Bordroot.add(pane1, 2, 5);
			pane.setAlignment(Pos.BASELINE_LEFT);

			Bordroot.add(pane2, 8, 5);
			pane.setAlignment(Pos.BASELINE_RIGHT);

			Bordroot.add(pane3, 2, 3);
			pane.setAlignment(Pos.BOTTOM_CENTER);

			textFirs.setText(NamePlayerOne.getText());
			textSeco.setText(NamePlayerTwo.getText());

			textfeild.setText("Player One Name : " + NamePlayerOne.getText() + "\n\n" + "Player Two Name : "
					+ NamePlayerTwo.getText());
			textfeild.setFont(Font.font("Times New Roman", 15));

			Bordroot.add(textfeild, 1, 2);


			
			Button exit = new Button("Exit");
		    exit.setPrefSize(80, 30);
		    exit.setFont(Font.font("Times New Roman", FontWeight.NORMAL, 14));
		    // Set the exit button style
		    exit.setStyle("-fx-background-color: #FF5B22; " +
		                  "-fx-text-fill: white; " +
		                  "-fx-border-radius: 15; " +
		                  "-fx-background-radius: 15;");
		    exit.setOnAction(e -> {
		        Stage stage = (Stage) exit.getScene().getWindow();
		        stage.close();
		    });

			Bordroot.add(exit, 2, 8);
			exit.setAlignment(Pos.BOTTOM_CENTER);
			exit.setPrefSize(80, 30);
			exit.setFont(Font.font("Times New Roman", 14));

			if (PlayerOne.isSelected()) {
				player_turn = true;
			} else if (PlayerTwo.isSelected()) {
				player_turn = false;
			}

			

			 EventHandler<ActionEvent> eventHandler = e -> {
		            Button btn = (Button) e.getSource();
		            if (btn.getText().isEmpty()) {
		                if (player_turn) {
		                    btn.setText("X");
		                    PLayerOne.add(Integer.parseInt(btn.getId()));
		                    player_turn = false;
		                } else {
		                    btn.setText("O");
		                    PLayerTwo.add(Integer.parseInt(btn.getId()));
		                    player_turn = true;
		                }

		                String result = checkWinner();
		                if (!result.isEmpty()) {
		                    updateStatistics(result);
		                    updateTextFields();
		                    if (result.equals("Player One Win") || result.equals("Player Two Win") || result.equals("CAT")) {
		                        showResult(result);
		                        resetGame(map);
		                    }
		                }
		            }
		        };

		        for (Button btn : map) {
		            btn.setOnAction(eventHandler);
		        }

			exit.setOnAction(e -> {
				Stage stage = (Stage) exit.getScene().getWindow();
				// do what you have to do
				stage.close();
			});
			Scene scene = new Scene(Bordroot, 900, 600);
			Stage primaryStage = new Stage();
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} else {
			JOptionPane.showMessageDialog(null, "Please Set All Data");

		}

	}

	public String checkWinner() {
		List TopRow = Arrays.asList(1, 2, 3);
		List midRow = Arrays.asList(4, 5, 6);
		List botRow = Arrays.asList(7, 8, 9);
		List leftCol = Arrays.asList(1, 4, 7);
		List midcol = Arrays.asList(2, 5, 8);
		List rightcol = Arrays.asList(3, 6, 9);
		List cross1 = Arrays.asList(1, 5, 9);
		List cross2 = Arrays.asList(7, 5, 3);

		List<List> Winning = new ArrayList<List>();
		Winning.add(TopRow);
		Winning.add(midRow);
		Winning.add(botRow);
		Winning.add(leftCol);
		Winning.add(midcol);
		Winning.add(rightcol);
		Winning.add(cross1);
		Winning.add(cross2);

		for (List l : Winning) {
			if (PLayerOne.containsAll(l)) {
				showMessage1(NamePlayerOne.getText() + " wins!");
				return "Player One Win";

			} else if (PLayerTwo.containsAll(l)) {
				showResult(NamePlayerTwo.getText() + " wins!");
				return "Player Two Win";

			} else if (PLayerOne.size() + PLayerTwo.size() == 9) {
				showResult("It's a draw!");
				return "CAT";

			}
		}
		return "";
	}
	private void showMessage1(String message) {
	    // SwingUtilities.invokeLater is used to run the JOptionPane on the Event Dispatch Thread
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	            JOptionPane.showMessageDialog(null, message);
	        }
	    });
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
			// do what you have to do
			stage.close();
		});

		Menu.setOnAction(e -> {
			try {
				root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
			} catch (IOException r) {
				// TODO Auto-generated catch block
				r.printStackTrace();
			}
			stage = new Stage();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			Stage stage = (Stage) Menu.getScene().getWindow();
			// do what you have to do
			stage.close();

			primaryStage.close();
		});

		Scene scene = new Scene(pane, 350, 120);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage primaryStage = new Stage();
		primaryStage.setScene(scene);
		primaryStage.show();

	}
	private void resetGame(Button[] map) {
	    for (Button btn : map) {
	        btn.setText("");
	    }
	    PLayerOne.clear();
	    PLayerTwo.clear();
	    player_turn = PlayerOne.isSelected();
	}

	private void updateStatistics(String result) {
	    totalRounds++;
	    switch (result) {
	        case "Player One Win":
	            playerOneWins++;
	            playerTwoLosses++;
	            break;
	        case "Player Two Win":
	            playerTwoWins++;
	            playerOneLosses++;
	            break;
	        case "CAT":
	            draws++;
	            break;
	    }
	}

	private void updateTextFields() {
	    roundsField.setText(String.valueOf(totalRounds));
	    playerOneWinsField.setText(String.valueOf(playerOneWins));
	    playerOneLossesField.setText(String.valueOf(playerOneLosses));
	    playerTwoWinsField.setText(String.valueOf(playerTwoWins));
	    playerTwoLossesField.setText(String.valueOf(playerTwoLosses));
	    DrawField.setText(String.valueOf(draws));
	}
	private void showResult(String result) {
	    // Determine the message to display based on the result
	    String message;
	    switch (result) {
	        case "Player One Win":
	            message = NamePlayerOne.getText() + " wins!";
	            break;
	        case "Player Two Win":
	            message = NamePlayerTwo.getText() + " wins!";
	            break;
	        case "CAT":
	            message = "It's a draw!";
	            break;
	        default:
	            message = "Unexpected result: " + result;
	    }

	}
}
