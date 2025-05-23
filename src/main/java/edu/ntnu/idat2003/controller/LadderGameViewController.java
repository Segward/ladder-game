package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.LadderGame;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.LadderGameObserver;
import edu.ntnu.idat2003.view.MainFrame;
import java.util.HashMap;
import java.util.HashSet;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Class representing the ladder game visual logic. Incluedes methods that draws the visual data.
 */
public class LadderGameViewController implements LadderGameObserver {

  private final BorderPane root;
  private final Canvas canvas;
  private final Board board;
  private final ImageView dice1;
  private final ImageView dice2;

  private final int columns = 10;
  private final int rows = 9;

  private final int cellWidth = 60;
  private final int cellHeight = 60;
  private final int cellPadding = 5;

  private LadderGame game;

  /**
   * Constructor for the LadderGameController class. Takes an BorderPane, Canvas and Board as
   * parameters.
   *
   * @param borderPane BorderPane representing the main Canvas
   * @param canvas Canvas representing the game visuales
   * @param board Board object representing the game board
   */
  public LadderGameViewController(
      BorderPane borderPane, Canvas canvas, Board board, ImageView dice1, ImageView dice2) {
    this.root = borderPane;
    this.canvas = canvas;
    this.board = board;
    this.dice1 = dice1;
    this.dice2 = dice2;
  }

