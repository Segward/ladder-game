package edu.ntnu.idat2003.exception;

/**
 *  Exception class for Reading data.
 */
public class DataReadException extends Exception {
  public DataReadException(String message) {
    super(message);
  }

  public DataReadException(String message, Throwable cause) {
    super(message, cause);
  }
}