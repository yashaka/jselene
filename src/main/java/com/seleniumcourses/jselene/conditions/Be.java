package com.seleniumcourses.jselene.conditions;

import com.seleniumcourses.jselene.SeleneElement;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;

/**
 * Created by yashaka on 3/30/17.
 */
public class Be {

    public static ElementCondition visible = new ElementCondition() {
        @Override
        public WebElement apply(SeleneElement seleneElement) {
            WebElement webElement = seleneElement.getActualWebElement();

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

}
