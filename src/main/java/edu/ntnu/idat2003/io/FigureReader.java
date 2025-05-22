package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;

public class FigureReader {

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

  public static HashSet<Figure> getAvailableFigures() {
    HashSet<Player> players = PlayerReader.getPlayers();
    HashSet<Figure> figures = getFigures();
    HashSet<Figure> availableFigures = new HashSet<>(figures);
    for (Player player : players) {
      availableFigures.remove(player.getFigure());
    }
    return availableFigures;
  }
}
