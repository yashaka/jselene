package com.seleniumcourses.jselene;

import com.seleniumcourses.jselene.exceptions.SeleneError;
import com.seleniumcourses.jselene.exceptions.TimeoutExpiredError;

import java.util.function.Function;

public class SeleneWait {

  private final SeleneDriver driver;
  private final long timeout;

  public SeleneWait(SeleneDriver driver, long timeout) {
    this.driver = driver;
    this.timeout = timeout;
  }

  public <T> T until(Function<SeleneDriver, T> condition) {
    return until(driver, condition);
  }

  public <F, S> S until(F entity, Function<F, S> condition) {
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
