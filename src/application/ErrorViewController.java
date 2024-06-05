package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ErrorViewController {
  @FXML
  private Label errorMessageLabel;
  @FXML
  private Button closeButton;

  // Event Listener on Button[#closeButton].onAction
  @FXML
  public void handleCloseButtonClicked(ActionEvent event) {
    Stage currentStage = (Stage) this.closeButton.getScene().getWindow();
    currentStage.close();
  }

  public void setErrorMessageLabel(String errorMessage) {
    this.errorMessageLabel.setText(errorMessage);
  }
}