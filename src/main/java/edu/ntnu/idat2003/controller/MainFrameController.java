package edu.ntnu.idat2003.controller;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import edu.ntnu.idat2003.component.PlayerSelection;

public class MainFrameController {

  private Pane root;
  private Button start;
  private Button exit;  
  
  public MainFrameController(Pane root, Button start, Button exit) {
    this.root = root;
    this.start = start;
    this.exit = exit;
  }

  public void init() {
    start.setOnAction(this::onStartClick);
    exit.setOnAction(this::onExitClick);
  }

  public void onStartClick(ActionEvent event) {
    PlayerSelection.init(root);
  }
  
  public void onExitClick(ActionEvent event) {
    System.exit(0);
  } 
}