  /**
   * Initilases the game data. Deffines the different button logics, collectes all players in a
   * HashSet, creates a new Ladder game object representing the game, and draws the game visuals.
   *
   * @param rollDice Button for rolling dice
   * @param exitGame Button for returning to main menue
   */
  public void init(Button rollDice, Button exitGame) {
    rollDice.setOnAction(e -> game.rollDice());
    exitGame.setOnAction(e -> exitGame());
    HashSet<Player> players = new HashSet<>();

    try {
      players = PlayerReader.getPlayers();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    game = new LadderGame(players, board, this);
    game.init();
    drawCanvas();
  }

  /** Method for transporting user to main manue. Initializes the init() method in MainFrame. */
  private void exitGame() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  /**
   * Draws the game board on the canvas. It clears the canvas, sets the background, and draws the
   * tiles, ladders, extradice actions then players. Previously used a gridpane, but now uses a
   * canvas for better performance. It draws the board first, overlays the actions, and then draws
   * the players on top. The canvas is cleared before each draw to avoid flickering.
   */
  private void drawCanvas() {
    GraphicsContext gc = canvas.getGraphicsContext2D();
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.setStroke(Color.BLACK);
    gc.setLineWidth(2);

    Image background = new Image("/background/spooky.jpg");
    gc.drawImage(background, 0, 0, canvas.getWidth(), canvas.getHeight());

    double gridWidth = columns * cellWidth;
    double gridHeight = rows * cellHeight;
    double offsetX = (canvas.getWidth() - gridWidth) / 2;
    double offsetY = (canvas.getHeight() - gridHeight) / 2;

    double arc = Math.min(cellWidth, cellHeight) * 0.3;
    double innerPadding = cellPadding;

    HashMap<Integer, Tile> tiles = game.getBoard().getTiles();
    for (Tile tile : tiles.values()) {
      int drawRow = rows - 1 - tile.getPosition().getY();
      int drawCol = tile.getPosition().getX();

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      if (tile.getAction() != null && tile.getAction() instanceof ExtraDiceAction) {
        gc.setFill(Color.YELLOW);
      } else if (tile.getAction() != null && tile.getAction() instanceof LadderAction) {
        if (((LadderAction) tile.getAction()).getDirection().equals("up")) {
          gc.setFill(Color.LIGHTGREEN);
        } else {
          gc.setFill(Color.DARKRED);
        }
      } else {
        gc.setFill(Color.BURLYWOOD);
      }

      gc.fillRoundRect(
          px, py, cellWidth - 2 * innerPadding, cellHeight - 2 * innerPadding, arc, arc);
    }

    for (LadderAction action : game.getBoard().getLadders()) {
      Vector2 start = action.getStart();
      Vector2 end = action.getDestination();
      String direction = action.getDirection();

      int startRow = rows - 1 - start.getY();
      int startCol = start.getX();

      int endRow = rows - 1 - end.getY();
      int endCol = end.getX();

      double startX = offsetX + startCol * cellWidth + cellWidth / 2;
      double startY = offsetY + startRow * cellHeight + cellHeight / 2;

      double endX = offsetX + endCol * cellWidth + cellWidth / 2;
      double endY = offsetY + endRow * cellHeight + cellHeight / 2;

      double dx = endX - startX;
      double dy = endY - startY;
      double length = Math.hypot(dx, dy);
      double ladderWidth = cellWidth * 0.3;

      double perpX = -dy / length;
      double perpY = dx / length;

      double sx1 = startX + perpX * ladderWidth / 2;
      double sy1 = startY + perpY * ladderWidth / 2;
      double ex1 = endX + perpX * ladderWidth / 2;
      double ey1 = endY + perpY * ladderWidth / 2;

      double sx2 = startX - perpX * ladderWidth / 2;
      double sy2 = startY - perpY * ladderWidth / 2;
      double ex2 = endX - perpX * ladderWidth / 2;
      double ey2 = endY - perpY * ladderWidth / 2;

      if (direction.equals("up")) {
        gc.setStroke(Color.GREEN);
      } else if (direction.equals("down")) {
        gc.setStroke(Color.RED);
      }

      gc.setLineWidth(8);
      gc.strokeLine(sx1, sy1, ex1, ey1);
      gc.strokeLine(sx2, sy2, ex2, ey2);

      int rungs = 4;
      for (int i = 1; i < rungs; i++) {
        double t = i / (double) rungs;
        double rx1 = sx1 + (ex1 - sx1) * t;
        double ry1 = sy1 + (ey1 - sy1) * t;
        double rx2 = sx2 + (ex2 - sx2) * t;
        double ry2 = sy2 + (ey2 - sy2) * t;
        gc.setLineWidth(4);
        gc.strokeLine(rx1, ry1, rx2, ry2);
      }
    }

    for (ExtraDiceAction action : game.getBoard().getExtraDice()) {
      Vector2 pos = action.getStart();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = pos.getX();

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      Image diceImage = new Image("/dice/default.png");
      gc.drawImage(diceImage, px + 16, py + 16, cellWidth - 30, cellHeight - 30);
    }

    for (Tile tile : tiles.values()) {
      int drawRow = rows - 1 - tile.getPosition().getY();
      int drawCol = tile.getPosition().getX();

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      gc.setFont(new Font("Arial Black", 12));
      gc.setTextAlign(TextAlignment.CENTER);
      gc.setTextBaseline(VPos.CENTER);

      String text = String.valueOf(tile.getText());
      double x = px + 12;
      double y = py + 12;

      gc.setStroke(Color.BLACK);
      gc.setLineWidth(2);
      gc.strokeText(text, x, y);

      gc.setFill(Color.WHITE);
      gc.fillText(text, x, y);
    }

    for (Player player : game.getPlayers()) {
      Vector2 pos = player.getPosition();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = pos.getX();

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      Image playerImage = new Image(player.getFigure().getPath());
      double size = cellWidth - 2 * innerPadding;
      gc.drawImage(playerImage, px + 12, py + 10, size - 25, size - 20);
    }
  }

  /**
   * This method is called when a player moves. It draws the canvas and checks if the player has
   * reached the end of the board. If so, it executes the tile action and moves to the next player.
   *
   * @param player The player who moved.
   * @param remainder The number of steps remaining for the player to move.
   */
  @Override
  public void onPlayerMoved(Player player, int remainder) {
    drawCanvas();
    if (remainder == 0) {
      game.executeTileAction();
      game.nextPlayer();
      return;
    }

    PauseTransition pause = new PauseTransition(Duration.seconds(0.05));
    pause.setOnFinished(e -> game.movePlayer(remainder - 1));
    pause.play();
  }

  /**
   * This method is called when a tile action is executed. It checks if the action is a LadderAction
   * and redraws the canvas after a short pause.
   *
   * @param player The player who executed the action.
   * @param action The tile action that was executed.
   */
  @Override
  public void onTileActionExecuted(Player player, TileAction action) {
    if (action instanceof LadderAction) {
      PauseTransition pause = new PauseTransition(Duration.seconds(0.05));
      pause.setOnFinished(e -> drawCanvas());
      pause.play();
    }
  }

  /**
   * This method is called when a player wins the game. It draws the canvas and displays a message
   * indicating the winner.
   *
   * @param player The player who won.
   */
  @Override
  public void onPlayerWon(Player player) {
    drawCanvas();

    GraphicsContext gc = canvas.getGraphicsContext2D();
    double width = canvas.getWidth();
    double height = canvas.getHeight();

    gc.clearRect(0, 0, width, height);

    String text = player.getName() + " WON!";

    gc.setFont(new Font("Arial Black", 40));
    gc.setFill(Color.GOLD);
    gc.setStroke(Color.ORANGE);
    gc.setLineWidth(4);

    DropShadow glow = new DropShadow();
    glow.setColor(Color.ORANGE);
    glow.setRadius(20);
    glow.setSpread(0.5);
    gc.applyEffect(glow);

    gc.setTextAlign(TextAlignment.CENTER);
    gc.setTextBaseline(VPos.CENTER);

    double x = (width) / 2;
    double y = (height) / 2;

    gc.strokeText(text, x, y);
    gc.fillText(text, x, y);

    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), canvas);
    scaleTransition.setFromX(1);
    scaleTransition.setFromY(1);
    scaleTransition.setToX(1.2);
    scaleTransition.setToY(1.2);
    scaleTransition.setCycleCount(ScaleTransition.INDEFINITE);
    scaleTransition.setAutoReverse(true);
    scaleTransition.play();
  }

  /**
   * Method for animating dice image. Take int as parameter. Utilizes a foor loop that initializes
   * Timeline to change to random dice image with setDiceImage() method. When loop is finished
   * changes dice image to int parameter.
   *
   * @param diceValue Int representing die side
   */
  @Override
  public void onDiceRolled(int diceValue) {
    int animationFrames = 10;
    Timeline timeline = new Timeline();
    for (int i = 0; i < animationFrames; i++) {
      timeline
          .getKeyFrames()
          .add(
              new KeyFrame(
                  Duration.seconds(i * 0.05), e -> setDiceImage((int) (Math.random() * 11) + 2)));
    }
    timeline
        .getKeyFrames()
        .add(new KeyFrame(Duration.seconds(animationFrames * 0.05), e -> setDiceImage(diceValue)));
    timeline.play();
  }

  /**
   * Method for setting dice image. Take int as parameter. Utilizes Math max and min to generate two
   * die values equal to diceValue. Then give each dice a new image that qeual to diceValue.
   *
   * @param diceValue Int representing dice side amount
   */
  private void setDiceImage(int diceValue) {
    int minFirst = Math.max(1, diceValue - 6);
    int maxFirst = Math.min(6, diceValue - 1);
    int dice1Value = minFirst + (int) (Math.random() * (maxFirst - minFirst + 1));
    int dice2Value = diceValue - dice1Value;
    Image image1 = new Image("/dice/" + dice1Value + "face.png");
    Image image2 = new Image("/dice/" + dice2Value + "face.png");
    dice1.setImage(image1);
    dice2.setImage(image2);
  }
}
