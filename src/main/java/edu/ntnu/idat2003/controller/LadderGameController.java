package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.model.Board;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import edu.ntnu.idat2003.component.MainFrame;

public class LadderGameController {
  
  private Pane root;
  private Board board;
  private Text rollText;
  private GridPane gridPane;
  private Button roll;
  private Button stop;

  public LadderGameController(Pane root, Board board, Text rollText, GridPane gridPane, Button roll, Button stop) {
    this.root = root;
    this.board = board;
    this.rollText = rollText;
    this.gridPane = gridPane;
    this.roll = roll;
    this.stop = stop;
  }

  public void init() {
    roll.setOnAction(this::onRollClick);
    stop.setOnAction(this::onStopClick);
    updateBoard();
  }

  public void onRollClick(ActionEvent event) {
    int rand = (int) (Math.random() * 6) + 1;
    rollText.setText("You rolled a " + rand);
  }

  public void onStopClick(ActionEvent event) {
    MainFrame.init(root);
  }

  private void updateBoard() {
    // Update gridPane with the current state of the board 
  }
}
