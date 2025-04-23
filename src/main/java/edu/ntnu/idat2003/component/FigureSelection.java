package edu.ntnu.idat2003.component;

import edu.ntnu.idat2003.controller.FigureSelectionController;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FigureSelection {
  public static void init(Pane root) {
    root.getChildren().clear();

    StackPane stackPane = new StackPane();
    root.getChildren().add(stackPane);
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);

    Image backImage = new Image("imag/cityBack.png");
    BackgroundImage background = new BackgroundImage(
      backImage,
       BackgroundRepeat.REPEAT,
       BackgroundRepeat.ROUND,
         BackgroundPosition.CENTER,
          null);
    stackPane.setBackground(new Background(background));

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);

    TextField playerName = new TextField();
    playerName.setPromptText("Enter player name");
    flowPane.getChildren().add(playerName);

    FigureSelectionController controller =
        new FigureSelectionController(root, flowPane, playerName);
    controller.init();
  }
}
