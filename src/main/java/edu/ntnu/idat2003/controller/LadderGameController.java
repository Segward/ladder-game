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
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class LadderGameController implements LadderGameObserver {

  private final BorderPane root;
  private final Canvas canvas;
  private final Board board;

  private final int columns = 10;
  private final int rows = 9;

  private final int cellWidth = 60;
  private final int cellHeight = 60;
  private final int cellPadding = 5;

  private LadderGame game;

  public LadderGameController(BorderPane borderPane, Canvas canvas, Board board) {
    this.root = borderPane;
    this.canvas = canvas;
    this.board = board;
  }

  public void init(Button rollDice, Button exitGame) {
    rollDice.setOnAction(e -> game.rollDice());
    exitGame.setOnAction(e -> exitGame());

    HashSet<Player> players = PlayerReader.getPlayers();
    game = new LadderGame(players, board, this);
    game.init();
    drawCanvas();
  }

  private void exitGame() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

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

      gc.setFill(Color.LIGHTGREEN);
      gc.fillRoundRect(
          px, py, cellWidth - 2 * innerPadding, cellHeight - 2 * innerPadding, arc, arc);

      gc.setFill(Color.BLACK);
      gc.setFont(new Font("Arial Black", 12));
      gc.setTextAlign(TextAlignment.CENTER);
      gc.setTextBaseline(VPos.CENTER);
      gc.fillText(String.valueOf(tile.getText()), px + 12, py + 12);
    }

    for (LadderAction action : game.getBoard().getLadders()) {
      Vector2 start = action.getStart();
      Vector2 end = action.getDestination();

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

      gc.setLineWidth(8);
      gc.setStroke(Color.SADDLEBROWN);
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
      int drawCol = (drawRow % 2 == 0) ? pos.getX() : (columns - 1 - pos.getX());

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      Image playerImage = new Image("/dice/default.png");
      gc.drawImage(playerImage, px + 10, py + 10, cellWidth - 20, cellHeight - 20);
    }

    for (Player player : game.getPlayers()) {
      Vector2 pos = player.getPosition();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = pos.getX();

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      Image playerImage = new Image(player.getFigure().getPath());
      double size = cellWidth - 2 * innerPadding;
      gc.drawImage(playerImage, px + 10, py + 10, size - 20, size - 20);
    }
  }

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

  @Override
  public void onTileActionExecuted(Player player, TileAction action) {
    if (action instanceof LadderAction) {
      PauseTransition pause = new PauseTransition(Duration.seconds(0.05));
      pause.setOnFinished(e -> drawCanvas());
      pause.play();
    }
  }

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

  @Override
  public void onDiceRolled(int diceValue) {}
}
