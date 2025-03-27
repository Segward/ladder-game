package edu.ntnu.idat2003.components;

import edu.ntnu.idat2003.models.Board;
import edu.ntnu.idat2003.services.GsonService;
import java.util.HashSet;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardSelection {
  public static void init(Pane root) {
    // Clear the pane
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    root.getChildren().add(stackPane);
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);

    HashSet<Board> boards = new HashSet<>();
    boards = GsonService.getBoards();

    for (Board board : boards) {
      FlowPane boardPane = new FlowPane();
      boardPane.setOrientation(Orientation.HORIZONTAL);
      boardPane.setAlignment(Pos.CENTER);

      Button selectBoard = new Button(board.getName());
      boardPane.getChildren().add(selectBoard);
      flowPane.getChildren().add(boardPane);

      selectBoard.setOnAction(e -> PlayerSelection.init(root));
    }
  }
}