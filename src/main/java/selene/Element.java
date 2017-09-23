package selene;

import selene.locators.ByWebElementLocator;
import selene.locators.InnerByWebElementLocator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.function.Consumer;
import java.util.function.Function;


/**
 * Created by yashaka on 3/30/17.
 */
public class Element {

    private final Locator<WebElement> locator;
    private final DriverContext context;

    Element(Locator<WebElement> locator, DriverContext seleneDriver) {
        this.locator = locator;
        this.context = seleneDriver;
    }

    public Element(By by, DriverContext context) {
        this(new ByWebElementLocator(context.driver(), by), context);
    }

    public WebElement getActualWebElement() {
        return this.locator.find();
    }

    @Override
    public String toString(){
        return this.locator.description();
    }

    /*
     * Asserts
     */

    // todo: tbd

    /*
     * "Abstract Element" Actions
     */

    public Element sendKeys(final CharSequence...  keys) {
        this.waitCommand(
                it -> it.getActualWebElement().sendKeys(keys),
                this + " is not available for sendKeys");
        return this;
    }

    public Element pressEnter() {
        this.waitCommand(
                it -> it.getActualWebElement().sendKeys(Keys.ENTER),
                this + " is not available for press enter");
        return this;
    }

    public Element hover() {
        this.waitCommand(
                it -> this.actions().moveToElement(it.getActualWebElement()).perform(),
                this + " is not available for hover");
        return this;
    }

    public Element click() {
        this.waitCommand(
                it -> it.getActualWebElement().click(),
                this + " is not available for click");
        return this;
    }

    public Element doubleClick() {
        this.waitCommand(
                it -> this.actions().doubleClick(it.getActualWebElement()).perform(),
                this + " is not available for double-click");
        return this;
    }

    /*
     * Abstract Element "queries"
     */

    public String outerHtml() {
        return this.waitQuery(
                it -> it.getActualWebElement().getAttribute("outerHTML"),
                this + "is not available for getAttribute(\"outerHTML\")");
    }

    // todo: add more "general" actions that might be relevant for "any element"

    /*
     * Relative search
     */

    public Element element(String cssSelector) {
        return this.element(By.cssSelector(cssSelector));
    }

    private Element element(By by) {
        return new Element(new InnerByWebElementLocator(this, by), context);
    }

    // todo: add Element#all

    /*
     * Private
     */

    private void waitCommand(Consumer<Element> command, String errorMessage) {
        new Wait<>(this, this.context.getCapability("selene.element.wait.timeout", 4000L))
                .command(command, errorMessage);
    }

    private <R> R waitQuery(Function<Element, R> query, String errorMessage) {
        return new Wait<>(this, this.context.getCapability("selene.element.wait.timeout", 4000L))
                .query(query, errorMessage);
    }

    private Actions actions() {
        return new Actions(this.context.driver());
    }
}
