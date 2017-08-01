package com.seleniumcourses.jselene.conditions;

import com.seleniumcourses.jselene.SeleneElement;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

public interface ElementCondition extends Function<SeleneElement, WebElement> {
}
