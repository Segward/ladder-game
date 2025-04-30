package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.PlayerSelectionController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class PlayerSelection {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setId("mainframe");
    root.getChildren().add(stackPane);

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setId("playerSelectGrid");

    Text playerSelectText = new Text("Players");
    playerSelectText.setTextAlignment(TextAlignment.RIGHT);
    playerSelectText.setId("playerSelectText");
    
    FlowPane buttonFlow = new FlowPane();
    buttonFlow.setId("playerSelectMenu");
    buttonFlow.setOrientation(Orientation.VERTICAL);
    buttonFlow.setAlignment(Pos.CENTER);

    stackPane.getChildren().add(gridPane);

    Button add = new Button("Add Player");

    Button resume = new Button("Resume To Boards");
    buttonFlow.getChildren().add(resume);
    buttonFlow.getChildren().add(add);
    
    FlowPane playersFlow = new FlowPane();
    playersFlow.setAlignment(Pos.CENTER);
    playersFlow.setId("playerSelectMenu");

    gridPane.add(playerSelectText, 0, 0);
    gridPane.add(add, 0, 2);
    gridPane.add(resume, 1, 2);

    PlayerSelectionController controller =
        new PlayerSelectionController(root, gridPane, add, resume);
    controller.init();
  }
}
