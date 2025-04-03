package edu.ntnu.idat2003.controller;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;

public class MainFrameController {

  public Pane root;
  public Button start;
  public Button exit;  
  
  public MainFrameController(Pane root, Button start, Button exit) {
    this.root = root;
    this.start = start;
    this.exit = exit;
    this.start.setOnAction(this::onStartClick);
    this.exit.setOnAction(this::onExitClick);
  }

  public void onStartClick(ActionEvent event) {
    System.out.println("Game started!");
  }
  
  public void onExitClick(ActionEvent event) {
    System.out.println("Game exited!");
  } 
}
