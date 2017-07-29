package com.seleniumcourses.jselene.conditions;

import com.seleniumcourses.jselene.SeleneElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

/**
 * Created by yashaka on 3/30/17.
 */
public class Be {

    public static Function<SeleneElement, SeleneElement> visible() {
        return new Function<SeleneElement, SeleneElement>() {
            @Override
            public SeleneElement apply(SeleneElement seleneElement) {
                WebElement webElement = seleneElement.getActualWebElement();

                if (webElement.isDisplayed()) {
                    return seleneElement;
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
    }
}
