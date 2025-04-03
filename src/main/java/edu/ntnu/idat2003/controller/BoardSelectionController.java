package edu.ntnu.idat2003.controller;

import javafx.scene.layout.FlowPane;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.scene.text.Text;
import edu.ntnu.idat2003.repo.BoardRepository;
import edu.ntnu.idat2003.component.LadderGame;
import javafx.scene.layout.Pane;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import edu.ntnu.idat2003.repo.RepoFactory;

public class BoardSelectionController {

  private Pane root;
  private FlowPane boards;

  public BoardSelectionController(Pane root, FlowPane boards) {
    this.root = root;
    this.boards = boards;
  }

  public void init() {
    HashSet<Board> boardSet = BoardRepository.getBoards();
    if (boardSet.isEmpty()) {
      RepoFactory.makeBoards();
    }

    updateBoards();
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
    HashSet<Board> boardSet = BoardRepository.getBoards();
    for (Board board : boardSet) {
      FlowPane boardPane = createBoardPane(board);
      boards.getChildren().add(boardPane);
    }
  }
}
