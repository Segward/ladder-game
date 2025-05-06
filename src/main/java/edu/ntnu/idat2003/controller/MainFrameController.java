package edu.ntnu.idat2003.controller;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import edu.ntnu.idat2003.component.PlayerSelection;
import edu.ntnu.idat2003.component.TicTacToe;

public class MainFrameController {

  private Pane root;
  private Button start;
  private Button exit;  
  private Button ticTacToe;
  
  public MainFrameController(Pane root, Button start, Button exit, Button ticTacToe) {
    this.root = root;
    this.start = start;
    this.exit = exit;
    this.ticTacToe = ticTacToe;
  }

  public void init() {
    start.setOnAction(this::onStartClick);
    exit.setOnAction(this::onExitClick);
    ticTacToe.setOnAction(this::onTicTacToeClick);
  }

  public void onStartClick(ActionEvent event) {
    PlayerSelection.init(root);
  }
  
  public void onExitClick(ActionEvent event) {
    System.exit(0);
  } 

  public void onTicTacToeClick(ActionEvent event) {
    TicTacToe.init(root);
  }
}
