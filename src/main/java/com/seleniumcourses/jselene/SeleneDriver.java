package com.seleniumcourses.jselene;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Created by yashaka on 3/30/17.
 */
public class SeleneDriver {

    private final WebDriver webdriver;

    public SeleneDriver(WebDriver webdriver) {
        this.webdriver = webdriver;
    }


    public SeleneDriver open_url(String url) {
        this.webdriver.get(url);
        return this;
    }

    public SeleneElement element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private SeleneElement element(By by) {
        return new SeleneElement(by, this.webdriver);
    }

    public void quit() {
        this.webdriver.quit();
    }

    public Object executeJavaScript(String script) {
        return ((JavascriptExecutor) this.webdriver).executeScript(script);
    }

    public SeleneCollection all(String cssSelector) {
        return this.all(By.cssSelector(cssSelector));
    }

    private SeleneCollection all(By by) {
        return new SeleneCollection(by, this.webdriver);
    }
}
