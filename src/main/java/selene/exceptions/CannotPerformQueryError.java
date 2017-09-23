package selene.exceptions;

/**
 * Created by yashaka on 3/30/17.
 */
public class CannotPerformQueryError extends SeleneError {
  public CannotPerformQueryError() {
    super();
  }

  public CannotPerformQueryError(String message) {
    super(message);
  }

  public CannotPerformQueryError(String message, Throwable cause) {
    super(message, cause);
  }

  public CannotPerformQueryError(Throwable cause) {
    super(cause);
  }
}
