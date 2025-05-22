package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.LadderGameController;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class LadderGame {

  private final BorderPane root;
  private final Board board;

  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final double WIDTH_RATIO = 0.8;

  public LadderGame(BorderPane borderPane, Board board) {
    this.root = borderPane;
    this.board = board;
  }

  public void init() {
    root.setCenter(null);

    Canvas canvas = new Canvas(WIDTH * WIDTH_RATIO, HEIGHT);
    StackPane canvasPane = new StackPane(canvas);

    Button rollDice = new Button("Roll Dice");
    rollDice.setPrefSize(100, 100);

    Button exitGame = new Button("Exit Game");
    exitGame.setPrefSize(100, 100);

    FlowPane buttonPane = new FlowPane();
    buttonPane.setPrefSize(WIDTH * (1 - WIDTH_RATIO), HEIGHT);
    buttonPane.setOrientation(Orientation.VERTICAL);
    buttonPane.setAlignment(Pos.CENTER);
    buttonPane.setVgap(10);
    buttonPane.getChildren().addAll(rollDice, exitGame);

    HBox hBox = new HBox(canvasPane, buttonPane);
    hBox.setPrefSize(WIDTH, HEIGHT);
    root.setCenter(hBox);

    buttonPane.setStyle("-fx-background-color:rgb(174, 109, 109);");

    LadderGameController ladderGameController = new LadderGameController(root, canvas, board);
    ladderGameController.init(rollDice, exitGame);
  }
}
