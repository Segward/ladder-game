package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.view.LadderGame;
import edu.ntnu.idat2003.view.PartyGame;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainFrameController {

  private final BorderPane root;

  public MainFrameController(BorderPane borderPane) {
    this.root = borderPane;
  }

  public void init(Button startLadderGame, Button startPartyGame, Button exitGame) {
    startLadderGame.setOnAction(e -> startLadderGame());
    startPartyGame.setOnAction(e -> startPartyGame());
    exitGame.setOnAction(e -> exitGame());
  }

  private void startLadderGame() {
    LadderGame ladderGame = new LadderGame(root);
    ladderGame.init();
  }

  private void startPartyGame() {
    PartyGame partyGame = new PartyGame(root);
    partyGame.init();
  }

  private void exitGame() {
    System.exit(0);
  }
}
