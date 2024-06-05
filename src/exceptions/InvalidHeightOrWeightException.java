package exceptions;

public class InvalidHeightOrWeightException extends Exception {

  private static final long serialVersionUID = 4768995166959349756L;

  public InvalidHeightOrWeightException() {
    super();
  }

  public InvalidHeightOrWeightException(String message) {
    super(message);
  }
}
