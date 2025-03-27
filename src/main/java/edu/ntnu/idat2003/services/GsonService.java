package edu.ntnu.idat2003.services;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Player;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;

public class GsonService {

  private static final String PLAYERS_FILE_PATH = "src/main/resources/players.json";
  private static final String BOARDS_FILE_PATH = "src/main/resources/boards.json";
  private static final String FIGURES_FILE_PATH = "src/main/resources/figures.json";

  public static HashSet<Player> getPlayers() {
    Type type = new TypeToken<HashSet<Player>>() {}.getType();
    return GsonUtil.getObjects(PLAYERS_FILE_PATH, type);
  }

  public static HashSet<Board> getBoards() {
    Type type = new TypeToken<HashSet<Board>>() {}.getType();
    HashSet<Board> boards = GsonUtil.getObjects(BOARDS_FILE_PATH, type);
    
    if (boards == null || boards.isEmpty()) {
      boards = GameFactory.makeBoards();
    }
    
    return boards;
  }

  public static HashSet<Figure> getAvailableFigures() {
    Type figuresType = new TypeToken<HashSet<Figure>>() {}.getType();
    Type playersType = new TypeToken<HashSet<Player>>() {}.getType();

    HashSet<Figure> figures = GsonUtil.getObjects(FIGURES_FILE_PATH, figuresType);
    HashSet<Player> players = GsonUtil.getObjects(PLAYERS_FILE_PATH, playersType);
    
    if (figures == null || figures.isEmpty()) {
      figures = GameFactory.makeFigures();
    }

    if (players == null || players.isEmpty()) {
      return figures;
    }

    for (Player player : players) {
      figures.remove(player.getFigure());
    }

    return figures;
  }

  public static void deletePlayer(Player player) {
    Type type = new TypeToken<HashSet<Player>>() {}.getType();
    GsonUtil.deleteObject(player, PLAYERS_FILE_PATH, type);
  }

  public static void savePlayer(Player player) {
    Type type = new TypeToken<HashSet<Player>>() {}.getType();
    GsonUtil.saveObject(player, PLAYERS_FILE_PATH, type);
  }
}
