package com.seleniumcourses.jselene;

import com.seleniumcourses.jselene.conditions.Have;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by yashaka on 3/30/17.
 */
public class FilteredByConditionCollectionShouldTest {

    public static WebDriver webdriver;
    public static SeleneDriver browser;
    public static HTMLBuilder htmlBuilder;

    static {
        webdriver = new ChromeDriver();
        browser = new SeleneDriver(webdriver);
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
