package com.seleniumcourses.jselene.exceptions;

public class SeleneError extends AssertionError {
  public SeleneError() {
    super();
  }

  public SeleneError(String message) {
    super(message);
  }

  public SeleneError(String message, Throwable cause) {
    super(message, cause);
  }

  public SeleneError(Throwable cause) {
    super(cause);
  }
}
