package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.GameSelectionController;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GameSelection {

  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setId("mainframe");
    root.getChildren().add(stackPane);

    HBox hBox = new HBox();
    hBox.prefWidthProperty().bind(stackPane.widthProperty());
    hBox.prefHeightProperty().bind(stackPane.heightProperty());
    hBox.setAlignment(Pos.CENTER);
    stackPane.getChildren().add(hBox);
    hBox.setSpacing(20);

    Button ladderGameButton = new Button("Ladder Game");
    ladderGameButton.setPrefSize(300, 50);
    hBox.getChildren().add(ladderGameButton);

    Button partyGameButton = new Button("Party Game");
    partyGameButton.setPrefSize(300, 50);
    hBox.getChildren().add(partyGameButton);

    GameSelectionController controller =
        new GameSelectionController(root, ladderGameButton, partyGameButton);
    controller.init();
  }
}
