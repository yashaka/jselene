package com.seleniumcourses.jselene.locators;

import com.google.common.base.Function;
import com.seleniumcourses.jselene.ConditionNotMatchedException;
import com.seleniumcourses.jselene.Locator;
import com.seleniumcourses.jselene.SeleneCollection;
import com.seleniumcourses.jselene.SeleneElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Optional;

import static com.seleniumcourses.jselene.conditions.ConditionUtils.checkCondition;

/**
 * Created by yashaka on 3/30/17.
 */
public class InnerByWebElementLocator implements Locator<WebElement> {
    private final SeleneElement element;
    private final By by;

    public InnerByWebElementLocator(SeleneElement element, By by) {
        this.element = element;
        this.by = by;
    }

    @Override
    public WebElement find() {
        return this.element.getActualWebElement().findElement(this.by);
    }

    @Override
    public String description() {
        return String.format("{%s}.find(%s)", this.element, this.by);
    }
}
