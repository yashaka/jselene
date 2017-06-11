package com.seleniumcourses.jselene.locators;

import com.seleniumcourses.jselene.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by yashaka on 3/30/17.
 */
public class ByWebElementLocator implements Locator<WebElement> {
    private final By by;
    private final WebDriver webdriver;

    public ByWebElementLocator(WebDriver webdriver, By by) {
        this.webdriver = webdriver;
        this.by = by;
    }

    public WebElement find() {
        return this.webdriver.findElement(by);
    }

    public String description() {
        return this.by.toString();
    }
}
