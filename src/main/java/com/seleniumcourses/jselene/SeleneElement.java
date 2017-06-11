package com.seleniumcourses.jselene;

import com.seleniumcourses.jselene.conditions.Be;
import com.seleniumcourses.jselene.locators.ByWebElementLocator;
import com.seleniumcourses.jselene.locators.InnerByWebElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Consumer;

/**
 * Created by yashaka on 3/30/17.
 */
public class SeleneElement {

    private final Locator<WebElement> locator;

    public SeleneElement(By by, WebDriver webdriver) {
        this(new ByWebElementLocator(webdriver, by));
    }

    public SeleneElement(Locator<WebElement> locator) {
        this.locator = locator;
    }

    public WebElement getActualWebElement() {
        return this.locator.find();
    }

    public SeleneElement setValue(String value) {
        this.execute(it -> {
            it.clear();
            it.sendKeys(value);
        });
        return this;
    }

    public SeleneElement pressEnter() {
        return this.sendKeys(Keys.ENTER);
    }

    public SeleneElement sendKeys(final CharSequence...  keys) {
        this.execute(it -> it.sendKeys(keys) );
        return this;
    }

    private void execute(Consumer<WebElement> command) {
        try {
            command.accept(this.getActualWebElement());
        } catch (Exception e) {
            command.accept(
                    Wait.until(this, Be.visible())
            );
        }
    }

    @Override
    public String toString(){
        return this.locator.description();
    }

    public SeleneElement element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private SeleneElement element(By by) {
        return new SeleneElement(new InnerByWebElementLocator(this, by));
    }

    public SeleneElement click() {
        this.execute(it -> it.click());
        return this;
    }
}
