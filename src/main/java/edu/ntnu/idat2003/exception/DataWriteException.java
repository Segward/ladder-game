package edu.ntnu.idat2003.exception;

/**
 *  Exception class for writing data.
 */
public class DataWriteException extends Exception {

   /**
   *  Method for cathing data write exceptions.
   *  Infroms user of type of exception.
   * 
   *  @param message detail message
   */
  public DataWriteException(String message) {
    super(message);
  }

  /**
   *  Method for cathing data write exceptions.
   *  Infroms user of type of exception and cause.
   * 
   *  @param message detail message
   *  @param cause cause of exception, can be null
   */
  public DataWriteException(String message, Throwable cause) {
    super(message, cause);
  }
}