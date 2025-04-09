package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.model.Board;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import edu.ntnu.idat2003.component.MainFrame;
import edu.ntnu.idat2003.model.Dice;
import edu.ntnu.idat2003.model.Game;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.repo.PlayerRepository;
import java.util.HashSet;
import java.util.Stack;

public class LadderGameController {
  
  private Pane root;
  private Board board;
  private Text rollText;
  private GridPane gridPane;
  private Button roll;
  private Button stop;
  private Game game;  

  public LadderGameController(Pane root, Board board, Text rollText, GridPane gridPane, Button roll, Button stop) {
    this.root = root;
    this.board = board;
    this.rollText = rollText;
    this.gridPane = gridPane;
    this.roll = roll;
    this.stop = stop;
  }

  public void init() {
    HashSet<Player> players = PlayerRepository.getPlayers();
    Dice dice = new Dice(2);
    game = new Game(players, board, dice);
    roll.setOnAction(this::onRollClick);
    stop.setOnAction(this::onStopClick);
    updateBoard();
  }
  
  public void onRollClick(ActionEvent event) {
    game.roll();
    updateBoard();
  }

  public void onStopClick(ActionEvent event) {
    MainFrame.init(root);
  }

  private void updateBoard() {
    /*
    gridPane.getChildren().clear();
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 10; j++) {
        Button button = new Button("Temp");
        button.setMinSize(50, 50);
        button.setMaxSize(50, 50);
        gridPane.add(button, j, i);
      }
    }
    
    
    

    HashSet<Player> players = game.getPlayers2();
    for (Player player : players) {
      int position = player.getPosition();
      int row = 8 - position / 10;
      int col = position % 10;
      System.out.println("Player " + player.getName() + " is at position " + position);
      Button button = new Button(player.getName());
      button.setMinSize(50, 50);
      button.setMaxSize(50, 50);
      button.setStyle("-fx-background-color: red;");
      gridPane.add(button, col, row);
    }
    */
    createBord();
  }

  public void createBord() {
    gridPane.getChildren().clear();
    for(int i = 0; i < 9; i++) {
      
      for(int j = 0; j < 10; j++) {
        StackPane stackPane = new StackPane();
        Rectangle visualTile = new Rectangle(50,50);

        visualTile.setFill(Color.BEIGE);

        String tileString = String.valueOf(i) + String.valueOf(j);
        int tileNum= Integer.parseInt(tileString);
        visualTile.setUserData(board.getTile((tileNum)));

        Text text = new Text(String.valueOf(board.getTile((tileNum)).getPosition()+1));
        stackPane.getChildren().addAll(visualTile, text);
        gridPane.add(stackPane, j, i);
        
      }
      
      /* 
      int j = 0;
      int add = 1;
      do {
        StackPane stackPane = new StackPane();
        Rectangle visualTile = new Rectangle(50,50);

        visualTile.setFill(Color.BEIGE);

        String tileString = String.valueOf(i) + String.valueOf(j);
        int tileNum= Integer.parseInt(tileString);
        visualTile.setUserData(board.getTile((tileNum)));

        Text text = new Text(String.valueOf(board.getTile((tileNum)).getPosition()+1));
        stackPane.getChildren().addAll(visualTile, text);
        gridPane.add(stackPane, j, i);
        j+=add;
      } while(j >= 0 && j < 10);
      add*=-1; 
      */
    }
     
  }
}
