package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;

public class PlayerReader {

  private static final String playerPath = "data/player.csv";
  private static final String figureResourcePath = "/figure/";

  public static HashSet<Player> getPlayers() {
    HashSet<Player> players = new HashSet<>();
    StringBuilder data = new StringBuilder();
    try {
      data.append(CsvUtil.readFile(playerPath));
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

      String playerName = values[0];
      String figureName = values[1];
      String path = String.format("%s%s.png", figureResourcePath, figureName);
      Figure figure = new Figure(figureName, path);
      Player player = new Player(playerName, figure);
      players.add(player);
    }

    return players;
  }
}
