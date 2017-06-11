package com.seleniumcourses.comparison;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class EmberTodoMvcSeleniumTryTest {
    // 14.000

    public static WebDriver driver = new FirefoxDriver();

    @AfterClass
    public static void driverTearDown() {
        driver.quit();
    }

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
    public  void complete_task() {

        driver.get("http://todomvc.com/examples/emberjs/");

        driver.findElement(byCssSelector("#new-todo")).sendKeys("a" + Keys.ENTER);
        driver.findElement(byCssSelector("#new-todo")).sendKeys("b" + Keys.ENTER);
        driver.findElement(byCssSelector("#new-todo")).sendKeys("c" + Keys.ENTER);
        assertEquals("a", driver.findElements(byCssSelector("#todo-list>li")).get(0).getText());
        assertEquals("b", driver.findElements(byCssSelector("#todo-list>li")).get(1).getText());
        assertEquals("c", driver.findElements(byCssSelector("#todo-list>li")).get(2).getText());
        assertEquals(3, driver.findElements(byCssSelector("#todo-list>li")).size());

        driver.findElements(byCssSelector("#todo-list>li")).get(1).findElement(byCssSelector(".toggle")).click();
        assertThat(driver.findElements(byCssSelector("#todo-list>li")).get(1).getAttribute("class"), containsString("completed"));
        assertThat(driver.findElements(byCssSelector("#todo-list>li")).get(0).getAttribute("class"), not(containsString("completed")));
        assertThat(driver.findElements(byCssSelector("#todo-list>li")).get(2).getAttribute("class"), not(containsString("completed")));
    }
}