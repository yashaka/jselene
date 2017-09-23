package selene.comparison;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;

public class EmberTodoMvcSelenideTryTest {

    @Before
    public void before() {
        Configuration.browser = "chrome";
    }

    @After
    public void tearDown() {
        Selenide.executeJavaScript("localStorage.clear();");
    }

    @Test
    public void complete_task() {

        open("http://todomvc.com/examples/emberjs/");

        $("#new-todo").setValue("a").pressEnter();
        $("#new-todo").setValue("b").pressEnter();
        $("#new-todo").setValue("c").pressEnter();
        $$("#todo-list>li").shouldHave(exactTexts("a", "b", "c"));

        $$("#todo-list>li").findBy(exactText("b")).find(".toggle").click();
        $$("#todo-list>li").filterBy(cssClass("completed")).shouldHave(exactTexts("b"));
        $$("#todo-list>li").excludeWith(cssClass("completed")).shouldHave(exactTexts("a", "c"));
    }
}