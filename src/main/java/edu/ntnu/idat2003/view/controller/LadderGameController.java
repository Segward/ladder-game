package edu.ntnu.idat2003.view.controller;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.LadderGame;
import edu.ntnu.idat2003.model.LadderGameObserver;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.model.tile.Tile;
import edu.ntnu.idat2003.model.tile.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tile.tileactions.TileAction;
import edu.ntnu.idat2003.repo.PlayerRepo;
import edu.ntnu.idat2003.view.component.MainFrame;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LadderGameController implements LadderGameObserver {

  private Pane root;
  private StackPane overlay;
  private Board board;
  private Text rollText;
  private GridPane gridPane;
  private Button roll;
  private Button stop;
  private LadderGame game;
  private MediaPlayer backgroundMediaPlayer;
  private MediaPlayer diceRollMediaPlayer;
  private HashMap<Integer, StackPane> tilePanes;

  public LadderGameController(
      Pane root,
      StackPane overlay,
      Board board,
      Text rollText,
      GridPane gridPane,
      Button roll,
      Button stop) {
    this.root = root;
    this.overlay = overlay;
    this.board = board;
    this.rollText = rollText;
    this.gridPane = gridPane;
    this.roll = roll;
    this.stop = stop;
    this.tilePanes = new HashMap<>();
  }

  public void init() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    game = new LadderGame(players, board, this);
    game.init();

    roll.setOnAction(e -> game.rollDice());
    stop.setOnAction(e -> stop());

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

    HashMap<Integer, Tile> tiles = board.getTiles();
    for (Tile tile : tiles.values()) {
      Vector2 position = tile.getPosition();
      StackPane tilePane = new StackPane();
      tilePane.setStyle("-fx-background-color: red;");
      tilePane.setPrefSize(50, 50);
      tilePanes.put(position.hashCode(), tilePane);
      Text text = new Text(tile.getText());
      text.setFill(Color.BLACK);
      tilePane.getChildren().add(text);
      gridPane.add(tilePane, position.getX(), 9 - tile.getPosition().getY());
    }

    Platform.runLater(() -> updateBoard());
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

  private void drawPlayers() {
    HashSet<Player> players = game.getPlayers();
    for (Player player : players) {
      Vector2 position = player.getPosition();
      System.out.println(position.hashCode());
      StackPane tilePane = tilePanes.get(position.hashCode());
      double x = tilePane.getLayoutX();
      double y = tilePane.getLayoutY();
      System.out.println("x: " + x + ", y: " + y);
      Pane playerPane = new Pane();
      overlay.getChildren().add(playerPane);
      ImageView figureView = new ImageView(player.getFigure().getPath());
      figureView.setFitHeight(50);
      figureView.setPreserveRatio(true);
      figureView.setLayoutX(x);
      figureView.setLayoutY(y);
      playerPane.getChildren().add(figureView);
    }
  }

  private void updateBoard() {
    overlay.getChildren().clear();
    drawPlayers();
  }
}
