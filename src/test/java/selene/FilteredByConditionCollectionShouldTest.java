package selene;

import selene.conditions.Have;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by yashaka on 3/30/17.
 */
public class FilteredByConditionCollectionShouldTest {

    public static WebDriver webdriver;
    public static Browser browser;
    public static HTMLBuilder htmlBuilder;

    static {
        webdriver = new ChromeDriver();
        browser = new Browser(webdriver);
        htmlBuilder =  new HTMLBuilder(browser);
    }

    @AfterClass
    public static void driverTearDown() {
        browser.quit();
    }

    @Test
    public void shouldWaitsForDynamicFilteredCollection() {

        htmlBuilder.given.openedPageWithBody(
                "<ul>Hello to:",
                "<li class='friend'>Bob</li>",
                "<li class='enemy'>Joe</li>",
                "</ul>"
        );
        htmlBuilder.when.withBodyTimedOut(3000,
                "<ul>Hello to:",
                "<li class='friend'>Bob</li>",
                "<li class='enemy'>Dick</li>",
                "<li class='friend'>Lucy</li>",
                "</ul>"
                );

        browser.all("li").filteredBy(Have.cssClass("friend")).should(Have.exactTexts("Bob", "Lucy"));
    }
}
