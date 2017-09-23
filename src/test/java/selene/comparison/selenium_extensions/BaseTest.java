package selene.comparison.selenium_extensions;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class BaseTest {

    public static WebDriver driver = new ChromeDriver();

    @AfterClass
    public static void driverTearDown() {
        driver.quit();
    }

    public WebElement $(String cssSelector) {
        return driver.findElement(By.cssSelector(cssSelector));
    }

    public List<WebElement> $$(String cssSelector) {
        return driver.findElements(By.cssSelector(cssSelector));
    }
}
