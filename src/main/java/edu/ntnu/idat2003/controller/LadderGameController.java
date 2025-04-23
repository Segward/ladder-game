package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.component.MainFrame;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.ExtraDiceAction;
import edu.ntnu.idat2003.model.Game;
import edu.ntnu.idat2003.model.LadderAction;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.repo.PlayerRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LadderGameController {

  private Pane root;
  private Board board;
  private Text rollText;
  private GridPane gridPane;
  private Button roll;
  private Button stop;
  private Game game;
  private boolean moving = false;

  public LadderGameController(
      Pane root, Board board, Text rollText, GridPane gridPane, Button roll, Button stop) {
    this.root = root;
    this.board = board;
    this.rollText = rollText;
    this.gridPane = gridPane;
    this.roll = roll;
    this.stop = stop;
  }

  public void init() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    game = new Game(players, board);
    roll.setOnAction(this::onRollClick);
    stop.setOnAction(this::onStopClick);
    updateBoard();
  }

  public void onRollClick(ActionEvent event) {
    if (moving) {
      return;
    }

    moving = true;
    if (game.isGameOver()) {
      rollText.setText("Game Over");
      return;
    }

    diceAnimation();
    int steps = game.roll();
    movePlayerWithPause(steps);
  }

  private void movePlayerWithPause(int stepsLeft) {
    if (stepsLeft == 0) {
      int actionType = game.checkAction();
      updateBoard();
      moving = false;
      return;
    }

    game.movePlayer();
    updateBoard();
    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
    pause.setOnFinished(e -> movePlayerWithPause(stepsLeft - 1));
    pause.play();
  }

  public void diceAnimation() {
    String face = "face.png";
    ImageView diceView = new ImageView();
    diceView.setFitHeight(100);
    diceView.setPreserveRatio(true);

    Timeline TimeLine =
        new Timeline(
            new KeyFrame(
                Duration.millis(10),
                e -> {
                  int dice = (int) (Math.random() * 6) + 1;
                  Image diceImage =
                      new Image(getClass().getResource("/imag/" + dice + face).toExternalForm());
                  diceView.setImage(diceImage);
                  roll.setGraphic(diceView);
                }));
    TimeLine.setCycleCount(50);
    TimeLine.play();
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
      gridPane.add(stackPane, tile.getPosition().getX(), 9 - tile.getPosition().getY());
    }

    HashMap<Integer, LadderAction> ladders = board.getLadderActions();
    for (LadderAction action : ladders.values()) {
      StackPane stackPane = new StackPane();
      Rectangle rectangle = new Rectangle(50, 50);
      rectangle.setFill(Color.GREEN);
      stackPane.getChildren().addAll(rectangle);
      gridPane.add(stackPane, action.getStart().getX(), 9 - action.getStart().getY());

      StackPane stackPane2 = new StackPane();
      Rectangle rectangle2 = new Rectangle(50, 50);
      rectangle2.setFill(Color.RED);
      stackPane2.getChildren().addAll(rectangle2);
      gridPane.add(stackPane2, action.getDestination().getX(), 9 - action.getDestination().getY());
    }

    HashMap<Integer, ExtraDiceAction> extraDiceActions = board.getExtraDiceActions();
    for (ExtraDiceAction action : extraDiceActions.values()) {
      StackPane stackPane = new StackPane();
      Rectangle rectangle = new Rectangle(50, 50);
      rectangle.setFill(Color.YELLOW);
      stackPane.getChildren().addAll(rectangle);
      gridPane.add(stackPane, action.getStart().getX(), 9 - action.getStart().getY());
    }

    HashSet<Player> players = game.getPlayers();
    HashSet<Vector2> playerPositions = new HashSet<>();
    for (Player player : players) {
      playerPositions.add(player.getPosition());
    }

    HashMap<Integer, StackPane> tilePanes = new HashMap<>();
    for (Vector2 position : playerPositions) {
      StackPane playerPane = new StackPane();
      playerPane.setMaxHeight(50);
      playerPane.setMaxWidth(50);
      playerPane.setPrefSize(50, 50);
      gridPane.add(playerPane, position.getX(), 9 - position.getY());
      tilePanes.put(position.hashCode(), playerPane);
    }

    for (Player player : players) {
      StackPane playerPane = tilePanes.get(player.getPosition().hashCode());
      ImageView imageView = new ImageView();
      imageView.setFitHeight(40);
      imageView.setPreserveRatio(true);
      String figurePath = "/imag/" + player.getFigure().getColor() + ".png";
      Image figureImage = new Image(getClass().getResource(figurePath).toExternalForm());
      imageView.setImage(figureImage);
      int index = new ArrayList<>(players).indexOf(player);
      imageView.setTranslateX(index * 2);
      imageView.setTranslateY(index * 2);
      playerPane.getChildren().add(imageView);
    }
  }
}
