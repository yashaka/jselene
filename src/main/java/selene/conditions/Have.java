package selene.conditions;

import selene.CollectionCondition;
import selene.Element;
import selene.ElementCondition;
import selene.ElementsCollection;
import selene.exceptions.ConditionNotMatchedException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yashaka on 3/30/17.
 */
public class Have {

    public static CollectionCondition exactTexts(String... texts) {
        return new CollectionCondition() {
            @Override
            public List<WebElement> apply(ElementsCollection elementsCollection) {

                List<String> actualTexts = Collections.emptyList();
                List<String> expectedTexts = Arrays.asList(texts);

                try {
                    List<WebElement> webelements = elementsCollection.getActualWebElements();
                    actualTexts = webelements.stream()
                            .map(it -> it.getText())
                            .collect(Collectors.toList()); // todo: add exceptions-sage method and pass map into it
                    if (actualTexts.equals(expectedTexts))
                        return webelements;
                } catch (WebDriverException e) {/*NOP*/}

                throw new ConditionNotMatchedException(String.format(
                        "\nFailed to assert exact texts for elements: %s" +
                                "\n\texpected: %s" +
                                "\n\t  actual: %s",
                        elementsCollection, expectedTexts, actualTexts
                ));
            }

            @Override
            public String toString() {
                return String.format("exactTexts(%s)", texts);
            }
        };
    }

    public static ElementCondition exactText(String text) {
        return new ElementCondition() {
            @Override
            public WebElement apply(Element element) {
                String actualText = "";

                try {
                    WebElement webElement = element.getActualWebElement();
                    actualText = webElement.getText();
                    if (actualText.equals(text))
                        return webElement;
                } catch (WebDriverException e) {/*NOP*/}

                throw new ConditionNotMatchedException(String.format(
                        "\nFailed to assert exact text for element: %s" +
                                "\n\texpected: %s" +
                                "\n\t  actual: %s"
                        , element, text, actualText));
            }

            @Override
            public String toString() {
                return String.format("exactText(%s)", text);
            }
        };
    }

    public static ElementCondition cssClass(String expected) {
        return new ElementCondition() {
            @Override
            public WebElement apply(Element element) {
                String actualClassAttributeValue = "";

                try {
                    WebElement webElement = element.getActualWebElement();
                    actualClassAttributeValue = webElement.getAttribute("class");
                    if (hasClass(actualClassAttributeValue, expected))
                        return webElement;
                } catch (WebDriverException e) {/*NOP*/}

                throw new ConditionNotMatchedException(String.format(
                        "\nFailed to assert cssClass for element: %s" +
                                "\n\texpected: %s" +
                                "\n\t  actual: %s",
                        element, expected, actualClassAttributeValue));
            }

            @Override
            public String toString() {
                return String.format("cssClass(%s)", expected);
            }
        };
    }

    public static boolean hasClass(String classes, String cssClass) {
        return classes != null && contains(classes.split(" "), cssClass);
    }

    private static <T> boolean contains(T[] objects, T object) {
        for (T object1 : objects) {
            if (object.equals(object1)) {
                return true;
            }
        }
        return false;
    }

    /*
     * todo: add more conditions
     */
}
