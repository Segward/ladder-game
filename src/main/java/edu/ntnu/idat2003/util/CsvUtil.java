package edu.ntnu.idat2003.util;

import edu.ntnu.idat2003.exception.DataCreateException;
import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CsvUtil {

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
