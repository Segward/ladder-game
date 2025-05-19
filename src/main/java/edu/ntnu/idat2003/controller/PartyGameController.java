package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.BoardReader;
import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.PartyGame;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.PartyGameObserver;
import edu.ntnu.idat2003.view.MainFrame;
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

public class PartyGameController implements PartyGameObserver {

  private final BorderPane root;
  private final Canvas canvas;

  private final int columns = 10;
  private final int rows = 9;

  private final int cellWidth = 60;
  private final int cellHeight = 60;
  private final int cellPadding = 5;

  private PartyGame game;

  public PartyGameController(BorderPane borderPane, Canvas canvas) {
    this.root = borderPane;
    this.canvas = canvas;
  }

  public void init(Button rollDice, Button exitGame) {
    rollDice.setOnAction(e -> game.rollDice());
    exitGame.setOnAction(e -> exitGame());

    HashSet<Player> players = PlayerReader.getPlayers();
    HashSet<Board> boards = BoardReader.getLadderBoards();
    Board board = boards.iterator().next();
    game = new PartyGame(players, board, null);
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

    int number = 1;
    double arc = Math.min(cellWidth, cellHeight) * 0.3;
    double innerPadding = cellPadding;

    for (int row = rows - 1; row >= 0; row--) {
      for (int col = 0; col < columns; col++) {
        int drawCol = ((rows - 1 - row) % 2 == 0) ? col : (columns - 1 - col);

        double x = offsetX + drawCol * cellWidth;
        double y = offsetY + row * cellHeight;

        // Draw cell background with padding inside cell
        gc.setFill(Color.GRAY);
        gc.fillRoundRect(
            x + innerPadding,
            y + innerPadding,
            cellWidth - 2 * innerPadding,
            cellHeight - 2 * innerPadding,
            arc,
            arc);

        gc.setStroke(Color.BLACK);
        gc.strokeRoundRect(
            x + innerPadding,
            y + innerPadding,
            cellWidth - 2 * innerPadding,
            cellHeight - 2 * innerPadding,
            arc,
            arc);

        // Draw cell number near top-left inside the cell with padding
        gc.setFill(Color.BLACK);
        gc.fillText(String.valueOf(number), x + innerPadding + 4, y + innerPadding + 16);

        number++;
      }
    }

    // Draw ladders
    for (LadderAction action : game.getBoard().getLadders()) {
      Vector2 start = action.getStart();
      Vector2 end = action.getDestination();

      // Flip rows because we draw from bottom to top
      int startRow = rows - 1 - start.getY();
      int startCol = ((startRow % 2) == 0) ? start.getX() : (columns - 1 - start.getX());

      int endRow = rows - 1 - end.getY();
      int endCol = ((endRow % 2) == 0) ? end.getX() : (columns - 1 - end.getX());

      double startX = offsetX + startCol * cellWidth + cellWidth / 2;
      double startY = offsetY + startRow * cellHeight + cellHeight / 2;

      double endX = offsetX + endCol * cellWidth + cellWidth / 2;
      double endY = offsetY + endRow * cellHeight + cellHeight / 2;

      // Calculate perpendicular offset for ladder width
      double dx = endX - startX;
      double dy = endY - startY;
      double length = Math.hypot(dx, dy);
      double ladderWidth = cellWidth * 0.3; // Adjust for ladder thickness

      // Perpendicular vector (normalized)
      double perpX = -dy / length;
      double perpY = dx / length;

      // Side 1
      double sx1 = startX + perpX * ladderWidth / 2;
      double sy1 = startY + perpY * ladderWidth / 2;
      double ex1 = endX + perpX * ladderWidth / 2;
      double ey1 = endY + perpY * ladderWidth / 2;

      // Side 2
      double sx2 = startX - perpX * ladderWidth / 2;
      double sy2 = startY - perpY * ladderWidth / 2;
      double ex2 = endX - perpX * ladderWidth / 2;
      double ey2 = endY - perpY * ladderWidth / 2;

      // Draw the two sides
      gc.setLineWidth(8);
      gc.setStroke(Color.SADDLEBROWN);
      gc.strokeLine(sx1, sy1, ex1, ey1);
      gc.strokeLine(sx2, sy2, ex2, ey2);

      // Draw rungs
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

    // Draw ExtraDiceAction circles
    for (ExtraDiceAction action : game.getBoard().getExtraDice()) {
      Vector2 pos = action.getStart();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = (drawRow % 2 == 0) ? pos.getX() : (columns - 1 - pos.getX());

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      Image playerImage = new Image("/dice/default.png");
      gc.drawImage(playerImage, px + 10, py + 10, cellWidth - 20, cellHeight - 20);
    }

    // Draw players
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

  @Override
  public void onQuizGameStarted(Player player) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onQuizGameStarted'");
  }

  @Override
  public void onQuizGameFinished(Player player, int score) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onQuizGameFinished'");
  }

  @Override
  public void onDiceWallGameStarted(Player player) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onDiceWallGameStarted'");
  }

  @Override
  public void onDiceWallGameFinished(Player player, int score) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'onDiceWallGameFinished'");
  }
}
