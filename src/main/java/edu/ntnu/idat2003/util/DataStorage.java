package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idat2003.models.Player;

public class DataStorage {

  public static void saveData(Player object, String filename) {
    GsonBuilder builder = new GsonBuilder();
    builder.setPrettyPrinting();
    Gson gson = builder.create();
    String json = gson.toJson(object);
    System.out.println(json);
  }

  public static Player loadData(String filename) {
    Gson gson = new Gson();
    return gson.fromJson(filename, Player.class);
  }
}
