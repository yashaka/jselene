package com.seleniumcourses.jselene;

import com.seleniumcourses.jselene.locators.ByWebElementLocator;
import com.seleniumcourses.jselene.locators.InnerByWebElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


/**
 * Created by yashaka on 3/30/17.
 */
public class SeleneElement {

    private final Locator<WebElement> locator;
    private final SeleneDriver seleneDriver;

    public SeleneElement(By by, SeleneDriver seleneDriver) {
        this(new ByWebElementLocator(seleneDriver, by), seleneDriver);
    }

    SeleneElement(Locator<WebElement> locator, SeleneDriver seleneDriver) {
        this.locator = locator;
        this.seleneDriver = seleneDriver;
    }

    public WebElement getActualWebElement() {
        return this.locator.find();
    }

    public SeleneElement setValue(String value) {
        seleneDriver.wait(this).until(it -> {
                it.getActualWebElement().clear();
                it.getActualWebElement().sendKeys(value);
        }, this + " is not available for clear and sendKeys");
        return this;
    }

    public SeleneElement pressEnter() {
        seleneDriver.wait(this).until(it -> it.getActualWebElement().sendKeys(Keys.ENTER), this + " is not available for press enter");
        return this;
    }

    public SeleneElement sendKeys(final CharSequence...  keys) {
        seleneDriver.wait(this).until(it -> it.getActualWebElement().sendKeys(keys), this + " is not available for sendKeys");
        return this;
    }

    public SeleneElement click() {
        seleneDriver.wait(this).until(it -> it.getActualWebElement().click(), this + " is not available for click");
        return this;
    }

    @Override
    public String toString(){
        return this.locator.description();
    }

    public SeleneElement element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private SeleneElement element(By by) {
        return new SeleneElement(new InnerByWebElementLocator(this, by), seleneDriver);
    }
}
