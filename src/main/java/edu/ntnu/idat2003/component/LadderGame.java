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

    FlowPane playerPane = new FlowPane();
    hBox.getChildren().add(playerPane);
    playerPane.setStyle("-fx-background-color: beige;");
    playerPane.prefWidthProperty().bind(root.widthProperty().multiply(0.3));
    playerPane.prefHeightProperty().bind(root.heightProperty());
    playerPane.setOrientation(Orientation.VERTICAL);

    GridPane bord = new GridPane();
    bord.setPadding(new Insets(5, 5, 5, 5));
    bord.setVgap(5);
    bord.setHgap(5);
    bord.setStyle("-fx-background-color: gray");
    boardPane.getChildren().add(bord);

    Button endGameButton = new Button("End game");
    playerPane.getChildren().add(endGameButton);

    Text text = new Text("Hello, World!");
    playerPane.getChildren().add(text);

    Image diceImage =
        new Image(LadderGame.class.getResource("/firstDice.png").toExternalForm());
    ImageView diceView = new ImageView(diceImage);
    diceView.setFitHeight(100);
    diceView.setPreserveRatio(true);

    Button rollDiceButton = new Button();
    rollDiceButton.setGraphic(diceView);
    rollDiceButton.setPrefSize(50, 50);
    rollDiceButton.setStyle("-fx-background-color: transparent;");
    playerPane.getChildren().add(rollDiceButton);

    LadderGameController controller =
        new LadderGameController(root, board, text, bord, rollDiceButton, endGameButton);
    controller.init();
  }
}
