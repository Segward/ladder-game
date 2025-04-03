package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.util.GsonUtil;
import edu.ntnu.idat2003.model.Player;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class PlayerRepository {
  private static final string path = "src/main/resources/player.json";
  
  public static HashSet<Player> getPlayers() {
    Type playerSetType = new TypeToken<HashSet<Player>>() {}.getType();
    HashSet<Player> players = GsonUtil.getObjects(path, playerSetType);
    return players != null ? players : new HashSet<>();
  }

  public static void savePlayers(HashSet<Player> players) {
    GsonUtil.saveObjects(path, players);
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
