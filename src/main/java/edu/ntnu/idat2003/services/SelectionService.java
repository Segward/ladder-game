package edu.ntnu.idat2003.services;

import edu.ntnu.idat2003.models.Player;
import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.models.Figure;
import javafx.scene.layout.FlowPane;
import java.util.HashSet;

public class SelectionService {
  private StackPane createSelection() {
    StackPane stackPane = new StackPane();
    stackPane.setPrefSize(200, 200);
    return stackPane;
  } 
  
  /**
    Create a selection of players with the option to delete them.
    @param flowPane The flowpane to add the selection to.
    @param players The players to create the selection from.
  */
  public void createPlayerSelection(FlowPane flowPane, HashSet<Player> players) {
    for (Player player : players) {
      StackPane stackPane = createSelection();
      flowPane.getChildren().add(stackPane);
    } 
  }
  
  /**
    Create a selection of boards with the option to use them in the game.
    @param flowPane The flowpane to add the selection to.
    @param boards The boards to create the selection from.
  */
  public void createBoardSelection(FlowPane flowPane, HashSet<Board> boards) {
    for (Board board : boards) {
      StackPane stackPane = createSelection();
      flowPane.getChildren().add(stackPane);
    } 
  }

  /**
    Create a selection of figures with the option to give them to a player
    @param flowPane The flowpane to add the selection to.
    @param figures The figures to create the selection from.
    @param player The player to give the figures to.
  */
  public void createFigureSelection(FlowPane flowPane, HashSet<Figure> figures, Player player) {
    for (Figure figure : figures) {
      StackPane stackPane = createSelection();
      flowPane.getChildren().add(stackPane);
    }
  }
}

