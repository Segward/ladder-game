package edu.ntnu.idat2003.exception;

public class DataCreateException extends Exception {
  public DataCreateException(String message) {
    super(message);
  }

  public DataCreateException(String message, Throwable cause) {
    super(message, cause);
  }
}