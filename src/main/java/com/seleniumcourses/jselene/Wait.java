package com.seleniumcourses.jselene;

import com.google.common.base.Function;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
//import java.util.function.Function;

import static java.util.Arrays.asList;

/**
 * Created by yashaka on 3/30/17.
 */
public class Wait {

    public static <P, R> R until(P entity, Function<P, R> condition) {
        return new FluentWait<P>(entity)
                .withTimeout(4, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(WebDriverException.class)
                .until(condition);
    }
}
