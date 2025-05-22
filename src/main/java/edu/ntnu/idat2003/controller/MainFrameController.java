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

public class MainFrameController {

  private final BorderPane root;

  public MainFrameController(BorderPane borderPane) {
    this.root = borderPane;
  }

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

  private void alert(String message) {
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setTitle("Notice");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void startLadderGame() {
    HashSet<Player> players = PlayerReader.getPlayers();
    if (players.isEmpty()) {
      alert("No players found. Please create a player first.");
      return;
    }

    BoardSelection boardSelection = new BoardSelection(root, 1);
    boardSelection.init();
  }

  private void startQuizGame() {
    HashSet<Player> players = PlayerReader.getPlayers();
    if (players.isEmpty()) {
      alert("No players found. Please create a player first.");
      return;
    }

    BoardSelection boardSelection = new BoardSelection(root, 2);
    boardSelection.init();
  }

  private void configureGame() {
    Configuration configuration = new Configuration(root);
    configuration.init();
  }

  private void startTicTacToeGame() {
    HashSet<Player> players = PlayerReader.getPlayers();
    if (players.isEmpty() || players.size() < 2) {
      alert("Not enough players found. Please create at least 2 players.");
      return;
    }

    TicTacToe ticTacToe = new TicTacToe(root);
    ticTacToe.init();
  }

  private void exitGame() {
    System.exit(0);
  }
}
