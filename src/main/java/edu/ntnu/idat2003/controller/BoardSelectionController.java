package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.BoardFactory;
import edu.ntnu.idat2003.io.BoardReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.view.LadderGame;
import edu.ntnu.idat2003.view.PartyGame;
import java.util.HashSet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BoardSelectionController {

  private Pane root;
  private HBox boards;
  private int gameType;

  public BoardSelectionController(Pane root, HBox boards, int gameType) {
    this.root = root;
    this.boards = boards;
    this.gameType = gameType;
  }

  public void init() {
    try {
      HashSet<Board> boardSet = new HashSet<>();
      if (gameType == 0) {
        boardSet = BoardReader.getLadderBoards();
      } else if (gameType == 1) {
        boardSet = BoardReader.getPartyBoards();
      }

      if (boardSet.isEmpty() && gameType == 0) {
        BoardFactory.makeLadderBoards();
      } else if (boardSet.isEmpty() && gameType == 1) {
        BoardFactory.makePartyBoards();
      }

      updateBoards();
    } catch (Exception e) {
      System.out.println("Error loading boards: 1");
    }
  }

  private void onSelectClick(Board board) {
    if (gameType == 0) {
      LadderGame.init(root, board);
    } else if (gameType == 1) {
      PartyGame.init(root, board);
    }
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
      HashSet<Board> boardSet = new HashSet<>();
      if (gameType == 0) {
        boardSet = BoardReader.getLadderBoards();
      } else if (gameType == 1) {
        boardSet = BoardReader.getPartyBoards();
      }

      for (Board board : boardSet) {
        StackPane boardPane = createBoardPane(board);
        boards.getChildren().add(boardPane);
      }
    } catch (Exception e) {
      System.out.println("Error loading boards: 2");
    }
  }
}
