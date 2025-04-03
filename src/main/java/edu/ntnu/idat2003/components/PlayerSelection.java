package edu.ntnu.idat2003.components;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.repo.PlayerRepository;
import java.util.HashSet;
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
    root.getChildren().add(stackPane);
    stackPane.prefWidthProperty().bind(root.widthProperty());
    stackPane.prefHeightProperty().bind(root.heightProperty());
    stackPane.setAlignment(Pos.CENTER);

    FlowPane flowPane = new FlowPane();
    stackPane.getChildren().add(flowPane);
    flowPane.setOrientation(Orientation.VERTICAL);
    flowPane.setAlignment(Pos.CENTER);

    HashSet<Player> players = new HashSet<>();
    // players = GsonService.getPlayers();

    for (Player player : players) {
      FlowPane playerPane = new FlowPane();
      playerPane.setOrientation(Orientation.HORIZONTAL);
      playerPane.setAlignment(Pos.CENTER);

      Button deletePlayer = new Button(player.getName());
      playerPane.getChildren().add(deletePlayer);
      flowPane.getChildren().add(playerPane);

      deletePlayer.setOnAction(
          e -> {
            // GsonService.deletePlayer(player);
            init(root);
          });
    }

    Button addPlayer = new Button("Add player");
    flowPane.getChildren().add(addPlayer);

    Button selectBoard = new Button("Select board");
    flowPane.getChildren().add(selectBoard);

    addPlayer.setOnAction(e -> FigureSelection.init(root));
    selectBoard.setOnAction(e -> BoardSelection.init(root));
  }
}