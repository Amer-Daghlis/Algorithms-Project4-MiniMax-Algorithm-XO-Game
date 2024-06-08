package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class SampleController {

	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	private RadioButton Single;

	@FXML
	private ToggleGroup Choice;

	@FXML
	private RadioButton Multi;

	@FXML
	private RadioButton MinMax;

	@FXML
	private Button gobtn;

	@FXML
	private Button exitbtn;

	@FXML
	void Exit(ActionEvent event) {
		// get a handle to the stage
		Stage stage = (Stage) exitbtn.getScene().getWindow();
		// do what you have to do
		stage.close();

	}

	@FXML
	void gotogamebtn(ActionEvent event) {
		if (Single.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("EasySample.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage = new Stage();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			Stage stage = (Stage) gobtn.getScene().getWindow();
			// do what you have to do
			stage.close();

		} else if (Multi.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("TwoPlayerSample.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage = new Stage();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			Stage stage = (Stage) gobtn.getScene().getWindow();
			// do what you have to do
			stage.close();

		} else if (MinMax.isSelected()) {
			try {
				root = FXMLLoader.load(getClass().getResource("MinMax.fxml"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			stage = new Stage();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

			Stage stage = (Stage) gobtn.getScene().getWindow();
			// do what you have to do
			stage.close();

		}

	}

}
