package com.seleniumcourses.jselene;

/**
 * Created by yashaka on 3/30/17.
 */
public interface Locator<T> {

    T find();
    String description();
}
