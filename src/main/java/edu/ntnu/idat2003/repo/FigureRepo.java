package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.util.CsvUtil;

import java.util.HashSet;

public class FigureRepo {
  private static final String path = "data/figure.csv";

  public static HashSet<Figure> getFigures() {
    HashSet<Figure> figures = new HashSet<>();
    StringBuilder data = new StringBuilder();
    try {
      data.append(CsvUtil.readFile(path));
    } catch (DataReadException e) {
      e.printStackTrace();
      return figures;
    }

    if (data.length() == 0) {
      return figures;
    }

    String[] lines = data.toString().split("\n");
    for (String line : lines) {
      String[] values = line.split(",");
      if (values.length < 2) {
        continue;
      }

      String name = values[0];
      String figurePath = values[1];
      Figure figure = new Figure(name, figurePath);
      figures.add(figure);
    }

    return figures;
  }

  public static void saveFigures(HashSet<Figure> figures) {
    StringBuilder data = new StringBuilder();
    for (Figure figure : figures) {
      data.append(figure.toString()).append("\n");
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

  public static HashSet<Figure> getAvailableFigures() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    HashSet<Figure> figures = getFigures();
    HashSet<Figure> availableFigures = new HashSet<>(figures);
    for (Player player : players) {
      availableFigures.remove(player.getFigure());
    }
    return availableFigures;
  }
}
