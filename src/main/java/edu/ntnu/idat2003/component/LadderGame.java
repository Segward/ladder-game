package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.LadderGameController;
import edu.ntnu.idat2003.model.Board;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LadderGame {
  public static void init(Pane root, Board board) {
    root.getChildren().clear();

    FlowPane menuPane = new FlowPane();
    menuPane.prefWidthProperty().bind(root.widthProperty());
    menuPane.prefHeightProperty().bind(root.heightProperty());
    menuPane.setAlignment(Pos.CENTER);
    menuPane.setStyle("-fx-background-color: gray;");
    menuPane.setOrientation(Orientation.VERTICAL);
    root.getChildren().add(menuPane);

    HBox hBox = new HBox();
    root.getChildren().add(hBox);

    FlowPane boardPane = new FlowPane();
    hBox.getChildren().add(boardPane);
    boardPane.setStyle("-fx-background-color: WHITE;");
    boardPane.prefWidthProperty().bind(root.widthProperty().multiply(0.7));
    boardPane.prefHeightProperty().bind(root.heightProperty());
    boardPane.setOrientation(Orientation.VERTICAL);
    boardPane.setAlignment(Pos.CENTER);

    FlowPane sidePane = new FlowPane();
    sidePane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    sidePane.prefHeightProperty().bind(root.heightProperty());
    sidePane.setId("sidepane");
    hBox.getChildren().add(sidePane);

    GridPane gridPane = new GridPane();
    gridPane.setPadding(new Insets(5, 5, 5, 5));
    gridPane.setVgap(5);
    gridPane.setHgap(5);
    boardPane.getChildren().add(gridPane);

    Button stopGame = new Button("End game");
    sidePane.getChildren().add(stopGame);

    Text gameTitle = new Text("Hello, World!");
    sidePane.getChildren().add(gameTitle);

    Image diceImage = new Image(LadderGame.class.getResource("/dice/default.png").toExternalForm());
    ImageView diceView = new ImageView(diceImage);
    diceView.setFitHeight(200);
    diceView.setPreserveRatio(true);

    Button rollDice = new Button();
    rollDice.setGraphic(diceView);
    rollDice.setPrefSize(50, 50);
    rollDice.setStyle("-fx-background-color: transparent;");
    sidePane.getChildren().add(rollDice);

    LadderGameController controller =
        new LadderGameController(root, board, gameTitle, gridPane, rollDice, stopGame);
    controller.init();
  }
}
