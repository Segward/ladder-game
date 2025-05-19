package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.view.BoardSelection;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GameSelectionController {

  private final Pane root;
  private final Button ladderGameButton;
  private final Button partyGameButton;

  public GameSelectionController(Pane root, Button ladderGameButton, Button partyGameButton) {
    this.root = root;
    this.ladderGameButton = ladderGameButton;
    this.partyGameButton = partyGameButton;
  }

  public void init() {
    ladderGameButton.setOnAction(e -> BoardSelection.init(root, 0));
    partyGameButton.setOnAction(e -> BoardSelection.init(root, 1));
  }
}
