package edu.ntnu.idat2003.repo;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;

public class PlayerRepo {
  private static final String path = "data/player.json";

  public static HashSet<Player> getPlayers() {
    Type playerSetType = new TypeToken<HashSet<Player>>() {}.getType();
    try {
      return GsonUtil.getObjects(path, playerSetType);
    } catch (Exception e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }

  public static void savePlayers(HashSet<Player> players) {
    try {
      GsonUtil.saveObjects(players, path);
    } catch (Exception e) {
      e.printStackTrace();
    }
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
