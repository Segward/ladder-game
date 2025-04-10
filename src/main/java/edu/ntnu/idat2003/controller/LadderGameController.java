package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.model.Board;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import edu.ntnu.idat2003.component.MainFrame;
import edu.ntnu.idat2003.model.Game;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.repo.PlayerRepository;
import java.util.HashSet;
import java.util.Stack;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.Tile;
import java.util.HashMap;

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
    game = new Game(players, board);
    roll.setOnAction(this::onRollClick);
    stop.setOnAction(this::onStopClick);
    updateBoard();
  }
  
  public void onRollClick(ActionEvent event) {
    boolean isGameOver = game.isGameOver();
    if (isGameOver) {
      rollText.setText("Game Over");
      return;
    }

    int steps = game.roll();
    updateBoard();
  }

  public void onStopClick(ActionEvent event) {
    MainFrame.init(root);
  }

  private void updateBoard() {
    gridPane.getChildren().clear();
    HashMap<Integer, Tile> tiles = board.getTiles();
    for (Tile tile : tiles.values()) {
      StackPane stackPane = new StackPane();
      Rectangle rectangle = new Rectangle(50, 50);
      rectangle.setFill(Color.LIGHTGRAY);
      int tileNumber = tile.getPosition().getNumber();
      Text text = new Text(String.valueOf(tileNumber));
      stackPane.getChildren().addAll(rectangle, text);
      gridPane.add(stackPane, tile.getPosition().getX(), 9-tile.getPosition().getY());
    }

    HashSet<Player> players = game.getPlayers();
    for (Player player : players) {
      Vector2 position = player.getPosition();
      StackPane stackPane = new StackPane();
      Rectangle rectangle = new Rectangle(50, 50);
      rectangle.setFill(Color.BLUE);
      Text text = new Text(player.getName());
      stackPane.getChildren().addAll(rectangle, text);
      gridPane.add(stackPane, position.getX(), 9-position.getY());
    }
  }
}
