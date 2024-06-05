package exceptions;

public class NullException extends Exception {

  private static final long serialVersionUID = 8893031773410100065L;

  public NullException() {
    super();
  }

  public NullException(String message) {
    super(message);
  }
}
