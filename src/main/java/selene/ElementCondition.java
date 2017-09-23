package selene;

import selene.Element;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public interface ElementCondition extends Function<Element, WebElement> {
}
