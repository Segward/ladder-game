package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.LadderGameController;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class LadderGame {
  public static void init(Pane root, Board board) {
    root.getChildren().clear();

    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    StackPane boardPane = new StackPane();
    boardPane.setStyle("-fx-background-color: WHITE;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setAlignment(Pos.TOP_LEFT);
    hBox.getChildren().add(boardPane);

    StackPane tilesPane = new StackPane();
    tilesPane.prefWidthProperty().bind(boardPane.widthProperty());
    tilesPane.prefHeightProperty().bind(boardPane.heightProperty());
    boardPane.getChildren().add(tilesPane);

    GridPane boardGrid = new GridPane();
    boardGrid.setVgap(2);
    boardGrid.setHgap(2);
    boardGrid.prefWidthProperty().bind(tilesPane.widthProperty());
    boardGrid.prefHeightProperty().bind(tilesPane.heightProperty());
    tilesPane.getChildren().add(boardGrid);

    Pane ladderPane = new StackPane();
    ladderPane.prefWidthProperty().bind(boardGrid.widthProperty());
    ladderPane.prefHeightProperty().bind(boardGrid.heightProperty());
    boardPane.getChildren().add(ladderPane);

    FlowPane sidePane = new FlowPane();
    sidePane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    sidePane.prefHeightProperty().bind(root.heightProperty());
    sidePane.setId("sidepane");
    hBox.getChildren().add(sidePane);

    Image diceImage = new Image("/dice/default.png");
    ImageView diceView = new ImageView(diceImage);
    diceView.setFitHeight(200);
    diceView.setPreserveRatio(true);

    Button diceButton = new Button();
    diceButton.setGraphic(diceView);
    diceButton.setPrefSize(50, 50);
    diceButton.setStyle("-fx-background-color: transparent;");
    sidePane.getChildren().add(diceButton);

    Button endButton = new Button("End game");
    sidePane.getChildren().add(endButton);

    LadderGameController controller =
        new LadderGameController(root, board, ladderPane, boardGrid, diceButton, endButton);
    controller.init();
  }
}
