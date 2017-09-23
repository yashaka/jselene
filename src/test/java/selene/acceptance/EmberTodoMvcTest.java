package selene.acceptance;

import org.junit.After;
import selene.Browser;
import selene.conditions.Have;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class EmberTodoMvcTest {

    public static WebDriver webdriver;
    public static Browser browser;

    static {
        Capabilities capabilities = new DesiredCapabilities() {{
            setCapability("selene.element.wait.timeout", 2000L);
        }};
        webdriver = new ChromeDriver();
        browser = new Browser(webdriver, capabilities);
    }

    @AfterClass
    public static void driverTearDown() {
        browser.quit();
    }

    @After
    public void tearDown() {
        browser.executeJavaScript("localStorage.clear();");
    }

    @Test
    public void completes_task() {
        browser.open("http://todomvc.com/examples/emberjs/");

        browser.element("#new-todo").sendKeys("a").pressEnter();
        browser.element("#new-todo").sendKeys("b").pressEnter();
        browser.element("#new-todo").sendKeys("c").pressEnter();
        browser.all("#todo-list>li").should(Have.exactTexts("a", "b", "c"));

        browser.all("#todo-list>li").elementBy(Have.exactText("b.")).element(".toggle").click();
        browser.all("#todo-list>li").filteredBy(Have.cssClass("completed")).should(Have.exactTexts("b"));
        browser.all("#todo-list>li").excludedBy(Have.cssClass("completed")).should(Have.exactTexts("a", "c"));
    }

    @Test
    public void edits_task() {
        browser.open("http://todomvc.com/examples/emberjs/");

        browser.element("#new-todo").sendKeys("a").pressEnter();
        browser.element("#new-todo").sendKeys("b").pressEnter();
        browser.element("#new-todo").sendKeys("c").pressEnter();
        browser.all("#todo-list>li").should(Have.exactTexts("a", "b", "c"));

        browser.all("#todo-list>li").elementBy(Have.exactText("c")).doubleClick();
        browser.all("#todo-list>li").elementBy(Have.cssClass("editing")).element(".edit")
                .sendKeys(" edited").pressEnter();
        browser.all("#todo-list>li").should(Have.exactTexts("a", "b", "c edited"));
    }
}