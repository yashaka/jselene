package com.seleniumcourses.jselene.locators;

import com.google.common.base.Function;
import com.seleniumcourses.jselene.ConditionNotMatchedException;
import com.seleniumcourses.jselene.Locator;
import com.seleniumcourses.jselene.SeleneCollection;
import com.seleniumcourses.jselene.SeleneElement;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.seleniumcourses.jselene.conditions.ConditionUtils.checkCondition;

/**
 * Created by yashaka on 3/30/17.
 */
public class FilteredByConditionWebElementsListLocator implements Locator<List<WebElement>> {
    private final SeleneCollection collection;
    private final Function<SeleneElement, WebElement> condition;

    public FilteredByConditionWebElementsListLocator(SeleneCollection collection, Function<SeleneElement, WebElement> condition) {
        this.collection = collection;
        this.condition = condition;
    }

    @Override
    public List<WebElement> find() {
        return this.collection.stream()
                .filter(it -> checkCondition(it, this.condition))
                .map(it -> it.getActualWebElement())
                .collect(Collectors.toList());
    }

    @Override
    public String description() {
        return String.format("{%s}.filterBy(%s)", this.collection, this.condition);
    }
}