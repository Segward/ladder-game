package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.BoardFactory;
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

/**
 * This class represents the logic that happends in the board selection screen.
 * 
 */
public class BoardSelectionController {

  private final BorderPane root;
  private final HBox hBox;
  private final int gameType;

  /**
   *  Constructor for the BOrderSelectionController class.
   *  Takes an BoarderPane, HBox and int as parameters.
   * 
   *  @param root BorderPane Main Canvas 
   *  @param hBox HBox selaction screen
   *  @param gameType int representing game type
   */
  public BoardSelectionController(BorderPane root, HBox hBox, int gameType) {
    this.root = root;
    this.hBox = hBox;
    this.gameType = gameType;
  }

  /**
   *  Initialises the screen data and defines the
   *  funconality of the return button.
   * 
   *  @param returnButton Button representing return function
   */
  public void init(Button returnButton) {
    returnButton.setOnAction(e -> onReturn());
    updateBoards();
  }

  /**
   *  Method for returning to the main screen.
   *  Initialises the mainframes init() method.
   */
  public void onReturn() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  /**
   *  Creates a StackPane for a singel board selection.
   *  Creates a stackpane that holds a VBox that
   *  holdes the Text board name and a button for selection.
   * 
   *  @param board Board object to be placed in pane
   *  @return StackPane that holds board infromation
   */
  private StackPane createBoardPane(Board board) {
    StackPane boardPane = new StackPane();
    boardPane.setAlignment(Pos.CENTER);
    boardPane.setMinSize(100, 50);

    VBox boardInfo = new VBox();
    boardInfo.setAlignment(Pos.CENTER);
    boardInfo.setSpacing(50);
    boardPane.getChildren().add(boardInfo);

    Text boardName = new Text(board.getName());
    boardName.setStyle("-fx-font-size: 30px; -fx-fill: WHITE;");
    boardInfo.getChildren().add(boardName);
    
    Button selectButton = new Button("Select");
    selectButton.setOnAction(e -> onSelectClick(board));
    boardInfo.getChildren().add(selectButton);

    return boardPane;
  }

  /**
   *  Method for initialising the different board selections.
   *  Checks if the gametype is laddergame or partyGame and collectes
   *  given boards. Then utilizes a for loop to goes through each
   *  board and creates a visual StackPane.
   * 
  */
  private void updateBoards() {
    try {
      HashSet<Board> boardSet = new HashSet<>();
      if (gameType == 1) {
        boardSet = BoardReader.getLadderBoards();
        if (boardSet.isEmpty()) {
          BoardFactory.makeLadderBoards();
          boardSet = BoardReader.getLadderBoards();
        }
      } else if (gameType == 2) {
        boardSet = BoardReader.getPartyBoards();
        if (boardSet.isEmpty()) {
          BoardFactory.makePartyBoards();
          boardSet = BoardReader.getPartyBoards();
        }
      }

      for (Board board : boardSet) {
        StackPane boardPane = createBoardPane(board);
        hBox.getChildren().add(boardPane);
      }
    } catch (Exception e) {
      System.out.println("Error loading boards: 2");
    }
  }

  /**
   *  Method for checking type of board choosen.
   *  Checks if the gameType is ladder og party game,
   *  and creates a new Game object according to result.
   * 
   * @param board Board object to be Initialised
  */
  private void onSelectClick(Board board) {
    if (gameType == 1) {
      LadderGame ladderGame = new LadderGame(root, board);
      ladderGame.init();
    } else if (gameType == 2) {
      PartyGame partyGame = new PartyGame(root, board);
      partyGame.init();
    }
  }

  /**
   *  Method created for testing.
   *  Easy access to private createBoradPane method.
   * 
   *  @param board Board object to be placed in pane
   *  @return StackPane that holds board infromation
   */
  public StackPane getCreatboPane(Board board){
    return createBoardPane(board);
  }
}
