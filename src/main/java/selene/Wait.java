package selene;

import selene.exceptions.CannotPerformCommandError;
import selene.exceptions.CannotPerformQueryError;
import selene.exceptions.SeleneError;
import selene.exceptions.TimeoutExpiredError;
import org.openqa.selenium.WebDriverException;

import java.util.function.Consumer;
import java.util.function.Function;

public class Wait<T> {
    /*
     * This Wait class is pretty General, except the fact that is uses WebDriver and Selene exceptions
     * todo: consider make this error types configurable...
     */

    private final T entity;
    private final long timeout;

    public Wait(T context, long timeout) {
        this.entity = context;
        this.timeout = timeout;
    }

    public <R> R until(Function<T, R> condition) {
        long endTime = System.currentTimeMillis() + timeout;
        // todo: pollingInterval? to poll or not to poll with interval? probably not... what might be wrong then? :)
        while (true) {
            try {
                return condition.apply(entity);
            } catch (SeleneError error) {
                if (System.currentTimeMillis() > endTime) {
                    String timeoutMessage = String.format(" (tried for %d ms):\n%s ", timeout, error.getMessage());
                    throw new TimeoutExpiredError(timeoutMessage);
                }
            }
        }
    }

    /*
     * todo: consider until method overload: until(condition, errorMessage)
     */

    public void command(Consumer<T> consumer, String errorMessage) {
        until(it -> {
            try {
                consumer.accept(it);
                return null;  // we have to return something, even if are not supposed to according to "command" context
            } catch (WebDriverException e) {
                throw new CannotPerformCommandError(errorMessage +
                        "\n\nreason: " + e.getMessage());
            }
        });
    }
        /*
         * todo: consider overloading command with command(Consumer)
         * then passing "" as errorMessage
         * and in the overloaded command method: either checking it for empty and passing e original message
         * or passing it all the time concatenated to errorMessage as a cause
         */

    public <R> R query(Function<T, R> query, String errorMessage) {
        return until(it -> {
            try {
                return query.apply(it);
            } catch (WebDriverException e) {
                throw new CannotPerformQueryError(errorMessage +
                        "\n\nreason: " + e.getMessage());
            }
        });
    }
        /*
         * todo: do we actually need it? maybe we just need an "until" version with errorMessage?
         */

}
