package edu.ntnu.idat2003.services;

import javafx.scene.layout.FlowPane;
import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Player;
import java.util.HashSet;

public class LadderGameService {
  private final StateService stateService = new StateService();
  private final GsonService gsonService = new GsonService();
  private final SelectionService selectionService = new SelectionService();

  public void getPlayers(FlowPane flowPane) {
    HashSet<Player> players = gsonService.getPlayers();
    selectionService.selectPlayers(players, flowPane);
  }

  public void getBoards(FlowPane flowPane) {
    HashSet<Board> boards = gsonService.getBoards();
    selectionService.selectBoards(boards, flowPane);
  }

  public void getFigures(FlowPane flowPane) {
    HashSet<Figure> figures = gsonService.getFigures();
    selectionService.selectFigures(figures, flowPane);
  }
}
