package edu.ntnu.idat2003.io;

import java.util.HashSet;

import edu.ntnu.idat2003.exception.DataReadException;
import edu.ntnu.idat2003.exception.DataWriteException;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.CsvUtil;
import edu.ntnu.idat2003.util.FileUtil;

/**
 *  Class for Writting to Player csv files.
 *  Inclueds method for writting Player objects to csv.
 */
public class PlayerWriter {

    private static final String path = "data/player.csv";
    private static final String figures = "data/figures.csv";

  /**
     *  Method for saving Player objects to spesific csv file.
     *  Takes HasSet<Player> as parameter.
     *  Using a for loop, saves all player data as StringBuilder.
     *  Then utilizing different string methods formats string to csv format,
     *  before utilising CsvUtil method to write data to spesific csv file.
     * 
     *  @param players
     */
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

  /**
   *  Method for adding new player to cvs file.
   *  Takes Player object as parameter.
   *  Fetches saved Player to a new HashSet using PlayerReader method.  
   *  Then adds new player to the new HasSet before using savePlayers method 
   *  with the new HashSet as a parameter.
   * 
   *  @param player Player object to saved
   */
  public static void addPlayer(Player player) {
    HashSet<Player> players = PlayerReader.getPlayers();
    players.add(player);
    savePlayers(players);
  }

  /**
   *  Method for removing player from save file.
   *  Takes Player object as parameter.
   *  Fetches saved Player to a new HashSet using PlayerReader method.  
   *  Then removes parameter player from new HashSet.
   *  Checks if HashSet is empty, if true deletes save file.
   *  Then utilizes savePlayers method 
   *  with the new HashSet as a parameter.
   *  
   *  @param player Player object to be removed
   */
  public static void removePlayer(Player player) {
    HashSet<Player> players = PlayerReader.getPlayers();
    players.remove(player);
    if (players.isEmpty()) {
      FileUtil.deleteFile(path);
      return;
    }
    
    savePlayers(players);
  }

  /**
   *  Method for loading Player objects from cvs file.
   *  Takes a String representing filePath as parameter.
   *  Checks first if file allready exists, if true exits method.
   *  If not creates a new HashSet for storing new players.
   *  Then reads a spesific csv file and stores data to a StringBuilder.
   *  Then using a for loop cycles through the data and creates a new
   *  Player object based on retreved data.
   *  Finally adding the new Player object to the new HashSet,
   *  before utilizing the savePlayer() method with the new HashSet as parameter.
   * 
   *  @param filePath String representing filePath
   */
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

  /**
   *  Method for saving Player objects to new cvs file.
   *  Takes a String representing filePath as parameter.
   *  Using a for loop, saves all player data as StringBuilder.
   *  Then utilizing different string methods formats string to csv format,
   *  before utilising CsvUtil method to write data to spesific csv file.
   * 
   *  @param filePath
   */
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
