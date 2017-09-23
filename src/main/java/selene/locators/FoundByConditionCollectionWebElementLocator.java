package selene.locators;

import selene.Locator;
import selene.ElementsCollection;
import selene.Element;
import selene.ElementCondition;
import selene.conditions.Be;
import selene.exceptions.ConditionNotMatchedException;
import org.openqa.selenium.WebElement;

import java.util.Optional;
import java.util.stream.Collectors;

import static selene.conditions.ConditionUtils.checkCondition;

/**
 * Created by yashaka on 3/30/17.
 */
public class FoundByConditionCollectionWebElementLocator implements Locator<WebElement> {
    private final ElementsCollection collection;
    private final ElementCondition condition;

    public FoundByConditionCollectionWebElementLocator(ElementsCollection collection, ElementCondition condition) {
        this.collection = collection;
        this.condition = condition;
    }

    @Override
    public WebElement find() {
        Optional<Element> result = this.collection.stream()
                .filter(it -> checkCondition(it, this.condition))
                .findFirst();
        if (result.isPresent()) {
            return result.get().getActualWebElement();
        } else {
            throw new ConditionNotMatchedException(String.format(
                    "\nFailed to find element by condition inside collection: %s" +
                            "\n\tactual collection texts: %s" +
                            "\n\texpected element by: %s" +
                            "\n" +
                            "\n\tactual collection html: %s"
                    , this.collection
                    , this.collection.filteredBy(Be.visible).stream()
                            .map(Element::getActualWebElement)
                            .map(WebElement::getText)
                            .collect(Collectors.toList())
                    , this.condition
                    , this.collection.stream()
                            .map(Element::getActualWebElement)
                            .map(element -> element.getAttribute("outerHTML"))
                            .map(str -> "\n\n" + str + "\n")
                            .collect(Collectors.toList())));
        }
    }

    @Override
    public String description() {
        return String.format("{%s}.findBy(%s)", this.collection, this.condition);
    }
}
