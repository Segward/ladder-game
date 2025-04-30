package edu.ntnu.idat2003.view.controller;

import javafx.scene.control.Button;
import edu.ntnu.idat2003.view.component.PlayerSelection;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

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
