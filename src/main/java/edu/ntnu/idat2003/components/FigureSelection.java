package edu.ntnu.idat2003.components;

import edu.ntnu.idat2003.models.Figure;
import edu.ntnu.idat2003.models.Player;
import edu.ntnu.idat2003.services.GsonService;
import java.util.HashSet;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class FigureSelection {
  public static void init(Pane root) {
    // Clear the pane
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

    HashSet<Figure> figures = new HashSet<>();
    figures = GsonService.getAvailableFigures();

    for (Figure figure : figures) {
      FlowPane figurePane = new FlowPane();
      figurePane.setOrientation(Orientation.HORIZONTAL);
      figurePane.setAlignment(Pos.CENTER);

      Button selectFigure = new Button(figure.getColor());
      figurePane.getChildren().add(selectFigure);
      flowPane.getChildren().add(figurePane);

      selectFigure.setOnAction(e -> addPlayer(playerName.getText(), figure, root));
    }
  }

  public static void addPlayer(String playerName, Figure figure, Pane root) {
    if (playerName.isEmpty()) {
      return;
    }

    Player player = new Player(playerName, figure);
    GsonService.savePlayer(player);
    PlayerSelection.init(root);
  }
}