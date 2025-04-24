package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.component.MainFrame;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.ExtraDiceAction;
import edu.ntnu.idat2003.model.LadderAction;
import edu.ntnu.idat2003.model.LadderGame;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.TileAction;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.observer.LadderGameObserver;
import edu.ntnu.idat2003.repo.PlayerRepo;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LadderGameController implements LadderGameObserver {

  private Pane root;
  private Board board;
  private Text rollText;
  private GridPane gridPane;
  private Button roll;
  private Button stop;
  private LadderGame game;
  private MediaPlayer backgroundMediaPlayer;
  private MediaPlayer diceRollMediaPlayer;

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
    game = new LadderGame(players, board, this);
    roll.setOnAction(e -> game.rollDice());
    stop.setOnAction(e -> stop());
    updateBoard();

    File background = new File("src/main/resources/sound/thegrandaffair.mp3");
    File diceRoll = new File("src/main/resources/sound/diceroll.mp3");

    Media backgroundSound = new Media(background.toURI().toString());
    backgroundMediaPlayer = new MediaPlayer(backgroundSound);
    backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
    backgroundMediaPlayer.setVolume(0.1);
    backgroundMediaPlayer.play();

    Media diceRollSound = new Media(diceRoll.toURI().toString());
    diceRollMediaPlayer = new MediaPlayer(diceRollSound);
    diceRollMediaPlayer.setVolume(0.2);
  }

  private void stop() {
    backgroundMediaPlayer.stop();
    diceRollMediaPlayer.stop();
    MainFrame.init(root);
  }

  @Override
  public void onPlayerMoved(Player player, int remainder) {
    updateBoard();
    if (remainder == 0) {
      game.executeTileAction();
      game.nextPlayer();
      return;
    }

    PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
    pause.setOnFinished(e -> game.movePlayer(remainder - 1));
    pause.play();
  }

  @Override
  public void onTileActionExecuted(Player player, TileAction action) {
    if (action instanceof LadderAction) {
      PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
      pause.setOnFinished(e -> updateBoard());
      pause.play();
    }
  }

  @Override
  public void onPlayerWon(Player player) {
    rollText.setText(player.getName() + " has won!");
    roll.setDisable(true);
    updateBoard();
  }

  @Override
  public void onDiceRolled(int diceValue) {
    diceRollMediaPlayer.seek(Duration.ZERO);
    diceRollMediaPlayer.play();
    Timeline timeline = new Timeline();
    KeyFrame keyFrame =
        new KeyFrame(Duration.millis(10), e -> updateDice((int) (Math.random() * 6) + 1));
    timeline.getKeyFrames().add(keyFrame);
    timeline.setCycleCount(50);
    timeline.play();
    timeline.setOnFinished(e -> updateDice(diceValue));
  }

  private void updateDice(int diceValue) {
    ImageView diceView = new ImageView();
    diceView.setFitHeight(200);
    diceView.setPreserveRatio(true);

    Image diceImage =
        new Image(getClass().getResource("/dice/" + diceValue + "face.png").toExternalForm());
    diceView.setImage(diceImage);
    roll.setGraphic(diceView);
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
      String direction = action.getDirection();
      StackPane stackPane = new StackPane();
      Rectangle rectangle = new Rectangle(50, 50);
      stackPane.getChildren().addAll(rectangle);
      gridPane.add(stackPane, action.getStart().getX(), 9 - action.getStart().getY());

      StackPane stackPane2 = new StackPane();
      Rectangle rectangle2 = new Rectangle(50, 50);
      stackPane2.getChildren().addAll(rectangle2);
      gridPane.add(stackPane2, action.getDestination().getX(), 9 - action.getDestination().getY());

      if (direction.equals("up")) {
        rectangle.setFill(Color.DARKGREEN);
        rectangle2.setFill(Color.GREEN);
      } else {
        rectangle.setFill(Color.DARKRED);
        rectangle2.setFill(Color.RED);
      }
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
      String figurePath = "/figure/" + player.getFigure().getName() + ".png";
      Image figureImage = new Image(getClass().getResource(figurePath).toExternalForm());
      imageView.setImage(figureImage);
      int index = new ArrayList<>(players).indexOf(player);
      imageView.setTranslateX(index * 2);
      imageView.setTranslateY(index * 2);
      playerPane.getChildren().add(imageView);
    }
  }
}
