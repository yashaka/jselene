package selene.locators;

import selene.Locator;
import selene.ElementsCollection;
import selene.ElementCondition;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static selene.conditions.ConditionUtils.checkCondition;

/**
 * Created by yashaka on 3/30/17.
 */
public class ExcludedByConditionWebElementsListLocator implements Locator<List<WebElement>> {
    private final ElementsCollection collection;
    private final ElementCondition condition;

    public ExcludedByConditionWebElementsListLocator(ElementsCollection collection, ElementCondition condition) {
        this.collection = collection;
        this.condition = condition;
    }

    @Override
    public List<WebElement> find() {
        return this.collection.stream()
                .filter(it -> !checkCondition(it, this.condition))
                .map(it -> it.getActualWebElement())
                .collect(Collectors.toList());
    }

    @Override
    public String description() {
        return String.format("{%s}.excludedBy(%s)", this.collection, this.condition);
    }
}
