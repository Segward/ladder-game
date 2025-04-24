package edu.ntnu.idat2003.exception;

public class DataReadException extends Exception {
  public DataReadException(String message) {
    super(message);
  }

  public DataReadException(String message, Throwable cause) {
    super(message, cause);
  }
}