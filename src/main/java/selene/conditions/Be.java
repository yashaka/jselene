package selene.conditions;

import selene.Element;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import selene.ElementCondition;

/**
 * Created by yashaka on 3/30/17.
 */
public class Be {

    public static ElementCondition visible = new ElementCondition() {
        @Override
        public WebElement apply(Element element) {
            WebElement webElement = element.getActualWebElement();

            if (webElement.isDisplayed()) {
                return webElement;
            } else {
                throw new ElementNotVisibleException(String.format(
                        "\nElement: {%s} should be visible, but is not:("));
            }
        }

        @Override
        public String toString() {
            return "visible";
        }
    };

    /*
     * todo: add more conditions
     */
}
