package edu.ntnu.idat2003.exception;

/**
 *  Exeption class for creating data.
 */
public class DataCreateException extends Exception {
  public DataCreateException(String message) {
    super(message);
  }

  public DataCreateException(String message, Throwable cause) {
    super(message, cause);
  }
}