package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.PlayerSelectionController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PlayerSelection {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setId("mainframe");
    root.getChildren().add(stackPane);

    FlowPane flowPane = new FlowPane();
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);
    stackPane.getChildren().add(flowPane);

    Button add = new Button("Add");
    flowPane.getChildren().add(add);

    Button resume = new Button("Resume");
    flowPane.getChildren().add(resume);

    PlayerSelectionController controller =
        new PlayerSelectionController(root, flowPane, add, resume);
    controller.init();
  }
}
