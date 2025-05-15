package edu.ntnu.idat2003.view.controller;

import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.repo.PlayerRepo;
import edu.ntnu.idat2003.view.component.BoardSelection;
import edu.ntnu.idat2003.view.component.FigureSelection;
import edu.ntnu.idat2003.view.component.PlayerSelection;
import java.util.HashSet;
import java.util.Stack;
import javafx.stage.FileChooser;
import java.io.File;

import javafx.application.Platform;
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
  private Button loadFromFile;
  private Button saveToFile;

  public PlayerSelectionController(Pane root, Button add, Button resume, HBox hBox, Button loadFromFile, Button saveToFile) {
    this.root = root;
    this.add = add;
    this.resume = resume;
    this.hBox = hBox;
    this.loadFromFile = loadFromFile;
    this.saveToFile = saveToFile;
  }

  public void init() {
    add.setOnAction(e -> onAddClick());
    resume.setOnAction(e -> onResumeClick());
    loadFromFile.setOnAction(e -> onLoadFromFileClick());
    saveToFile.setOnAction(e -> onSaveToFileClick());

    HashSet<Player> playerSet = PlayerRepo.getPlayers();
    int size = playerSet.size();
    if (size == 5) {
      add.setDisable(true);
      add.setVisible(false);
    }

    updatePlayers();
    Platform.runLater(() -> root.requestLayout());
  }

  private void onSaveToFileClick() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
    File file = fileChooser.showSaveDialog(root.getScene().getWindow());
    String path = file.getAbsolutePath();
    if (file != null) {
      PlayerRepo.savePlayersToFile(path);
    }
  }

  private void onLoadFromFileClick() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
    File file = fileChooser.showOpenDialog(root.getScene().getWindow());
    String path = file.getAbsolutePath();
    if (file != null) {
      PlayerRepo.loadPlayersFromFile(path);
      PlayerSelection.init(root);
    }
  }

  private void onAddClick() {
    FigureSelection.init(root);
  }

  private void onResumeClick() {
    BoardSelection.init(root);
  }

  private void onDeleteClick(Player player) {
    PlayerRepo.removePlayer(player);
    PlayerSelection.init(root);
  }

  private StackPane createPlayerPane(Player player) {
    StackPane playerPane = new StackPane();
    playerPane.setAlignment(Pos.CENTER);
    playerPane.setMinSize(100, 50);

    VBox playerInfo = new VBox();
    playerInfo.setAlignment(Pos.CENTER);
    playerInfo.setSpacing(10);
    playerPane.getChildren().add(playerInfo);

    Text playerName = new Text(player.getName());
    playerName.setStyle("-fx-font-size: 20px; -fx-fill: white;");
    playerInfo.getChildren().add(playerName);

    ImageView figureImage = new ImageView(player.getFigure().getPath());
    figureImage.setFitWidth(50);
    figureImage.setFitHeight(50);
    figureImage.setPreserveRatio(true);
    playerInfo.getChildren().add(figureImage);

    Button deleteButton = new Button("X");
    deleteButton.setOnAction(e -> onDeleteClick(player));
    playerInfo.getChildren().add(deleteButton);

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
