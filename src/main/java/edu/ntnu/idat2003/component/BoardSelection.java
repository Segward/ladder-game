package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.BoardSelectionController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class BoardSelection {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setId("mainframe");
    root.getChildren().add(stackPane);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);

    BoardSelectionController controller = new BoardSelectionController(root, flowPane);
    controller.init();
  }
}
