package edu.ntnu.idat2003.exception;

/**
 *  Exception class for Reading data.
 */
public class DataReadException extends Exception {
  /**
   *  Method for cathing data read exceptions.
   *  Infroms user of type of exception.
   * 
   *  @param message detail message
   */
  public DataReadException(String message) {
    super(message);
  }

  /**
   *  Method for cathing data read exceptions.
   *  Infroms user of type of exception and cause.
   * 
   *  @param message detail message
   *  @param cause cause of exception, can be null
   */
  public DataReadException(String message, Throwable cause) {
    super(message, cause);
  }
}