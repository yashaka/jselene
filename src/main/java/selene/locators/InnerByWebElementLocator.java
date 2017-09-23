package selene.locators;

import selene.Element;
import selene.Locator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * Created by yashaka on 3/30/17.
 */
public class InnerByWebElementLocator implements Locator<WebElement> {
    private final Element element;
    private final By by;

    public InnerByWebElementLocator(Element element, By by) {
        this.element = element;
        this.by = by;
    }

    @Override
    public WebElement find() {
        return this.element.getActualWebElement().findElement(this.by);
    }

    @Override
    public String description() {
        return String.format("{%s}.find(%s)", this.element, this.by);
    }
}
