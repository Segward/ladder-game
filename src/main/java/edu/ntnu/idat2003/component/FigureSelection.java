package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import java.util.HashSet;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import edu.ntnu.idat2003.controller.FigureSelectionController;

public class FigureSelection {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    root.getChildren().add(stackPane);
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);

    TextField playerName = new TextField();
    playerName.setPromptText("Enter player name");
    flowPane.getChildren().add(playerName);
  
    FigureSelectionController controller = new FigureSelectionController(root, flowPane, playerName);
    controller.init();
  }
}
