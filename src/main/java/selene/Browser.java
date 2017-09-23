package selene;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * Created by yashaka on 3/30/17.
 */
public class Browser implements DriverContext {

    private final WebDriver driver;
    private final Capabilities capabilities;

    /*
     * todo: add constructor that takes WebDriverSource with idea to extend selene abilities
     * to create driver automatically by specified for example via selene.browser.name capability
     */

    public Browser(WebDriver driver, Capabilities capabilities) {
        this.driver = driver;
        this.capabilities = capabilities;
    }

    public Browser(WebDriver driver) {
        this(driver, new DesiredCapabilities());
    }

    public Browser open(String url) {
        this.driver.get(getCapability("selene.browser.baseUrl", "") + url);
            // todo: rename to Selene.Browser.open.url.base ?
        return this;
    }

    public Element element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private Element element(By by) {
        return new Element(by, this);
    }

    public ElementsCollection all(String cssSelector) {
        return this.all(By.cssSelector(cssSelector));
    }

    private ElementsCollection all(By by) {
        return new ElementsCollection(by, this);
    }

    public void quit() {
        this.driver.quit();
    }

    public void close() {
        this.driver.close();
    }

    public <T> Wait<T> wait(T context) {  // todo: context? or entity?
        return new Wait<>(context, getCapability("selene.browser.wait.timeout", 4000L));
    }

    public Object executeJavaScript(String script, Object... arguments) {
        return ((JavascriptExecutor) this.driver).executeScript(script, arguments);
    }

    @Override
    public String toString() {
        return String.format("Browser(%s).\n\t%s", driver.getClass().getSimpleName(), capabilities);
    }

    /*
     * DriverContext implementation
     */

    public <T> T getCapability(String name, T defaultValue) {
        Object cap = capabilities.getCapability(name);
        if (null == cap) {
            return defaultValue;
        }
        return (T) cap;
    }

    public WebDriver driver() {
        return this.driver;
    }
}
