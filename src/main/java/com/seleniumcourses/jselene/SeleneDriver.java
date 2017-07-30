package com.seleniumcourses.jselene;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Optional;

/**
 * Created by yashaka on 3/30/17.
 */
public class SeleneDriver {

    public final SeleneWait wait;

    private final WebDriver webdriver;
    private final Capabilities capabilities;

    public SeleneDriver(WebDriver webdriver) {
        this(webdriver, new DesiredCapabilities() {{
            setCapability("baseUrl", "");
            setCapability("timeout", 4000L);
        }});
    }

    public SeleneDriver(WebDriver webdriver, Capabilities capabilities) {
        this.webdriver = webdriver;
        this.capabilities = capabilities;
        this.wait = new SeleneWait(this, (Long) capabilities.getCapability("timeout"));
    }

    public SeleneDriver open(String url) {
        this.webdriver.get(getCapability("baseUrl") + url);
        return this;
    }

    public SeleneElement element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private SeleneElement element(By by) {
        return new SeleneElement(by, this);
    }

    public SeleneCollection all(String cssSelector) {
        return this.all(By.cssSelector(cssSelector));
    }

    private SeleneCollection all(By by) {
        return new SeleneCollection(by, this);
    }

    public WebDriver webdriver() { return this.webdriver; }

    public void quit() {
        this.webdriver.quit();
    }

    public Object executeJavaScript(String script, Object... arguments) {
        return ((JavascriptExecutor) this.webdriver).executeScript(script, arguments);
    }

    private <T> T getCapability(String name) {
        return (T) capabilities.getCapability(name);
    }
}
