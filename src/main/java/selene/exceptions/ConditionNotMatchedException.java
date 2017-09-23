package selene.exceptions;

/**
 * Created by yashaka on 3/30/17.
 */
public class ConditionNotMatchedException extends SeleneError {
  public ConditionNotMatchedException() {
    super();
  }

  public ConditionNotMatchedException(String message) {
    super(message);
  }

  public ConditionNotMatchedException(String message, Throwable cause) {
    super(message, cause);
  }

  public ConditionNotMatchedException(Throwable cause) {
    super(cause);
  }
}
