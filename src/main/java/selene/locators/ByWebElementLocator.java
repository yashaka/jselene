package selene.locators;

import selene.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

/**
 * Created by yashaka on 3/30/17.
 */
public class ByWebElementLocator implements Locator<WebElement> {
    private final By by;
    private final SearchContext searchContext;

    public ByWebElementLocator(SearchContext searchContext, By by) {
        this.searchContext = searchContext;
        this.by = by;
    }

    public WebElement find() {
        return this.searchContext.findElement(by);
    }

    public String description() {
        return this.by.toString();
    }
}
