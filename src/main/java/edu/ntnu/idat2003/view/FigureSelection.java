package edu.ntnu.idat2003.view;

import edu.ntnu.idat2003.controller.FigureSelectionController;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FigureSelection {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setId("mainframe");
    root.getChildren().add(stackPane);

    VBox vBox = new VBox();
    vBox.prefWidthProperty().bind(stackPane.widthProperty());
    vBox.prefHeightProperty().bind(stackPane.heightProperty());
    stackPane.getChildren().add(vBox);

    StackPane playerNamePane = new StackPane();
    playerNamePane.prefWidthProperty().bind(vBox.widthProperty());
    playerNamePane.prefHeightProperty().bind(vBox.heightProperty().multiply(0.5));
    playerNamePane.setAlignment(Pos.CENTER);
    vBox.getChildren().add(playerNamePane);

    TextField playerName = new TextField();
    playerName.setPromptText("Enter player name");
    playerName.setPrefSize(300, 50);
    playerName.setMaxSize(300, 50);
    playerNamePane.getChildren().add(playerName);

    HBox hBox = new HBox();
    hBox.prefWidthProperty().bind(vBox.widthProperty());
    hBox.prefHeightProperty().bind(vBox.heightProperty().multiply(0.5));
    hBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(20);
    vBox.getChildren().add(hBox);

    FigureSelectionController controller = new FigureSelectionController(root, hBox, playerName);
    controller.init();
  }
}
