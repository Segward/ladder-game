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
import edu.ntnu.idat2003.models.Board;
import java.util.HashMap;

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

  public static Board getBoard(String id, String filePath) {
    HashMap<String, Board> boards = getBoards(filePath);
    if (boards == null) {
      return null;
    }

    return boards.get(id);
  }

  public static void deleteBoard(Board object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      return;
    }

    HashMap<String, Board> boards = getBoards(filePath);
    if (boards == null) {
      return;
    }

    if (!boards.containsKey(object.getName())) {
      System.out.println("Board not found in file");
      return;
    }

    boards.remove(object.getName());
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(boards, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void saveBoard(Board object, String filePath) {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    if (!fileExists(filePath)) {
      createFile(filePath);
    }

    HashMap<String, Board> boards = getBoards(filePath);
    if (boards == null) {
      boards = new HashMap<>();
    }

    boards.put(object.getName(), object);
    try (FileWriter writer = new FileWriter(filePath)) {
      gson.toJson(boards, writer);
      writer.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static HashMap<String, Board> getBoards(String filePath) {
    Gson gson = new Gson();
    if (!fileExists(filePath)) {
      return null;
    }
    
    HashMap<String, Board> boards = null;
    try (FileReader reader = new FileReader(filePath)) {
      Type boardMapType = new TypeToken<HashMap<String, Board>>(){}.getType();
      boards = gson.fromJson(reader, boardMapType);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    return boards;
  }
}

