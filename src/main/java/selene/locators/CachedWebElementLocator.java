package selene.locators;


import selene.Locator;
import org.openqa.selenium.WebElement;

/**
 * Created by yashaka on 3/30/17.
 */
public class CachedWebElementLocator implements Locator<WebElement> {

    private final WebElement cached;
    private final String label;

    public CachedWebElementLocator(WebElement cached, String label) {
        this.cached = cached;
        this.label = label;
    }

    @Override
    public WebElement find() {
        return this.cached;
    }

    @Override
    public String description() {
        return this.label;
    }
}
