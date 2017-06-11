package com.seleniumcourses.jselene.conditions;

import com.google.common.base.Function;
import com.seleniumcourses.jselene.ConditionNotMatchedException;
import com.seleniumcourses.jselene.SeleneCollection;
import com.seleniumcourses.jselene.SeleneElement;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yashaka on 3/30/17.
 */
public class Have {

    public static Function<SeleneCollection,List<WebElement>> exactTexts(String... texts){
        return new Function<SeleneCollection, List<WebElement>>() {
            @Override
            public List<WebElement> apply(SeleneCollection seleneCollection) {
                List<WebElement> webelements = seleneCollection.getActualWebElements();
                List<String> expectedTexts = Arrays.asList(texts);
                List<String> actualTexts = webelements.stream().map(it -> it.getText()).collect(Collectors.toList());

                if (actualTexts.equals(expectedTexts)) {
                    return webelements;
                } else {
                    throw new ConditionNotMatchedException(String.format(
                            "\nFailed to assert exact texts for elements: %s\n\texpected: %s\n\t  actual: %s",
                            seleneCollection, expectedTexts, actualTexts
                    ));
                }
            }

            @Override
            public String toString() {
                return String.format("exactTexts(%s)", texts);
            }
        };
    }

    public static Function<SeleneElement, WebElement> exactText(String text) {
        return new Function<SeleneElement, WebElement>() {
            @Override
            public WebElement apply(SeleneElement seleneElement) {
                WebElement webElement = seleneElement.getActualWebElement();
                String actualText = webElement.getText();

                if (actualText.equals(text)) {
                    return webElement;
                } else {
                    throw new ConditionNotMatchedException(String.format(
                            "\nFailed to assert exact text for element: %s\n\texpected: %s\n\t  actual: %s", seleneElement, text, actualText));
                }
            }

            @Override
            public String toString() {
                return String.format("exactText(%s)", text);
            }
        };
    }

    public static Function<SeleneElement, WebElement> cssClass(String expected) {
        return new Function<SeleneElement, WebElement>() {
            @Override
            public WebElement apply(SeleneElement seleneElement) {
                WebElement webElement = seleneElement.getActualWebElement();
                String actualClassAttributeValue = webElement.getAttribute("class");

                if (hasClass(actualClassAttributeValue, expected)) {
                    return webElement;
                } else {
                    throw new ConditionNotMatchedException(String.format(
                            "\nFailed to assert cssClass for element: %s\n\texpected: %s\n\t  actual: %s", seleneElement, expected, actualClassAttributeValue));
                }
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
