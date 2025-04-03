package edu.ntnu.idat2003.repo;

import edu.ntnu.idat2003.util.GsonUtil;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class FigureRepository {
  private static final string path = "src/main/resources/figure.json";
  
  public static HashSet<Figure> getFigures() {
    Type figureSetType = new TypeToken<HashSet<Figure>>() {}.getType();
    HashSet<Figure> figures = GsonUtil.getObjects(path, figureSetType);
    return figures != null ? players : new HashSet<>();
  }

  public static void saveFigures(HashSet<Figure> figures) {
    GsonUtil.saveObjects(path, figures);
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
