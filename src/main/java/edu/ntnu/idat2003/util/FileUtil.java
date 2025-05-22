package edu.ntnu.idat2003.util;

import edu.ntnu.idat2003.exception.DataCreateException;
import java.io.File;

/**
 * Utility class for file operations. Provides methods to check if a file exists and to create a new
 * file.
 */
public class FileUtil {

  /**
   * Checks if a file exists at the specified path.
   *
   * @param filePath the path to the file
   * @return true if the file exists, false otherwise
   */
  public static boolean fileExists(String filePath) {
    File file = new File(filePath);
    return file.exists() && !file.isDirectory();
  }

  /**
   * Creates a new file at the specified path. If the file already exists, it will not be created
   * again.
   *
   * @param filePath the path to the file to be created
   * @throws DataCreateException if an error occurs while creating the file
   */
  public static void createFile(String filePath) throws DataCreateException {
    try {
      File file = new File(filePath);
      file.createNewFile();
    } catch (Exception e) {
      throw new DataCreateException("Failed to create file at " + filePath, e);
    }
  }
}
