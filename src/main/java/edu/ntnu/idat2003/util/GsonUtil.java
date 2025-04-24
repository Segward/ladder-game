package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idat2003.exception.DataCreateException;
import edu.ntnu.idat2003.exception.DataReadException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.HashSet;

public class GsonUtil {

  private static boolean fileExists(String filePath) {
    File file = new File(filePath);
    return file.exists() && !file.isDirectory();
  }

  private static Gson createGson() {
    return new GsonBuilder().setPrettyPrinting().create();
  }

  private static void createFile(String filePath) throws DataCreateException {
    try {
      File file = new File(filePath);
      file.createNewFile();
    } catch (Exception e) {
      throw new DataCreateException("Failed to create file at " + filePath, e);
    }
  }

  public static <T> void saveObjects(HashSet<T> objects, String filePath) throws DataReadException {
    Gson gson = createGson();
    if (!fileExists(filePath)) {
      try {
        createFile(filePath);
      } catch (DataCreateException e) {
        throw new DataReadException("Failed to create file at " + filePath, e);
      }
    }

    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(objects, writer);
      writer.flush();
    } catch (Exception e) {
      throw new DataReadException("Failed to save data to " + filePath, e);
    }
  }

  public static <T> HashSet<T> getObjects(String filePath, Type type) throws DataReadException {
    Gson gson = createGson();
    HashSet<T> objects = new HashSet<>();
    if (!fileExists(filePath)) {
      return objects;
    }

    try (FileReader reader = new FileReader(filePath)) {
      objects = gson.fromJson(reader, type);
    } catch (Exception e) {
      throw new DataReadException("Failed to read data from " + filePath, e);
    }

    if (objects == null) {
      objects = new HashSet<>();
    }

    return objects;
  }

  public static <T> void deleteObjects(String filePath) {
    File file = new File(filePath);
    if (file.exists()) {
      file.delete();
    }
  }
}
