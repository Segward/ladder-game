package edu.ntnu.idat2003.view.controller;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.repo.BoardRepo;
import edu.ntnu.idat2003.repo.RepoFactory;
import edu.ntnu.idat2003.view.component.LadderGame;

import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class BoardSelectionController {

  private Pane root;
  private HBox boards;

  public BoardSelectionController(Pane root, HBox boards) {
    this.root = root;
    this.boards = boards;
  }

  public void init() {
    try {
      HashSet<Board> boardSet = BoardRepo.getBoards();
      if (boardSet.isEmpty()) {
        RepoFactory.makeBoards();
      }
      updateBoards();
    } catch (Exception e) {
      System.out.println("Error loading boards: 1");
    }
  }

  private void onSelectClick(Board board) {
    LadderGame.init(root, board);
  }

  private StackPane createBoardPane(Board board) {
    StackPane boardPane = new StackPane();
    boardPane.setAlignment(Pos.CENTER);
    boardPane.setMinSize(100, 50);

    VBox boardInfo = new VBox();
    boardInfo.setAlignment(Pos.CENTER);
    boardInfo.setSpacing(10);
    boardPane.getChildren().add(boardInfo);

    Text boardName = new Text(board.getName());
    boardName.setStyle("-fx-font-size: 20px; -fx-fill: white;");
    boardInfo.getChildren().add(boardName);

    Button selectButton = new Button("Select");
    selectButton.setOnAction(e -> onSelectClick(board));
    boardInfo.getChildren().add(selectButton);

    return boardPane;
  }

  private void updateBoards() {
    try {
      HashSet<Board> boardSet = BoardRepo.getBoards();
      for (Board board : boardSet) {
        StackPane boardPane = createBoardPane(board);
        boards.getChildren().add(boardPane);
      }
    } catch (Exception e) {
      System.out.println("Error loading boards: 2");
    }
  }
}
