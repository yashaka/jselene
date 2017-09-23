package selene.comparison.speed;

import selene.Browser;
import selene.conditions.Have;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;

@RunWith(Parameterized.class)
public class EmberTodoMvcJSeleneTryTest {
    // 18.000


    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[20][0]);
//        return Arrays.asList(new Object[1][0]);
    }

    public EmberTodoMvcJSeleneTryTest() {
    }

    @After
    public void tearDown() {
        browser.executeJavaScript("localStorage.clear();");
    }

    public static WebDriver webdriver;
    public static Browser browser;

    static {
        webdriver = new ChromeDriver();
        browser = new Browser(webdriver);
    }

    @AfterClass
    public static void driverTearDown() {
        browser.quit();
    }

    @Test
    public  void complete_task() {
        browser.open("http://todomvc.com/examples/emberjs/");

        browser.element("#new-todo").sendKeys("a").pressEnter();
        browser.element("#new-todo").sendKeys("b").pressEnter();
        browser.element("#new-todo").sendKeys("c").pressEnter();
        browser.all("#todo-list>li").should(Have.exactTexts("a", "b", "c"));

        browser.all("#todo-list>li").elementBy(Have.exactText("b")).element(".toggle").click();
        browser.all("#todo-list>li").filteredBy(Have.cssClass("completed")).should(Have.exactTexts("b"));
        browser.all("#todo-list>li").excludedBy(Have.cssClass("completed")).should(Have.exactTexts("a", "c"));
    }
}