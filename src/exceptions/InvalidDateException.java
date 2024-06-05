package exceptions;

public class InvalidDateException extends Exception {

  private static final long serialVersionUID = -5084526013652975263L;

  public InvalidDateException() {
    super();
  }

  public InvalidDateException(String message) {
    super(message);
  }
}
