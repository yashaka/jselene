package com.seleniumcourses.jselene;

import org.openqa.selenium.WebDriverException;

/**
 * Created by yashaka on 3/30/17.
 */
public class ConditionNotMatchedException extends WebDriverException {
    public ConditionNotMatchedException(String message) {
        super(message);
    }
}
