package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewUtilities {
  public static void showErrorMessageDialogueBox(String errorMessage, Stage parentStage) {

    try {
      FXMLLoader loader = new FXMLLoader(ViewUtilities.class.getResource("ErrorView.fxml"));
      Pane errorView = (Pane) loader.load();
      
      ErrorViewController errorViewController = loader.getController();
      errorViewController.setErrorMessageLabel(errorMessage);

      Stage errorStage = new Stage();
      Scene errorScene = new Scene(errorView);
      errorStage.setScene(errorScene);
      errorStage.initOwner(parentStage);
      errorStage.initModality(Modality.APPLICATION_MODAL);
      errorStage.setTitle("Error");
      errorStage.showAndWait();
    } catch (IOException exception) {
      System.out.println("Could not load the ErrorView.fxml file");
    }
  }
}
