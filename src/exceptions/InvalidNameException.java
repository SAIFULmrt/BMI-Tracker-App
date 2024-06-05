package exceptions;

public class InvalidNameException extends Exception {

  private static final long serialVersionUID = -5307349316103676672L;

  public InvalidNameException() {
    super();
  }

  public InvalidNameException(String message) {
    super(message);
  }
}
