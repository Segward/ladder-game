package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;

public class GsonUtil {

  private static boolean fileExists(String filePath) {
    File file = new File(filePath);
    return file.exists() && !file.isDirectory();
  }

  private static void createFile(String filePath) {
    try {
      File file = new File(filePath);
      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> void saveObjects(HashSet<T> objects, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      createFile(filePath);
    }

    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(objects, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> HashSet<T> getObjects(String filePath, Type type) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    HashSet<T> objects = new HashSet<>();
    if (!fileExists(filePath)) {
      return objects;
    }

    try (FileReader reader = new FileReader(filePath)) {
      objects = gson.fromJson(reader, type);
    } catch (Exception e) {
      e.printStackTrace();
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
