package edu.ntnu.idat2003.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.ntnu.idat2003.models.Player;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.HashSet;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class DataStorage {
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

  public static void deletePlayer(Player object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      return;
    }

    HashSet<Player> players = getPlayers(filePath);
    if (players == null) {
      return;
    }

    if (!players.contains(object)) {
      System.out.println("Player not found in file");
      return;
    }

    players.remove(object);
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(players, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  public static void savePlayer(Player object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      createFile(filePath);
    }

    HashSet<Player> players = getPlayers(filePath);
    if (players == null) {
      players = new HashSet<>();
    }

    players.add(object);
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(players, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static HashSet<Player> getPlayers(String filePath) {
    Gson gson = new Gson();
    if (!fileExists(filePath)) {
      return null;
    }
    
    HashSet<Player> players = null;
    try (FileReader reader = new FileReader(filePath)) {
      Type playerSetType = new TypeToken<HashSet<Player>>(){}.getType();
      players = gson.fromJson(reader, playerSetType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return players;
  }
}

