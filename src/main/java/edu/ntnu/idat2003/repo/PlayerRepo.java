package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.util.CsvUtil;

import java.util.HashSet;

public class PlayerRepo {
  private static final String path = "data/player.csv";
  private static final String figures = "/figure/";

  public static HashSet<Player> getPlayers() {
    HashSet<Player> players = new HashSet<>();
    StringBuilder data = new StringBuilder();
    try {
      data.append(CsvUtil.readFile(path));
    } catch (DataReadException e) {
      e.printStackTrace();
      return players;
    }

    if (data.length() == 0) {
      return players;
    }

    String[] lines = data.toString().split("\n");
    for (String line : lines) {
      String[] values = line.split(",");
      if (values.length < 2) {
        continue;
      }

      String name = values[0];
      String figure = values[1];
      String figurePath = String.format("%s%s.png", figures, figure);
      Figure figureObj = new Figure(figure, figurePath);
      Player player = new Player(name, figureObj);
      players.add(player);
    }

    return players;
  }

  public static void savePlayers(HashSet<Player> players) {
    StringBuilder data = new StringBuilder();
    for (Player player : players) {
      data.append(player.toString()).append("\n");
    }

    String dataString = data.toString();
    if (dataString.charAt(dataString.length() - 1) == '\n') {
      dataString = dataString.substring(0, dataString.length() - 1);
    }

    try {
      CsvUtil.writeFile(path, data.toString());
    } catch (DataWriteException e) {
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
