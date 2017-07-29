package com.seleniumcourses.jselene;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.Optional;

/**
 * Created by yashaka on 3/30/17.
 */
public class SeleneDriver {

    public final SeleneWait wait;

    private final WebDriver webdriver;
    private final String baseUrl;
    private static final long DEFAULT_TIMEOUT = 4000L;

    public SeleneDriver(WebDriver webdriver) {
        this(webdriver, "", DEFAULT_TIMEOUT);
    }

    public SeleneDriver(WebDriver webdriver, Capabilities capabilities) {
        this(
                webdriver,
                Optional.of((String) capabilities.getCapability("baseUrl")).orElse(""),
                Optional.of((Long) capabilities.getCapability("timeout")).orElse(DEFAULT_TIMEOUT)
        );
    }

    public SeleneDriver(WebDriver webdriver, String baseUrl, long timeout) {
        this.webdriver = webdriver;
        this.baseUrl = baseUrl;
        this.wait = new SeleneWait(this, timeout);
    }

    public SeleneDriver open(String relativeUrl) {
        this.webdriver.get(baseUrl + relativeUrl);
        return this;
    }

    public SeleneElement element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private SeleneElement element(By by) {
        return new SeleneElement(by, this.webdriver, this.wait);
    }

    public SeleneCollection all(String cssSelector) {
        return this.all(By.cssSelector(cssSelector));
    }

    private SeleneCollection all(By by) {
        return new SeleneCollection(by, this.webdriver, this.wait);
    }

    public void quit() {
        this.webdriver.quit();
    }

    public Object executeJavaScript(String script, Object... arguments) {
        return ((JavascriptExecutor) this.webdriver).executeScript(script, arguments);
    }
}
