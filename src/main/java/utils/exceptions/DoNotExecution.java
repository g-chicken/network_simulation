package utils.exceptions;

/** DoNotExecution express not to execute. */
public class DoNotExecution extends Exception {
  public DoNotExecution(String msg) {
    super(String.format("do not execution : %s", msg));
  }
}
