package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.LadderGameViewController;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

/**
 * Class which builds the scene for the ladder game. It doesn't include any logic but connects with
 * a controller. It is a game so it includes all the game logic and visual representation. It has
 * two main components: the canvas for game rendering, and the buttons on the right.
 */
public class LadderGameView {

  private final BorderPane root;
  private final Board board;

  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final double WIDTH_RATIO = 0.8;

  /**
   * Constructor for the LadderGame class.
   *
   * @param borderPane The root pane of the application.
   * @param board The board object representing the game board.
   */
  public LadderGameView(BorderPane borderPane, Board board) {
    this.root = borderPane;
    this.board = board;
  }

  /**
   * Initializes the ladder game screen. It sets up the layout and adds the necessary components,
   * including a roll dice button, exit game button, and a canvas for rendering the game.
   */
  public void init() {
    root.setCenter(null);

    Canvas canvas = new Canvas(WIDTH * WIDTH_RATIO, HEIGHT);
    StackPane canvasPane = new StackPane(canvas);

    Button rollDice = new Button("Roll Dice");
    rollDice.setPrefSize(150, 50);

    ImageView diceImage1 = new ImageView("/dice/default.png");
    diceImage1.setFitWidth(75);
    diceImage1.setFitHeight(75);
    diceImage1.setPreserveRatio(true);

    ImageView diceImage2 = new ImageView("/dice/default.png");
    diceImage2.setFitWidth(75);
    diceImage2.setFitHeight(75);
    diceImage2.setPreserveRatio(true);

    FlowPane dicePane = new FlowPane();
    dicePane.setPrefSize(160, 75);
    dicePane.setOrientation(Orientation.HORIZONTAL);
    dicePane.setAlignment(Pos.CENTER);
    dicePane.setHgap(10);
    dicePane.getChildren().addAll(diceImage1, diceImage2);

    Button exitGame = new Button("Exit Game");
    exitGame.setPrefSize(150, 50);

    FlowPane buttonPane = new FlowPane();
    buttonPane.setPrefSize(WIDTH * (1 - WIDTH_RATIO), HEIGHT);
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(50);
    buttonPane.getChildren().addAll(dicePane, rollDice, exitGame);

    HBox hBox = new HBox(canvasPane, buttonPane);
    hBox.setPrefSize(WIDTH, HEIGHT);
    root.setCenter(hBox);

    buttonPane.setStyle("-fx-background-color:rgb(174, 109, 109);");

    LadderGameViewController ladderGameController =
        new LadderGameViewController(root, canvas, board, diceImage1, diceImage2);
    ladderGameController.init(rollDice, exitGame);
  }
}
