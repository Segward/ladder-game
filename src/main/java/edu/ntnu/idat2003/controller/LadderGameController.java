package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.BoardReader;
import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.LadderGame;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.LadderGameObserver;
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

public class LadderGameController implements LadderGameObserver {

  private final BorderPane root;
  private final Canvas canvas;

  private final int columns = 10;
  private final int rows = 9;

  private final int cellWidth = 60;
  private final int cellHeight = 60;
  private final int cellPadding = 5;

  private LadderGame game;

  public LadderGameController(BorderPane borderPane, Canvas canvas) {
    this.root = borderPane;
    this.canvas = canvas;
  }

  public void init(Button rollDice, Button exitGame) {
    rollDice.setOnAction(e -> game.rollDice());
    exitGame.setOnAction(e -> exitGame());

    HashSet<Player> players = PlayerReader.getPlayers();
    HashSet<Board> boards = BoardReader.getLadderBoards();
    Board board = boards.iterator().next();
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

    double gridWidth = columns * cellWidth;
    double gridHeight = rows * cellHeight;
    double offsetX = (canvas.getWidth() - gridWidth) / 2;
    double offsetY = (canvas.getHeight() - gridHeight) / 2;

    int number = 1;
    double arc = Math.min(cellWidth, cellHeight) * 0.3;
    double innerPadding =
        cellPadding; // padding inside each cell (space between cell border and content)

    for (int row = rows - 1; row >= 0; row--) {
      for (int col = 0; col < columns; col++) {
        int drawCol = ((rows - 1 - row) % 2 == 0) ? col : (columns - 1 - col);

        double x = offsetX + drawCol * cellWidth;
        double y = offsetY + row * cellHeight;

        // Draw cell background with padding inside cell
        gc.setFill(Color.WHITE);
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

      gc.setLineWidth(4);
      if (action.getDirection().equals("up")) {
        gc.setStroke(Color.GREEN);
      } else {
        gc.setStroke(Color.RED);
      }
      gc.strokeLine(startX, startY, endX, endY);
    }

    // Draw ExtraDiceAction circles
    for (ExtraDiceAction action : game.getBoard().getExtraDice()) {
      Vector2 pos = action.getStart();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = (drawRow % 2 == 0) ? pos.getX() : (columns - 1 - pos.getX());

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      gc.setFill(Color.YELLOW);
      double size = cellWidth - 2 * innerPadding;
      gc.fillOval(px + 10, py + 10, size - 20, size - 20);
    }

    // Draw players
    for (Player player : game.getPlayers()) {
      Vector2 pos = player.getPosition();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = (drawRow % 2 == 0) ? pos.getX() : (columns - 1 - pos.getX());

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
