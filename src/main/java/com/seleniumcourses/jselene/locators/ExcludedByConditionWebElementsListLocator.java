package com.seleniumcourses.jselene.locators;

import com.seleniumcourses.jselene.Locator;
import com.seleniumcourses.jselene.SeleneCollection;
import com.seleniumcourses.jselene.conditions.ElementCondition;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

import static com.seleniumcourses.jselene.conditions.ConditionUtils.checkCondition;

/**
 * Created by yashaka on 3/30/17.
 */
public class ExcludedByConditionWebElementsListLocator implements Locator<List<WebElement>> {
    private final SeleneCollection collection;
    private final ElementCondition condition;

    public ExcludedByConditionWebElementsListLocator(SeleneCollection collection, ElementCondition condition) {
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
