package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.view.BoardSelection;
import edu.ntnu.idat2003.view.Configuration;
import edu.ntnu.idat2003.view.TicTacToe;
import java.util.HashSet;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
      Button startQuizGame,
      Button startTicTacToe,
      Button configureGame,
      Button exitGame) {
    startLadderGame.setOnAction(e -> startLadderGame());
    startQuizGame.setOnAction(e -> startQuizGame());
    startTicTacToe.setOnAction(e -> startTicTacToeGame());
    configureGame.setOnAction(e -> configureGame());
    exitGame.setOnAction(e -> exitGame());
  }

  /**
   *  Method for alerting user.
   *  Takes String as parameter and creates an alert with the message.
   * 
   *  @param message Message to be displayed
   */
  private void alert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Notice");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   *  Method that initialises LadderGame.
   *  Creates a new LadderGame object,
   *  then initialises ladderGame init() method.
   */
  private void startLadderGame() {
    HashSet<Player> players = PlayerReader.getPlayers();
    if (players.isEmpty()) {
      alert("No players found. Please create a player first.");
      return;
    }

    BoardSelection boardSelection = new BoardSelection(root, 1);
    boardSelection.init();
  }

  /**
   *  Method that initialises quiz game.
   *  Creates a new BoardSelection object,
   *  then initialises boardSelection init() method.
   */
  private void startQuizGame() {
    HashSet<Player> players = PlayerReader.getPlayers();
    if (players.isEmpty()) {
      alert("No players found. Please create a player first.");
      return;
    }

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
    HashSet<Player> players = PlayerReader.getPlayers();
    if (players.isEmpty() || players.size() < 2) {
      alert("Not enough players found. Please create at least 2 players.");
      return;
    }

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
