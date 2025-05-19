package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.view.LadderGame;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainFrameController {

  private final BorderPane root;

  public MainFrameController(BorderPane borderPane) {
    this.root = borderPane;
  }

  public void init(Button startGame, Button exitGame) {
    startGame.setOnAction(e -> startGame());
    exitGame.setOnAction(e -> exitGame());
  }

  private void startGame() {
    LadderGame ladderGame = new LadderGame(root);
    ladderGame.init();
  }

  private void exitGame() {
    System.exit(0);
  }
}
