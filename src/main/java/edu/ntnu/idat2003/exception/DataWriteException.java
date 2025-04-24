package edu.ntnu.idat2003.exception;

public class DataWriteException extends Exception {
  public DataWriteException(String message) {
    super(message);
  }

  public DataWriteException(String message, Throwable cause) {
    super(message, cause);
  }
}