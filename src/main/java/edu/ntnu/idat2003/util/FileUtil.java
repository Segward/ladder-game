package edu.ntnu.idat2003.util;

import edu.ntnu.idat2003.exception.DataCreateException;
import java.io.File;

public class FileUtil {

  public static boolean fileExists(String filePath) {
    File file = new File(filePath);
    return file.exists() && !file.isDirectory();
  }

  public static void createFile(String filePath) throws DataCreateException {
    try {
      File file = new File(filePath);
      file.createNewFile();
    } catch (Exception e) {
      throw new DataCreateException("Failed to create file at " + filePath, e);
    }
  }
}
