package com.seleniumcourses.jselene;

import com.seleniumcourses.jselene.exceptions.CannotPerformCommandError;
import com.seleniumcourses.jselene.exceptions.SeleneError;
import com.seleniumcourses.jselene.exceptions.TimeoutExpiredError;
import org.openqa.selenium.WebDriverException;

import java.util.function.Consumer;
import java.util.function.Function;

public class Wait<T> {

    private final T entity;
    private final long timeout;

    public Wait(T context, long timeout) {
        this.entity = context;
        this.timeout = timeout;
    }

    public void until(Consumer<T> consumer, String errorMessage) {
        until(function -> {
            try {
                consumer.accept(entity);
                return entity;
            } catch (WebDriverException e) {
                throw new CannotPerformCommandError(errorMessage);
            }
        });
    }

    public <R> R until(Function<T, R> condition) {
        long endTime = System.currentTimeMillis() + timeout;

        while (true) {
            try {
                return condition.apply(entity);
            } catch (SeleneError error) {
                if (System.currentTimeMillis() > endTime) {
                    String timeoutMessage = String.format(" (tried for %d ms):\n%s ", timeout, error.getMessage());
                    throw new TimeoutExpiredError(timeoutMessage);
                }
            }
        }
    }

}
