package utils.exceptions;

/**
 * InvalidArguments is invalid argument exception.
 */
public class InvalidArguments extends Exception {
  public InvalidArguments(String msg) {
    super(String.format("Invalid Argument : %s", msg));
  }
}
