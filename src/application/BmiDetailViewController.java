package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import dataclass.Person;
import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BmiDetailViewController {
  @FXML
  private ImageView profileImageView;
  @FXML
  private Label fullNameLabel;
  @FXML
  private Label ageLabel;
  @FXML
  private Label bmiLabel;
  @FXML
  private Label bmiStatusLabel;
  @FXML
  private Label weightLabel;
  @FXML
  private Label heightLabel;
  @FXML
  private Button closeButton;

  // Event Listener on Button[#closeButton].onAction
  @FXML
  public void handleCloseButtonClicked(ActionEvent event) throws IOException {
    System.out.println("Close Button on the view details scene is pressed"); // for printing on console

    FXMLLoader loader = new FXMLLoader(getClass().getResource("BmiTrackerView.fxml"));
    Pane root = loader.load();
    Scene trackerView = new Scene(root);
    Stage currentStage = (Stage) this.closeButton.getScene().getWindow();
    currentStage.setScene(trackerView);
    currentStage.setTitle("BMI Tracker App");
    currentStage.show();
  }

  public void transferPersonData(Person person) {
    System.out.println(person);

    String fullName = person.getFirstName() + " " + person.getLastName();
    int age = person.getAge();
    double bmi = person.getBMI();
    String bmiStatus = person.getBmiStatus();
    double height = person.getHeight();
    double weight = person.getWeight();
    String pathToProfilePicture = person.getPathToProfilePicture();

    this.fullNameLabel.setText(fullName);
    this.ageLabel.setText(String.valueOf(age)); // as it is integer we have to cast it to string
    this.bmiLabel.setText(String.valueOf(bmi));
    this.bmiStatusLabel.setText(bmiStatus);
    this.heightLabel.setText(String.valueOf(height));
    this.heightLabel.setText(String.valueOf(weight));
    this.profileImageView.setImage(new Image(pathToProfilePicture));

  }
}
