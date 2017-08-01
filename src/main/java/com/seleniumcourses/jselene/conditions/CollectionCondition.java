package com.seleniumcourses.jselene.conditions;

import com.seleniumcourses.jselene.SeleneCollection;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.function.Function;

public interface CollectionCondition extends Function<SeleneCollection, List<WebElement>> {
}
