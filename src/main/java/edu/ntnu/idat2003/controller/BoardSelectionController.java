package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.BoardReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.view.LadderGame;
import edu.ntnu.idat2003.view.MainFrame;
import edu.ntnu.idat2003.view.PartyGame;
import java.util.HashSet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class BoardSelectionController {

  private final BorderPane root;
  private final HBox hBox;
  private final int gameType;

  public BoardSelectionController(BorderPane root, HBox hBox, int gameType) {
    this.root = root;
    this.hBox = hBox;
    this.gameType = gameType;
  }

  public void init(Button returnButton) {
    returnButton.setOnAction(e -> onReturn());
    updateBoards();
  }

  public void onReturn() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  private StackPane createBoardPane(Board board) {
    StackPane boardPane = new StackPane();
    boardPane.setAlignment(Pos.CENTER);
    boardPane.setMinSize(100, 50);

    VBox boardInfo = new VBox();
    boardInfo.setAlignment(Pos.CENTER);
    boardInfo.setSpacing(50);
    boardPane.getChildren().add(boardInfo);

    Text boardName = new Text(board.getName());
    boardName.setStyle("-fx-font-size: 20px; -fx-fill: BLACK;");
    boardInfo.getChildren().add(boardName);

    Button selectButton = new Button("Select");
    selectButton.setOnAction(e -> onSelectClick(board));
    boardInfo.getChildren().add(selectButton);

    return boardPane;
  }

  private void updateBoards() {
    try {
      HashSet<Board> boardSet = new HashSet<>();
      if (gameType == 1) {
        boardSet = BoardReader.getLadderBoards();
      } else if (gameType == 2) {
        boardSet = BoardReader.getPartyBoards();
      }

      for (Board board : boardSet) {
        StackPane boardPane = createBoardPane(board);
        hBox.getChildren().add(boardPane);
      }
    } catch (Exception e) {
      System.out.println("Error loading boards: 2");
    }
  }

  private void onSelectClick(Board board) {
    if (gameType == 1) {
      LadderGame ladderGame = new LadderGame(root, board);
      ladderGame.init();
    } else if (gameType == 2) {
      PartyGame partyGame = new PartyGame(root, board);
      partyGame.init();
    }
  }
}
