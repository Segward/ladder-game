package edu.ntnu.idat2003.services;

import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Player;
import edu.ntnu.idat2003.util.GsonUtil;
import java.util.HashSet;

public class GsonService {

  HashSet<Player> getPlayers() {
    return GsonUtil.getObjects("players.json");
  }

  HashSet<Board> getBoards() {
    return GsonUtil.getObjects("boards.json");
  }

  HashSet<Figure> getAvailableFigures() {
    HashSet<Figure> figures = GsonUtil.getObjects("figures.json");
    HashSet<Player> players = GsonUtil.getObjects("players.json");
    for (Player player : players) {
      figures.remove(player.getFigure());
    }
    return figures;
  }

  void savePlayer(Player player) {
    GsonUtil.deleteObject(player, "players.json");
    GsonUtil.saveObject(player, "players.json");
  }
}
