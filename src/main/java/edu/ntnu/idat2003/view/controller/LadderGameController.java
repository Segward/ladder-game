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
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class LadderGameController implements LadderGameObserver {
  private Pane root;
  private Board board;
  private Pane ladderPane;
  private GridPane boardGrid;
  private Button rollButton;
  private Button endButton;
  private LadderGame game;
  private MediaPlayer backgroundMediaPlayer;
  private MediaPlayer diceRollMediaPlayer;

  public LadderGameController(
      Pane root,
      Board board,
      Pane ladderPane,
      GridPane boardGrid,
      Button rollButton,
      Button endButton) {
    this.root = root;
    this.board = board;
    this.ladderPane = ladderPane;
    this.boardGrid = boardGrid;
    this.rollButton = rollButton;
    this.endButton = endButton;
  }

  public void init() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    game = new LadderGame(players, board, this);
    game.init();

    rollButton.setOnAction(e -> game.rollDice());
    endButton.setOnAction(e -> stop());

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
    rollButton.setDisable(true);
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
    rollButton.setGraphic(diceView);
  }

  private void drawLadder(Vector2 start, Vector2 destination) {

  }

  private void drawPlayer(Player player) {
    StackPane playerPane = new StackPane();
    playerPane.prefWidthProperty().bind(boardGrid.widthProperty().divide(10));
    playerPane.prefHeightProperty().bind(boardGrid.heightProperty().divide(9));
    ImageView playerImage = new ImageView(player.getFigure().getPath());
    playerImage.setFitHeight(50);
    playerImage.setPreserveRatio(true);
    playerPane.getChildren().add(playerImage);
    int column = player.getPosition().getX();
    int row = 9 - player.getPosition().getY();
    boardGrid.add(playerPane, column, row);
  }

  private void drawTile(Tile tile) {
    StackPane tilePane = new StackPane();
    tilePane.setStyle("-fx-background-color: GREEN;");
    tilePane.prefWidthProperty().bind(boardGrid.widthProperty().divide(10));
    tilePane.prefHeightProperty().bind(boardGrid.heightProperty().divide(9));
    Text tileText = new Text(tile.getText());
    tileText.setStyle("-fx-font-size: 20px; -fx-text-fill: WHITE;");
    tilePane.getChildren().add(tileText);
    int column = tile.getPosition().getX();
    int row = 9 - tile.getPosition().getY();
    boardGrid.add(tilePane, column, row);
  }

  private void updateBoard() {
    boardGrid.getChildren().clear();
    ladderPane.getChildren().clear();

    for (Tile tile : board.getTiles().values()) {
      drawTile(tile);
    }

    for (Player player : game.getPlayers()) {
      drawPlayer(player);
    }

    HashSet<LadderAction> ladders = board.getLadders();
    for (LadderAction ladder : ladders) {
      Vector2 start = ladder.getStart();
      Vector2 destination = ladder.getDestination();
      Platform.runLater(() -> drawLadder(start, destination));
    }
  }
}
