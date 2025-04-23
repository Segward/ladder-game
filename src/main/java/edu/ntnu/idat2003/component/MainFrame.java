package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.MainFrameController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class MainFrame {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);
    root.getChildren().add(stackPane);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);

    Button startGameButton = new Button("Start Game");
    flowPane.getChildren().add(startGameButton);

    Button exitAppButton = new Button("Exit");
    flowPane.getChildren().add(exitAppButton);

    MainFrameController controller = new MainFrameController(root, startGameButton, exitAppButton);
    controller.init();
  }
}
