package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.util.GsonUtil;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;

public class FigureRepo {
  private static final String path = "src/main/resources/figure.json";
  
  public static HashSet<Figure> getFigures() {
    Type figureSetType = new TypeToken<HashSet<Figure>>() {}.getType();
    HashSet<Figure> figures = GsonUtil.getObjects(path, figureSetType);
    return figures != null ? figures : new HashSet<>();
  }

  public static void saveFigures(HashSet<Figure> figures) {
    GsonUtil.saveObjects(figures, path);
  }

  public static HashSet<Figure> getAvailableFigures() {
    HashSet<Figure> figures = getFigures();
    HashSet<Player> players = PlayerRepository.getPlayers();
    for (Player player : players) {
      if (player.getFigure() != null) {
        figures.remove(player.getFigure());
      }
    }
    return figures;
  }
}
