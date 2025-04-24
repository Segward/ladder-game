package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.service.GsonService;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;

public class PlayerRepo {
  private static final String path = "src/main/resources/player.json";
  
  public static HashSet<Player> getPlayers() {
    Type playerSetType = new TypeToken<HashSet<Player>>() {}.getType();
    HashSet<Player> players = GsonService.getObjects(path, playerSetType);
    return players != null ? players : new HashSet<>();
  }

  public static void savePlayers(HashSet<Player> players) {
    GsonService.saveObjects(players, path);
  }

  public static void addPlayer(Player player) {
    HashSet<Player> players = getPlayers();
    players.add(player);
    savePlayers(players);
  }

  public static void removePlayer(Player player) {
    HashSet<Player> players = getPlayers();
    players.remove(player);
    savePlayers(players);
  }
}
