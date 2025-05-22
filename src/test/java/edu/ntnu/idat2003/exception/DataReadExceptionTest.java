package edu.ntnu.idat2003.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DataReadExceptionTest {

  @Test
  @DisplayName("Test default constructor")
  public void testMessageConstructor() {
    String message = "Test error message";
    DataReadException exception = new DataReadException(message);
    assertEquals(message, exception.getMessage());
    assertNull(exception.getCause());
  }

  @Test
  @DisplayName("Test constructor with message and cause")
  public void testMessageAndCauseConstructor() {
    String message = "Test error with cause";
    Throwable cause = new RuntimeException("Cause");
    DataReadException exception = new DataReadException(message, cause);
    assertEquals(message, exception.getMessage());
    assertEquals(cause, exception.getCause());
  }
}