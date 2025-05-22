package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.QuizGame;
import edu.ntnu.idat2003.model.Tile;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.QuestionAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.QuizGameObserver;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * The QuizGameController class is responsible for managing the user interface and interactions of
 * the quiz game. It handles player movements, tile actions, and game events.
 */
public class QuizGameController implements QuizGameObserver {

  private final BorderPane root;
  private final Canvas canvas;
  private final Board board;
  private final StackPane overlayPane;
  private final Text questionText;
  private final TextField answerField;
  private final Button rollDice;
  private final Button submitAnswer;
  private final ImageView dice1;
  private final ImageView dice2;

  private final int columns = 10;
  private final int rows = 9;

  private final int cellWidth = 60;
  private final int cellHeight = 60;
  private final int cellPadding = 5;

  private QuizGame game;
  private boolean isQuestionAction = false;

  /**
   * Constructor for the QuizGameController class.
   *
   * @param borderPane The main layout of the application.
   * @param canvas The canvas where the game board is drawn.
   * @param board The game board.
   * @param overlayPane The overlay pane for displaying questions.
   * @param questionText The text field for displaying questions.
   * @param answerField The text field for player answers.
   * @param rollDice The button to roll the dice.
   * @param submitAnswer The button to submit answers.
   * @param dice1 The first dice image view.
   * @param dice2 The second dice image view.
   */
  public QuizGameController(
      BorderPane borderPane,
      Canvas canvas,
      Board board,
      StackPane overlayPane,
      Text questionText,
      TextField answerField,
      Button rollDice,
      Button submitAnswer,
      ImageView dice1,
      ImageView dice2) {
    this.root = borderPane;
    this.canvas = canvas;
    this.board = board;
    this.overlayPane = overlayPane;
    this.questionText = questionText;
    this.answerField = answerField;
    this.rollDice = rollDice;
    this.submitAnswer = submitAnswer;
    this.dice1 = dice1;
    this.dice2 = dice2;
  }

  /**
   * Initializes the game controller with the provided exit button. It sets up the game then draws
   * the board.
   *
   * @param exitGame The button to exit the game. It's only used once.
   */
  public void init(Button exitGame) {
    rollDice.setOnAction(e -> game.rollDice());
    exitGame.setOnAction(e -> exitGame());
    overlayPane.setVisible(false);

    HashSet<Player> players = PlayerReader.getPlayers();
    game = new QuizGame(players, board, this);
    game.init();
    drawCanvas();
  }

  /** Exits the game and returns to the main menu. */
  private void exitGame() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  /**
   * Draws the game board on the canvas. It clears the canvas, sets the background, and draws the
   * tiles, players, and questions. Previously used a gridpane, but now uses a canvas for better
   * performance. It draws the board first, overlays the actions, and then draws the players on top.
   * The canvas is cleared before each draw to avoid flickering.
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

      gc.setFill(Color.BURLYWOOD);
      gc.fillRoundRect(
          px, py, cellWidth - 2 * innerPadding, cellHeight - 2 * innerPadding, arc, arc);

      gc.setFill(Color.BLACK);
      gc.setFont(new Font("Arial Black", 12));
      gc.setTextAlign(TextAlignment.CENTER);
      gc.setTextBaseline(VPos.CENTER);
      gc.fillText(String.valueOf(tile.getText()), px + 12, py + 12);
    }

    for (QuestionAction action : game.getBoard().getQuestions()) {
      Vector2 pos = action.getStart();

      int drawRow = rows - 1 - pos.getY();
      int drawCol = pos.getX();

      double px = offsetX + drawCol * cellWidth + innerPadding;
      double py = offsetY + drawRow * cellHeight + innerPadding;

      Image playerImage = new Image("/icons/question.png");
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

  /**
   * Handles the player movement. It draws the canvas and checks if the player has moved to a new
   * tile. If the player has finished moving, it executes the tile action. This method also has a
   * timer of 50 milliseconds so that it looks animated.
   *
   * @param player The player who moved.
   * @param remainder The number of moves remaining.
   */
  @Override
  public void onPlayerMoved(Player player, int remainder) {
    drawCanvas();
    if (remainder == 0) {
      game.executeTileAction();
      if (!isQuestionAction) {
        game.nextPlayer();
      }
      return;
    }

    PauseTransition pause = new PauseTransition(Duration.seconds(0.05));
    pause.setOnFinished(e -> game.movePlayer(remainder - 1));
    pause.play();
  }

  /**
   * This method updates the game state for questions then moves on to the next player.
   *
   * @param player The player who executed the action.
   * @param action The tile action executed.
   */
  @Override
  public void onTileActionExecuted(Player player, TileAction action) {
    isQuestionAction = false;
    game.nextPlayer();
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
   * 
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

  @Override
  public void onQuestion(Player player, QuestionAction action) {
    isQuestionAction = true;
    rollDice.setDisable(true);
    overlayPane.setVisible(true);
    questionText.setText(action.getQuestion());
    answerField.setText("");
    submitAnswer.setOnAction(e -> onAnswer(player, action));
  }

  private void onAnswer(Player player, QuestionAction action) {
    action.setGiven(answerField.getText());
    action.execute(player);
    onTileActionExecuted(player, action);
    rollDice.setDisable(false);
    overlayPane.setVisible(false);
  }
}
