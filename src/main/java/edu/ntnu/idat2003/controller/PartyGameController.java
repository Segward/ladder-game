package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.PartyGame;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.PartyGameObserver;
import edu.ntnu.idat2003.view.MainFrame;
import java.io.File;
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
import javafx.scene.text.Text;
import javafx.util.Duration;

public class PartyGameController implements PartyGameObserver {
  private Pane root;
  private Board board;
  private Pane ladderPane;
  private GridPane boardGrid;
  private Button rollButton;
  private Button endButton;
  private PartyGame game;
  private MediaPlayer backgroundMediaPlayer;
  private MediaPlayer diceRollMediaPlayer;

  public PartyGameController(
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
    HashSet<Player> players = PlayerReader.getPlayers();
    game = new PartyGame(players, board, this);
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

    PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
    pause.setOnFinished(e -> game.movePlayer(remainder - 1));
    pause.play();
  }

  @Override
  public void onTileActionExecuted(Player player, TileAction action) {
    if (action instanceof LadderAction) {
      PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
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

  @Override
  public void onQuizGameStarted(Player player) {}

  @Override
  public void onQuizGameFinished(Player player, int score) {}

  @Override
  public void onDiceWallGameStarted(Player player) {}

  @Override
  public void onDiceWallGameFinished(Player player, int score) {}

  private void updateDice(int diceValue) {
    ImageView diceView = new ImageView();
    diceView.setFitHeight(200);
    diceView.setPreserveRatio(true);

    Image diceImage = new Image("/dice/" + diceValue + "face.png");
    diceView.setImage(diceImage);
    rollButton.setGraphic(diceView);
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
    tilePane.setStyle("-fx-background-color: GREY; -fx-background-radius: 5px;");
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
  }
}
