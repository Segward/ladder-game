package edu.ntnu.idat2003.io;

import java.util.HashSet;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import edu.ntnu.idat2003.util.FileUtil;

public class PlayerWriter {

    private static final String path = "data/player.csv";
    private static final String figures = "data/figures.csv";

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
    HashSet<Player> players = PlayerReader.getPlayers();
    players.add(player);
    savePlayers(players);
  }

  public static void removePlayer(Player player) {
    HashSet<Player> players = PlayerReader.getPlayers();
    players.remove(player);
    if (players.isEmpty()) {
      FileUtil.deleteFile(path);
      return;
    }
    
    savePlayers(players);
  }

  public static void loadPlayersFromFile(String filePath) {
    if (!FileUtil.fileExists(filePath)) {
      return;
    }

    HashSet<Player> players = new HashSet<>();
    StringBuilder data = new StringBuilder();
    try {
      data.append(CsvUtil.readFile(filePath));
    } catch (DataReadException e) {
      e.printStackTrace();
      return;
    }

    if (data.length() == 0) {
      return;
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

    savePlayers(players);
  }

  public static void savePlayersToFile(String filePath) {
    HashSet<Player> players = PlayerReader.getPlayers();
    StringBuilder data = new StringBuilder();
    for (Player player : players) {
      data.append(player.toString()).append("\n");
    }

    String dataString = data.toString();
    if (dataString.charAt(dataString.length() - 1) == '\n') {
      dataString = dataString.substring(0, dataString.length() - 1);
    }

    try {
      CsvUtil.writeFile(filePath, data.toString());
    } catch (DataWriteException e) {
      e.printStackTrace();
    }
  }
}
