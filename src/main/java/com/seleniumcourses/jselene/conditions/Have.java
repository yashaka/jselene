package com.seleniumcourses.jselene.conditions;

import com.seleniumcourses.jselene.SeleneCollection;
import com.seleniumcourses.jselene.SeleneElement;
import com.seleniumcourses.jselene.exceptions.ConditionNotMatchedException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by yashaka on 3/30/17.
 */
public class Have {

    public static Function<SeleneCollection, SeleneCollection> exactTexts(String... texts) {
        return new Function<SeleneCollection, SeleneCollection>() {
            @Override
            public SeleneCollection apply(SeleneCollection seleneCollection) {

                List<String> actualTexts = Collections.emptyList();
                List<String> expectedTexts = Arrays.asList(texts);
                try {
                    List<WebElement> webelements = seleneCollection.getActualWebElements();
                    actualTexts = webelements.stream().map(it -> it.getText()).collect(Collectors.toList()); // todo add exceptions-sage method and pass map into it
                    if (actualTexts.equals(expectedTexts))
                        return seleneCollection;
                } catch (WebDriverException e) {/*NOP*/}

                throw new ConditionNotMatchedException(String.format(
                        "\nFailed to assert exact texts for elements: %s\n\texpected: %s\n\t  actual: %s",
                        seleneCollection, expectedTexts, actualTexts
                ));
            }

            @Override
            public String toString() {
                return String.format("exactTexts(%s)", texts);
            }
        };
    }

    public static Function<SeleneElement, SeleneElement> exactText(String text) {
        return new Function<SeleneElement, SeleneElement>() {
            @Override
            public SeleneElement apply(SeleneElement seleneElement) {
                String actualText = "";

                try {
                    WebElement webElement = seleneElement.getActualWebElement();
                    actualText = webElement.getText();
                    if (actualText.equals(text))
                        return seleneElement;
                } catch (WebDriverException e) {/*NOP*/}

                throw new ConditionNotMatchedException(String.format(
                        "\nFailed to assert exact text for element: %s\n\texpected: %s\n\t  actual: %s", seleneElement, text, actualText));
            }

            @Override
            public String toString() {
                return String.format("exactText(%s)", text);
            }
        };
    }

    public static Function<SeleneElement, SeleneElement> cssClass(String expected) {
        return new Function<SeleneElement, SeleneElement>() {
            @Override
            public SeleneElement apply(SeleneElement seleneElement) {
                String actualClassAttributeValue = "";

                try {
                    WebElement webElement = seleneElement.getActualWebElement();
                    actualClassAttributeValue = webElement.getAttribute("class");
                    if (hasClass(actualClassAttributeValue, expected))
                        return seleneElement;
                } catch (WebDriverException e) {/*NOP*/}

                throw new ConditionNotMatchedException(String.format(
                        "\nFailed to assert cssClass for element: %s\n\texpected: %s\n\t  actual: %s", seleneElement, expected, actualClassAttributeValue));
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
}
