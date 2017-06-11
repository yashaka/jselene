package com.seleniumcourses.jselene.locators;

import com.seleniumcourses.jselene.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by yashaka on 3/30/17.
 */
public class ByWebElementsListLocator implements Locator<List<WebElement>> {
    private final By by;
    private final WebDriver webdriver;

    public ByWebElementsListLocator(By by, WebDriver webdriver) {
        this.by = by;
        this.webdriver = webdriver;
    }

    @Override
    public List<WebElement> find() {
        return this.webdriver.findElements(by);
    }

    @Override
    public String description() {
        return this.by.toString();
    }
}
