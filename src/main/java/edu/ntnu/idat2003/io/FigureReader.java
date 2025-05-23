package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import java.util.HashSet;

/** Class for reading Figure csv files. Inclueds methods for getting Figure objects from csv. */
public class FigureReader {

  private static final String path = "data/figure.csv";

  /**
   * Method for retreving all figures from a csv file. Creates a HashSet for storing found Figure
   * objects, then reads a spesific csv file and stores data to a StringBuilder. Then using a for
   * loop cycles through the data and creates a new Figure object based on retreved data. Finlally
   * adding the new Figure object to the new HashSet.
   *
   * @return HasSet<Figure> all Figure objects retreved from csv
   * @throws DataReadException if there is an error reading the file
   */
  public static HashSet<Figure> getFigures() throws DataReadException {
    HashSet<Figure> figures = new HashSet<>();
    StringBuilder data = new StringBuilder();

    try {
      data.append(CsvUtil.readFile(path));
    } catch (DataReadException e) {
      throw new DataReadException("Error reading figure data", e);
    }

    if (data.toString().isEmpty()) {
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

  /**
   * Method for checking if a figure is available. Retreves all registed Players and figures, then
   * adds all registerd figures to a new HasSet representing available figures. Using a for loop
   * cycles through all players and removes used figures from the available figures HasSet.
   *
   * @return HashSet<Figure> all available Figure Objects
   * @throws DataReadException if there is an error reading the file
   */
  public static HashSet<Figure> getAvailableFigures() throws DataReadException {
    HashSet<Figure> figures = new HashSet<>();

    try {
      figures = getFigures();
    } catch (DataReadException e) {
      throw new DataReadException("Error reading figure data", e);
    }

    if (figures == null || figures.isEmpty()) {
      return null;
    }

    HashSet<Player> players = new HashSet<>();
    try {
      players = PlayerReader.getPlayers();
    } catch (DataReadException e) {
      throw new DataReadException("Error reading player data", e);
    }

    if (players == null || players.isEmpty()) {
      return figures;
    }

    HashSet<Figure> availableFigures = new HashSet<>(figures);
    for (Player player : players) {
      availableFigures.remove(player.getFigure());
    }
    return availableFigures;
  }
}
