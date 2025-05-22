package edu.ntnu.idat2003.util;

import edu.ntnu.idat2003.exception.DataCreateException;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *  Class representing the CsvUtil methods.
 *  Includes method for interacting with the cvs file format.
 *  
 */
public class CsvUtil {

  /**
   *  Checks if file allready exist before creating new cvs file.
   *  Takes String representing file path and String representing
   *  data to be writen as parameters. Checks if file allready exist before 
   *  utilizing FileUtil methods to write to file.
   * 
   *  @param filePath String representing file path
   *  @param data String representing data to be writen
   *  @throws DataWriteException Exception handeling write failure 
   */
  public static void writeFile(String filePath, String data) throws DataWriteException {
    if (!FileUtil.fileExists(filePath)) {
      try {
        FileUtil.createFile(filePath);
      } catch (DataCreateException e) {
        throw new DataWriteException("Failed to create file at " + filePath, e);
      }
    }

    try (FileWriter writer = new FileWriter(filePath)) {
      writer.write(data);
    } catch (IOException e) {
      throw new DataWriteException("Failed to write data to " + filePath, e);
    }
  }

  /**
   *  Method for converting cvs file string data.
   *  Takes String representing filePath as parameter.
   *  Creates new FileReader for storing fetched data.
   *  
   * 
   *  @param filePath
   *  @return
   *  @throws DataReadException
   */
  public static String readFile(String filePath) throws DataReadException {
    StringBuilder data = new StringBuilder();
    try (FileReader reader = new FileReader(filePath)) {
      int character;
      while ((character = reader.read()) != -1) {
        data.append((char) character);
      }
    } catch (IOException e) {
      throw new DataReadException("Failed to read data from " + filePath, e);
    }

    return data.toString();
  }
}
