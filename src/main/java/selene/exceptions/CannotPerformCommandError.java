package selene.exceptions;

/**
 * Created by yashaka on 3/30/17.
 */
public class CannotPerformCommandError extends SeleneError {
  public CannotPerformCommandError() {
    super();
  }

  public CannotPerformCommandError(String message) {
    super(message);
  }

  public CannotPerformCommandError(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotPerformCommandError(Throwable cause) {
    super(cause);
  }
}
