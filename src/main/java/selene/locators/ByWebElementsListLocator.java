package selene.locators;

import selene.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by yashaka on 3/30/17.
 */
public class ByWebElementsListLocator implements Locator<List<WebElement>> {
    private final By by;
    private final SearchContext searchContext;

    public ByWebElementsListLocator(By by, SearchContext searchContext) {
        this.by = by;
        this.searchContext = searchContext;
    }

    @Override
    public List<WebElement> find() {
        return this.searchContext.findElements(by);
    }

    @Override
    public String description() {
        return this.by.toString();
    }
}
