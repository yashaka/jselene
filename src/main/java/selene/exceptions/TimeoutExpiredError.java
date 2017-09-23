package selene.exceptions;

public class TimeoutExpiredError extends SeleneError {
  public TimeoutExpiredError() {
  }

  public TimeoutExpiredError(String message) {
    super(message);
  }

  public TimeoutExpiredError(String message, Throwable cause) {
    super(message, cause);
  }

  public TimeoutExpiredError(Throwable cause) {
    super(cause);
  }
}
