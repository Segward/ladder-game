package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.BoardSelectionController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class BoardSelection {

  private final int gameType;
  private final BorderPane root;

  private final int WIDTH = 1000;
  private final int HEIGHT = 800;
  private final double HEIGHT_RATIO = 0.5;

  public BoardSelection(BorderPane root, int gameType) {
    this.root = root;
    this.gameType = gameType;
  }

  public void init() {
    root.setCenter(null);

    StackPane stackPane = new StackPane();
    stackPane.setPrefSize(WIDTH, HEIGHT * (1 - HEIGHT_RATIO));
    stackPane.setAlignment(Pos.BOTTOM_CENTER);

    Button returnButton = new Button("Return");
    returnButton.setPrefSize(300, 50);
    stackPane.getChildren().add(returnButton);

    HBox hBox = new HBox();
    hBox.setPrefSize(WIDTH, HEIGHT);
    hBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(30);

    VBox vBox = new VBox(stackPane, hBox);
    vBox.setPrefSize(WIDTH, HEIGHT * HEIGHT_RATIO);
    root.setCenter(vBox);

    BoardSelectionController boardSelectionController =
        new BoardSelectionController(root, hBox, gameType);
    boardSelectionController.init(returnButton);
  }
}
