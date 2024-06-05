package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import dataclass.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utilities.DataValidator;
import utilities.DateConverter;
import utilities.Serializer;
import javafx.scene.control.DatePicker;

public class BmiTrackerViewController {
  @FXML
  private TextField firstNameTextField;
  @FXML
  private TextField lastNameTextField;
  @FXML
  private DatePicker dateOfBirthField;
  @FXML
  private TextField heightTextField;
  @FXML
  private TextField weightTextField;
  @FXML
  private Button chooseProfilePhotoButton;
  @FXML
  private ImageView chosenProfilePhotoImageView;
  @FXML
  private Button saveButton;
  @FXML
  private Button clearButton;
  @FXML
  private ListView personListView;;
  @FXML
  private Button viewDetailsButton;
  @FXML
  private Button editSelectedButton;
  @FXML
  private Button deleteButton;

  // global data field
  // data field to store the selected profile photo path
  private String chosenProfilePhotoPath = null;
  // arrayList to store the person objects
  private ArrayList<Person> personArrayList = null;
  // observable list to store Person objects for the UI
  private ObservableList<Person> observablePersonList = null;
  // global data field to check if the user has selected any existing person's
  // data
  private int indexOfChosenPerson = -1;

  // Event Listener on Button[#chooseProfilePhotoButton].onAction
  @FXML
  public void handleChooseProfilePhotoButtonClick(ActionEvent event) {
    System.out.println("ChooseProfilePhoto Button Clicked"); // for test
    FileChooser fileChooser = new FileChooser();
    Stage mainWindow = (Stage) this.chooseProfilePhotoButton.getScene().getWindow();
    File selectedFile = fileChooser.showOpenDialog(mainWindow);

    if (selectedFile != null) {
      this.chosenProfilePhotoPath = "file://" + selectedFile.toURI().getPath();
      Image chosenProfilePhoto = new Image(this.chosenProfilePhotoPath);
      this.chosenProfilePhotoImageView.setImage(chosenProfilePhoto);
    }
  }

  // Event Listener on Button[#saveButton].onAction
  @FXML
  public void handleSaveButtonClick(ActionEvent event) throws IOException {
    // gathering the data
    System.out.println("Save Button Clicked");
    String firstName = this.firstNameTextField.getText();
    String lastName = this.lastNameTextField.getText();
    LocalDate choosenDate = this.dateOfBirthField.getValue();
    String heightString = this.heightTextField.getText();
    String weightString = this.weightTextField.getText();
    String pathToProfilePicture = this.chosenProfilePhotoPath;

    /*
     * System.out.println(firstName + " " + lastName + " " + choosenDate + " " +
     * heightString + " " + weightString + " " + pathToProfilePicture);
     */

    // validate the data
    try {
      boolean validationSuccess = DataValidator.isValidData(firstName, lastName, choosenDate, weightString,
          heightString, pathToProfilePicture);

      // data conversion
      Date dateOfBirth = DateConverter.convertLocalDateToDate(choosenDate);
      double height = Double.parseDouble(heightString);
      double weight = Double.parseDouble(weightString);

      // create the Person object
      Person person = new Person(firstName, lastName, dateOfBirth, height, weight, pathToProfilePicture);

      if (this.indexOfChosenPerson == -1) {
        // user wants to add a fresh instance of data
        this.personArrayList.add(person);
        this.observablePersonList.add(person);
      } else {
        this.personArrayList.set(this.indexOfChosenPerson, person);
        this.observablePersonList.set(this.indexOfChosenPerson, person);
      }
      this.personListView.refresh();

      System.out.println(this.personArrayList);

      boolean isSerializationSuccessful = Serializer.serialize(Serializer.databasePath, this.personArrayList);
      System.out.println("Serialization is seccessful? " + isSerializationSuccessful);

      this.resetUI();
    } catch (Exception exception) {
      Stage parentStage = (Stage) this.saveButton.getScene().getWindow();
      ViewUtilities.showErrorMessageDialogueBox(exception.getMessage(), parentStage);
      // System.out.println(exception.getMessage()); // show error on console
    }
  }

  public void resetUI() {
    this.firstNameTextField.setText("");
    this.lastNameTextField.setText("");
    this.dateOfBirthField.setValue(null);
    this.heightTextField.setText("");
    this.weightTextField.setText("");
    this.chosenProfilePhotoPath = null;
    this.chosenProfilePhotoImageView.setImage(null);
    this.indexOfChosenPerson = -1;
  }

  // Event Listener on Button[#clearButton].onAction
  @FXML
  public void handleClearButtonClick(ActionEvent event) {
    this.resetUI();
  }

  // Event Listener on Button[#viewDetailsButton].onAction
  @FXML
  public void handleViewDetailsButtonClick(ActionEvent event) throws IOException {
    System.out.println("View details button clicked");

//    // 1st way
//    int indexOfChosenPerson = this.personListView.getSelectionModel().getSelectedIndex();
//    
    // 2nd way
    Person chosenPerson = (Person) this.personListView.getSelectionModel().getSelectedItem();

    // System.out.println(indexOfChosenPerson);

    if (chosenPerson != null) {
      System.out.println("Print to console " + chosenPerson); // for printing on console

      FXMLLoader loader = new FXMLLoader(getClass().getResource("BmiDetailView.fxml"));
      Pane root = loader.load();
      BmiDetailViewController bmiDetailViewController = loader.getController();

      bmiDetailViewController.transferPersonData(chosenPerson);

      Scene detailViewScene = new Scene(root);
      Stage currentStage = (Stage) this.viewDetailsButton.getScene().getWindow();
      currentStage.setScene(detailViewScene);
      currentStage.setTitle("BMI Detail View for: " + chosenPerson.getFirstName() + " " + chosenPerson.getLastName());
      currentStage.show();
    }
  }

  // Event Listener on Button[#editSelectedButton].onAction
  @FXML
  public void handleEditSelectedButtonClick(ActionEvent event) {
    this.indexOfChosenPerson = this.personListView.getSelectionModel().getSelectedIndex();

    if (this.indexOfChosenPerson != -1) {
      Person chosenPerson = this.personArrayList.get(this.indexOfChosenPerson);

      this.firstNameTextField.setText(chosenPerson.getFirstName());
      this.lastNameTextField.setText(chosenPerson.getLastName());
      this.dateOfBirthField.setValue(DateConverter.convertDateToLocalDate(chosenPerson.getDateOfBirth()));
      this.heightTextField.setText(String.valueOf(chosenPerson.getHeight()));
      this.weightTextField.setText(String.valueOf(chosenPerson.getWeight()));
      this.chosenProfilePhotoPath = chosenPerson.getPathToProfilePicture();
      this.chosenProfilePhotoImageView.setImage(new Image(this.chosenProfilePhotoPath));
    }
  }

//Event Listener on Button[#deleteButton].onAction
  @FXML
  public void handledeleteButtonClick(ActionEvent event) {

  }

  @FXML
  public void initialize() {
    System.out.println("This method is called automatically at" + " the start of the JavaFX program");

    // this.personArrayList = new ArrayList<>();
    this.personArrayList = Serializer.deserialize(Serializer.databasePath);

    if (this.personArrayList == null) {
      this.personArrayList = new ArrayList<>();
    }

    // for data structures to use in a program we have to use this in initialize
    // method its like must
    this.observablePersonList = FXCollections.observableArrayList(personArrayList);
    this.personListView.setItems(observablePersonList);
  }
}
