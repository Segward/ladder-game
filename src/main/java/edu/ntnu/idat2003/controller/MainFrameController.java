package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.view.BoardSelection;
import edu.ntnu.idat2003.view.Configuration;
import edu.ntnu.idat2003.view.TicTacToe;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 *  Class representing Main menu screen functonality.
 *  Deffines funtionality to buttons.
 */
public class MainFrameController {

  private final BorderPane root;

  /**
   *  Constructor for the MainFrameController.
   *  Takes BoarderPane as parameter.
   * 
   *  @param borderPane BorderPane representing Main screen
   */
  public MainFrameController(BorderPane borderPane) {
    this.root = borderPane;
  }

  /**
   *  Initiates the functonality of buttons.
   *  Deffines which method each button initiates
   * 
   *  @param startLadderGame Button for starting LadderGame
   *  @param startPartyGame Btuuon for Starting quiz game
   *  @param startTicTacToe Button for starting TicTacToe game
   *  @param configureGame Button for configure menu
   *  @param exitGame Button for exeting application
   */
  public void init(
      Button startLadderGame,
      Button startPartyGame,
      Button startTicTacToe,
      Button configureGame,
      Button exitGame) {
    startLadderGame.setOnAction(e -> startLadderGame());
    startPartyGame.setOnAction(e -> startPartyGame());
    startTicTacToe.setOnAction(e -> startTicTacToeGame());
    configureGame.setOnAction(e -> configureGame());
    exitGame.setOnAction(e -> exitGame());
  }

  /**
   *  Method that initialises LadderGame boardSelection.
   *  Creates a new BoarderSelection object with game type 1,
   *  then initialises boarderSelection init() method.
   */
  private void startLadderGame() {
    BoardSelection boardSelection = new BoardSelection(root, 1);
    boardSelection.init();
  }

  /**
   *  Method that initialises Quiz game boardSelection.
   *  Creates a new BoarderSelection object with game type 2,
   *  then initialises boarderSelection init() method.
   */
  private void startPartyGame() {
    BoardSelection boardSelection = new BoardSelection(root, 2);
    boardSelection.init();
  }

  /**
   *  Method that initialises configuration screen.
   *  Creates a new configuration object,
   *  then initialises configuration init() method.
   */
  private void configureGame() {
    Configuration configuration = new Configuration(root);
    configuration.init();
  }

   /**
   *  Method that initialises TicTacToe game.
   *  Creates a new tictactoe object,
   *  then initialises tictactoe init() method.
   */
  private void startTicTacToeGame() {
    TicTacToe ticTacToe = new TicTacToe(root);
    ticTacToe.init();
  }

  /**
   *  Method for exiting application.
   *  setts system status to 0.
   */
  private void exitGame() {
    System.exit(0);
  }
}
