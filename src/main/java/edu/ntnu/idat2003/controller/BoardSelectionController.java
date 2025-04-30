package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.component.LadderGame;
import edu.ntnu.idat2003.model.board.Board;
import edu.ntnu.idat2003.repo.BoardRepo;
import edu.ntnu.idat2003.repo.RepoFactory;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class BoardSelectionController {

  private Pane root;
  private FlowPane boards;

  public BoardSelectionController(Pane root, FlowPane boards) {
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

  private void onSelectClick(ActionEvent event, Board board) {
    LadderGame.init(root, board);
  }

  private FlowPane createBoardPane(Board board) {
    FlowPane boardPane = new FlowPane();
    boardPane.setOrientation(Orientation.HORIZONTAL);
    Text boardName = new Text(board.getName());
    Button select = new Button("Select");
    select.setOnAction(e -> onSelectClick(e, board));
    boardPane.getChildren().addAll(boardName, select);
    return boardPane;
  }

  private void updateBoards() {
    try {
      HashSet<Board> boardSet = BoardRepo.getBoards();
      for (Board board : boardSet) {
        FlowPane boardPane = createBoardPane(board);
        boards.getChildren().add(boardPane);
      }
    } catch (Exception e) {
      System.out.println("Error loading boards: 2");
    }
  }
}
