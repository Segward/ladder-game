package edu.ntnu.idat2003.view;

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
    stackPane.setId("mainframe");
    root.getChildren().add(stackPane);

    FlowPane flowPane = new FlowPane();
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);
    flowPane.setVgap(20);
    stackPane.getChildren().add(flowPane);

    Button startGameButton = new Button("Start Game");
    startGameButton.setPrefSize(300, 50);
    flowPane.getChildren().add(startGameButton);

    Button exitAppButton = new Button("Exit");
    exitAppButton.setPrefSize(300, 50);
    flowPane.getChildren().add(exitAppButton);

    MainFrameController controller = new MainFrameController(root, startGameButton, exitAppButton);
    controller.init();
  }
}
