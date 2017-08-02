package com.seleniumcourses.jselene.locators;

import com.seleniumcourses.jselene.Locator;
import com.seleniumcourses.jselene.SeleneDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by yashaka on 3/30/17.
 */
public class ByWebElementLocator implements Locator<WebElement> {
    private final By by;
    private final SeleneDriver seleneDriver;

    public ByWebElementLocator(SeleneDriver seleneDriver, By by) {
        this.seleneDriver = seleneDriver;
        this.by = by;
    }

    public WebElement find() {
        return this.seleneDriver.webdriver().findElement(by);
    }

    public String description() {
        return this.by.toString();
    }
}
