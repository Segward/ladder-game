package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idat2003.models.Player;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class DataStorage {
  private static boolean fileExists(String filePath) {
    return new File(filePath).exists();
  }

  private static void createFile(String filePath) {
    System.out.println("Creating file: " + filePath);
    try {
      File file = new File(filePath);
      file.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void saveData(Player object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      createFile(filePath);
    }
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(object, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Player loadData(String filePath) {
    Gson gson = new Gson();
    if (!fileExists(filePath)) {
      return null;
    }
    try (FileReader reader = new FileReader(filePath)) {
      return gson.fromJson(reader, Player.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}

