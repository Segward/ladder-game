package edu.ntnu.idat2003.view.component;

import edu.ntnu.idat2003.view.controller.BoardSelectionController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BoardSelection {
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

    BoardSelectionController controller = new BoardSelectionController(root, hBox);
    controller.init();
  }
}
