package selene.comparison.speed;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selene.comparison.selenium_extensions.BaseTest;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class EmberTodoMvcSeleniumTryTest extends BaseTest {
    // 15.500

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[20][0]);
    }

    public EmberTodoMvcSeleniumTryTest() {
    }

    @After
    public void tearDown() {
        ((JavascriptExecutor) driver).executeScript("localStorage.clear();");
    }

    @Test
    public  void completes_task() {

        driver.get("http://todomvc.com/examples/emberjs/");

        $("#new-todo").sendKeys("a" + Keys.ENTER);
        $("#new-todo").sendKeys("b" + Keys.ENTER);
        $("#new-todo").sendKeys("c" + Keys.ENTER);
        assertEquals("a", $$("#todo-list>li").get(0).getText());
        assertEquals("b", $$("#todo-list>li").get(1).getText());
        assertEquals("c", $$("#todo-list>li").get(2).getText());
        assertEquals(3, $$("#todo-list>li").size());

        $$("#todo-list>li").get(1).findElement(byCssSelector(".toggle")).click();
        assertThat($$("#todo-list>li").get(1).getAttribute("class"), containsString("completed"));
        assertThat($$("#todo-list>li").get(0).getAttribute("class"), not(containsString("completed")));
        assertThat($$("#todo-list>li").get(2).getAttribute("class"), not(containsString("completed")));
    }
}