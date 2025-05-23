package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.QuizGameViewController;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * Class representing the visual panes used in the Quiz game. The class constructs the different
 * panes, text and buttons which will be displayed during the Quiz game. This class is similar to
 * ladder game, as it is based on it.
 */
public class QuizGameView {

  private final BorderPane root;
  private final Board board;

  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final double WIDTH_RATIO = 0.8;

  /**
   * Constructor for the QuizGame class.
   *
   * @param borderPane The main layout of the application.
   * @param board The game board.
   */
  public QuizGameView(BorderPane borderPane, Board board) {
    this.root = borderPane;
    this.board = board;
  }

  /**
   * Initializes the visual components of the Quiz game. It sets up the layout and adds the
   * necessary components, including a roll dice button, exit game button, and a canvas for
   * rendering the game.
   */
  public void init() {
    root.setCenter(null);

    Canvas canvas = new Canvas(WIDTH * WIDTH_RATIO, HEIGHT);
    StackPane canvasPane = new StackPane(canvas);

    StackPane overlayPane = new StackPane();
    overlayPane.setMaxSize(400, 200);
    overlayPane.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10;");

    StackPane.setAlignment(overlayPane, Pos.CENTER);
    canvasPane.getChildren().add(overlayPane);

    FlowPane overlayPaneLayout = new FlowPane();
    overlayPaneLayout.setOrientation(Orientation.VERTICAL);
    overlayPaneLayout.setAlignment(Pos.CENTER);
    overlayPaneLayout.setVgap(10);
    overlayPane.getChildren().add(overlayPaneLayout);

    Text questionText = new Text("Question");
    questionText.setStyle("-fx-font-size: 20px; -fx-fill: BLACK;");
    questionText.setTextAlignment(TextAlignment.CENTER);
    questionText.setWrappingWidth(400);

    TextField answerField = new TextField();
    answerField.setPromptText("Enter your answer");
    answerField.setMaxWidth(400);

    Button submitAnswer = new Button("Submit Answer");
    submitAnswer.setPrefSize(400, 50);
    overlayPaneLayout.getChildren().addAll(questionText, answerField, submitAnswer);

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
    buttonPane.setId("sidePane");
    buttonPane.setPrefSize(WIDTH * (1 - WIDTH_RATIO), HEIGHT);
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(50);
    buttonPane.getChildren().addAll(dicePane, rollDice, exitGame);

    HBox hBox = new HBox(canvasPane, buttonPane);
    hBox.setPrefSize(WIDTH, HEIGHT);
    root.setCenter(hBox);

    buttonPane.setStyle("-fx-background-color:rgb(174, 109, 109);");

    QuizGameViewController partyGameController =
        new QuizGameViewController(
            root,
            canvas,
            board,
            overlayPane,
            questionText,
            answerField,
            rollDice,
            submitAnswer,
            diceImage1,
            diceImage2);
    partyGameController.init(exitGame);
  }
}
