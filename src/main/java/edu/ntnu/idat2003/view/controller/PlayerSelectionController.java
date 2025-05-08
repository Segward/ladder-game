package edu.ntnu.idat2003.view.controller;

import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.repo.PlayerRepo;
import edu.ntnu.idat2003.view.component.BoardSelection;
import edu.ntnu.idat2003.view.component.FigureSelection;
import edu.ntnu.idat2003.view.component.PlayerSelection;
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PlayerSelectionController {

  private Pane root;
  private Button add;
  private Button resume;
  private HBox hBox;

  public PlayerSelectionController(Pane root, Button add, Button resume, HBox hBox) {
    this.root = root;
    this.add = add;
    this.resume = resume;
    this.hBox = hBox;
  }

  public void init() {
    add.setOnAction(this::onAddClick);
    resume.setOnAction(this::onResumeClick);
    updatePlayers();
  }

  public void onAddClick(ActionEvent event) {
    FigureSelection.init(root);
  }

  public void onResumeClick(ActionEvent event) {
    BoardSelection.init(root);
  }

  private void onDeleteClick(ActionEvent event, Player player) {
    PlayerRepo.removePlayer(player);
    PlayerSelection.init(root);
  }

  private StackPane createPlayerPane(Player player) {
    VBox playerInfo = new VBox(10);
    playerInfo.setAlignment(Pos.CENTER);

    Text playerName = new Text(player.getName());
    playerName.setStyle("-fx-text-fill: #ffffff;");

    ImageView imageView = new ImageView(player.getFigure().getPath());
    imageView.setFitWidth(100);
    imageView.setFitHeight(100);
    imageView.setPreserveRatio(true);

    Button deleteButton = new Button("Delete");
    deleteButton.setOnAction(e -> onDeleteClick(e, player));
    playerInfo.getChildren().addAll(playerName, imageView, deleteButton);

    StackPane playerPane = new StackPane(playerInfo);
    playerPane.setAlignment(Pos.CENTER);
    return playerPane;
  }

  private void updatePlayers() {
    HashSet<Player> playerSet = PlayerRepo.getPlayers();
    int size = playerSet.size();
    for (Player player : playerSet) {
      StackPane playerPane = createPlayerPane(player);
      playerPane.prefWidthProperty().bind(hBox.widthProperty().divide(size));
      playerPane.prefHeightProperty().bind(hBox.heightProperty());
      hBox.getChildren().add(playerPane);
    }
  }
}
