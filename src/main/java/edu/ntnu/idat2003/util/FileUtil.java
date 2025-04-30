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

  public static void createDirectory(String dirPath) throws DataCreateException {
    try {
      File dir = new File(dirPath);
      if (!dir.exists()) {
        dir.mkdirs();
      }
    } catch (Exception e) {
      throw new DataCreateException("Failed to create directory at " + dirPath, e);
    }
  }

  public static void deleteFile(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }
}
