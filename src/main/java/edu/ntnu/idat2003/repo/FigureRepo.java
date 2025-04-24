package edu.ntnu.idat2003.repo;

import com.google.gson.reflect.TypeToken;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.util.GsonUtil;
import java.lang.reflect.Type;
import java.util.HashSet;

public class FigureRepo {
  private static final String path = "data/figure.json";

  public static HashSet<Figure> getFigures() {
    Type figureSetType = new TypeToken<HashSet<Figure>>() {}.getType();
    try {
      return GsonUtil.getObjects(path, figureSetType);
    } catch (Exception e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }

  public static void saveFigures(HashSet<Figure> figures) {
    try {
      GsonUtil.saveObjects(figures, path);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static HashSet<Figure> getAvailableFigures() {
    HashSet<Figure> figures = getFigures();
    HashSet<Player> players = PlayerRepo.getPlayers();
    for (Player player : players) {
      if (player.getFigure() != null) {
        figures.remove(player.getFigure());
      }
    }
    return figures;
  }
}
