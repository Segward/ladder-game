package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.BoardSelectionController;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardSelection {
  public static void init(Pane root, int gameType) {
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

    BoardSelectionController controller = new BoardSelectionController(root, hBox, gameType);
    controller.init();
  }
}
