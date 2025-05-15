package edu.ntnu.idat2003.view.component;


import edu.ntnu.idat2003.view.controller.PlayerSelectionController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class PlayerSelection {
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

    FlowPane flowPane = new FlowPane();
    flowPane.prefWidthProperty().bind(vBox.widthProperty());
    flowPane.prefHeightProperty().bind(vBox.heightProperty().multiply(0.5));
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.BOTTOM_CENTER);
    flowPane.setVgap(10);
    vBox.getChildren().add(flowPane);

    Button resumeButton = new Button("Resume");
    resumeButton.setPrefSize(300, 50);
    flowPane.getChildren().add(resumeButton);

    Button loadFromFileButton = new Button("Load from file");
    loadFromFileButton.setPrefSize(300, 50);
    flowPane.getChildren().add(loadFromFileButton);

    Button saveToFileButton = new Button("Save to file");
    saveToFileButton.setPrefSize(300, 50);
    flowPane.getChildren().add(saveToFileButton);

    Button addPlayerButton = new Button("Add");
    addPlayerButton.setPrefSize(300, 25);
    flowPane.getChildren().add(addPlayerButton);

    HBox hBox = new HBox();
    hBox.prefWidthProperty().bind(vBox.widthProperty());
    hBox.prefHeightProperty().bind(vBox.heightProperty().multiply(0.5));
    vBox.getChildren().add(hBox);

    PlayerSelectionController controller =
        new PlayerSelectionController(root, addPlayerButton, resumeButton, hBox, loadFromFileButton, saveToFileButton);
    controller.init();
  }
}