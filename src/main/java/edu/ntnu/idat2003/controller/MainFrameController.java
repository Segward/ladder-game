package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.view.BoardSelection;
import edu.ntnu.idat2003.view.Configuration;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainFrameController {

  private final BorderPane root;

  public MainFrameController(BorderPane borderPane) {
    this.root = borderPane;
  }

  public void init(
      Button startLadderGame, Button startPartyGame, Button configureGame, Button exitGame) {
    startLadderGame.setOnAction(e -> startLadderGame());
    startPartyGame.setOnAction(e -> startPartyGame());
    configureGame.setOnAction(e -> configureGame());
    exitGame.setOnAction(e -> exitGame());
  }

  private void startLadderGame() {
    BoardSelection boardSelection = new BoardSelection(root, 1);
    boardSelection.init();
  }

  private void startPartyGame() {
    BoardSelection boardSelection = new BoardSelection(root, 2);
    boardSelection.init();
  }

  private void configureGame() {
    Configuration configuration = new Configuration(root);
    configuration.init();
  }

  private void exitGame() {
    System.exit(0);
  }
}
