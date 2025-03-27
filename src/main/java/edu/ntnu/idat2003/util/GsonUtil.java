package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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

  public static <T> void deleteObject(T object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      return;
    }

    HashSet<T> objects = getObjects(filePath);
    if (objects == null) {
      return;
    }

    if (!objects.contains(object)) {
      return;
    }

    objects.remove(object);
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(objects, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> void saveObject(T object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      createFile(filePath);
    }

    HashSet<T> objects = getObjects(filePath);
    if (objects == null) {
      objects = new HashSet<>();
    }

    objects.add(object);
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(objects, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static <T> HashSet<T> getObjects(String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      return null;
    }

    HashSet<T> objects = null;
    try (FileReader reader = new FileReader(filePath)) {
      Type type = new TypeToken<HashSet<T>>() {}.getType();
      objects = gson.fromJson(reader, type);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return objects;
  }
}
