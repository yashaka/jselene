package com.seleniumcourses.jselene.locators;

import com.seleniumcourses.jselene.Locator;
import com.seleniumcourses.jselene.SeleneDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by yashaka on 3/30/17.
 */
public class ByWebElementsListLocator implements Locator<List<WebElement>> {
    private final By by;
    private final SeleneDriver seleneDriver;

    public ByWebElementsListLocator(By by, SeleneDriver seleneDriver) {
        this.by = by;
        this.seleneDriver = seleneDriver;
    }

    @Override
    public List<WebElement> find() {
        return this.seleneDriver.webdriver().findElements(by);
    }

    @Override
    public String description() {
        return this.by.toString();
    }
}
