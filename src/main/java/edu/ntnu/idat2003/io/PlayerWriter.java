package edu.ntnu.idat2003.io;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import edu.ntnu.idat2003.util.FileUtil;
import java.util.HashSet;

/** Class for Writting to Player csv files. Inclueds method for writting Player objects to csv. */
public class PlayerWriter {

  private static final String path = "data/player.csv";
  private static final String figures = "data/figures.csv";

  /**
   * Method for saving Player objects to spesific csv file. Takes HasSet<Player> as parameter. Using
   * a for loop, saves all player data as StringBuilder. Then utilizing different string methods
   * formats string to csv format, before utilising CsvUtil method to write data to spesific csv
   * file.
   *
   * @param players
   * @throws DataWriteException if there is an error writing the file
   */
  public static void savePlayers(HashSet<Player> players) throws DataWriteException {
    if (players == null || players.isEmpty()) {
      throw new IllegalArgumentException("Player set cannot be null or empty");
    }

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
      throw new DataWriteException("Error writing player data to file", e);
    }
  }

  /**
   * Method for adding new player to cvs file. Takes Player object as parameter. Fetches saved
   * Player to a new HashSet using PlayerReader method. Then adds new player to the new HasSet
   * before using savePlayers method with the new HashSet as a parameter.
   *
   * @param player Player object to saved
   * @throws DataWriteException if there is an error writing the file
   * @throws IllegalArgumentException if the player is null
   * @throws DataReadException if there is an error reading the file
   */
  public static void addPlayer(Player player) throws DataWriteException, DataReadException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    HashSet<Player> players = new HashSet<>();
    try {
      players = PlayerReader.getPlayers();
    } catch (DataReadException e) {
      throw new DataReadException("Error reading player data", e);
    }

    players.add(player);

    try {
      savePlayers(players);
    } catch (DataWriteException e) {
      throw new DataWriteException("Error adding new player data", e);
    }
  }

  /**
   * Method for wiping player file. It doesn't delete the file, but empties it.
   *
   * @throws DataWriteException if there is an error writing the file
   */
  public static void wipePlayerFile() throws DataWriteException {
    try {
      CsvUtil.writeFile(path, "");
    } catch (DataWriteException e) {
      throw new DataWriteException("Error wiping player file", e);
    }
  }

  /**
   * Method for removing player from save file. Takes Player object as parameter. Fetches saved
   * Player to a new HashSet using PlayerReader method. Then removes parameter player from new
   * HashSet. Checks if HashSet is empty, if true deletes save file. Then utilizes savePlayers
   * method with the new HashSet as a parameter.
   *
   * @param player Player object to be removed
   * @throws DataWriteException if there is an error writing the file
   * @throws DataReadException if there is an error reading the file
   * @throws IllegalArgumentException if player is null
   */
  public static void removePlayer(Player player) throws DataWriteException, DataReadException {
    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    HashSet<Player> players = new HashSet<>();
    try {
      players = PlayerReader.getPlayers();
    } catch (DataReadException e) {
      throw new DataReadException("Error reading player data", e);
    }

    players.remove(player);
    if (players.isEmpty() || players.size() == 0) {
      try {
        wipePlayerFile();
      } catch (DataWriteException e) {
        throw new DataWriteException("Error wiping player file", e);
      }
    } else {
      try {
        savePlayers(players);
      } catch (DataWriteException e) {
        throw new DataWriteException("Error removing player data", e);
      }
    }
  }

  /**
   * Method for loading Player objects from cvs file. Takes a String representing filePath as
   * parameter. Checks first if file allready exists, if true exits method. If not creates a new
   * HashSet for storing new players. Then reads a spesific csv file and stores data to a
   * StringBuilder. Then using a for loop cycles through the data and creates a new Player object
   * based on retreved data. Finally adding the new Player object to the new HashSet, before
   * utilizing the savePlayer() method with the new HashSet as parameter.
   *
   * @param filePath String representing filePath
   * @throws DataReadException if there is an error reading the file
   * @throws DataWriteException if there is an error writing the file
   * @throws IllegalArgumentException if the file does not exist
   */
  public static void loadPlayersFromFile(String filePath)
      throws DataReadException, DataWriteException {
    if (!FileUtil.fileExists(filePath)) {
      throw new IllegalArgumentException("File does not exist");
    }

    HashSet<Player> players = new HashSet<>();
    StringBuilder data = new StringBuilder();
    try {
      data.append(CsvUtil.readFile(filePath));
    } catch (DataReadException e) {
      throw new DataReadException("Error reading file", e);
    }

    if (data.toString().isEmpty()) {
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

    if (players.size() > 5) {
      while (players.size() > 5) {
        players.remove(players.iterator().next());
      }
    }

    try {
      savePlayers(players);
    } catch (DataWriteException e) {
      throw new DataWriteException("Error saving loaded players", e);
    }
  }

  /**
   * Method for saving Player objects to new cvs file. Takes a String representing filePath as
   * parameter. Using a for loop, saves all player data as StringBuilder. Then utilizing different
   * string methods formats string to csv format, before utilising CsvUtil method to write data to
   * spesific csv file.
   *
   * @param filePath
   */
  public static void savePlayersToFile(String filePath)
      throws DataWriteException, DataReadException {
    if (filePath == null || filePath.isEmpty()) {
      throw new IllegalArgumentException("File path cannot be null or empty");
    }

    HashSet<Player> players = new HashSet<>();

    try {
      players = PlayerReader.getPlayers();
    } catch (DataReadException e) {
      throw new DataReadException("Error reading player data for saving players to file", e);
    }

    if (players == null || players.isEmpty()) {
      throw new IllegalArgumentException("Player set cannot be null or empty");
    }

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
      throw new DataWriteException("Error writing player data to file", e);
    }
  }
}
