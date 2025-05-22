package edu.ntnu.idat2003.exception;

/**
 *  Exeption class for creating data.
 */
public class DataCreateException extends Exception {
  
  /**
   *  Method for cathing data create exceptions.
   *  Infroms user of type of exception.
   * 
   *  @param message detail message
   */
  public DataCreateException(String message) {
    super(message);
  }

  /**
   *  Method for cathing data create exceptions.
   *  Infroms user of type of exception and cause.
   * 
   *  @param message detail message
   *  @param cause cause of exception, can be null
   */
  public DataCreateException(String message, Throwable cause) {
    super(message, cause);
  }
}