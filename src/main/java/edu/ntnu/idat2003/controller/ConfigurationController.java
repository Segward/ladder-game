package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.PlayerReader;
import edu.ntnu.idat2003.io.PlayerWriter;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.view.MainFrame;
import edu.ntnu.idat2003.view.PlayerCreation;

import java.io.File;
import java.util.HashSet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class ConfigurationController {

  private final BorderPane root;
  private final HBox hBox;

  public ConfigurationController(BorderPane root, HBox hBox) {
    this.root = root;
    this.hBox = hBox;
  }

  public void init(
      Button returnButton,
      Button loadPlayersFromFile,
      Button savePlayersToFileButton,
      Button addPlayerButton) {

    returnButton.setOnAction(e -> onReturn());
    loadPlayersFromFile.setOnAction(e -> onLoadPlayersFromFile());
    savePlayersToFileButton.setOnAction(e -> onSavePlayersToFile());
    addPlayerButton.setOnAction(e -> onAddPlayer());
    updatePlayers();
  }

  public void onReturn() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  private void onSavePlayersToFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
    File file = fileChooser.showSaveDialog(root.getScene().getWindow());
    String path = file.getAbsolutePath();
    if (file != null) {
      PlayerWriter.savePlayersToFile(path);
    }
  }

  private void onLoadPlayersFromFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
    File file = fileChooser.showOpenDialog(root.getScene().getWindow());
    String path = file.getAbsolutePath();
    if (file != null) {
      PlayerWriter.loadPlayersFromFile(path);
      updatePlayers();
    }
  }

  public void onAddPlayer() {
    PlayerCreation playerCreation = new PlayerCreation(root);
    playerCreation.init();
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
    playerName.setStyle("-fx-font-size: 30px; -fx-fill: WHITE;");
    playerInfo.getChildren().add(playerName);

    ImageView figureImage = new ImageView(player.getFigure().getPath());
    figureImage.setFitWidth(50);
    figureImage.setFitHeight(50);
    figureImage.setPreserveRatio(true);
    playerInfo.getChildren().add(figureImage);

    Button deleteButton = new Button("X");
    deleteButton.setOnAction(e -> removePlayer(player));
    playerInfo.getChildren().add(deleteButton);

    return playerPane;
  }

  private void updatePlayers() {
    hBox.getChildren().clear();
    HashSet<Player> playerSet = PlayerReader.getPlayers();
    int size = playerSet.size();
    for (Player player : playerSet) {
      StackPane playerPane = createPlayerPane(player);
      playerPane.prefWidthProperty().bind(hBox.widthProperty().divide(size));
      playerPane.prefHeightProperty().bind(hBox.heightProperty());
      hBox.getChildren().add(playerPane);
    }
  }

  public void removePlayer(Player player) {
    HashSet<Player> players = PlayerReader.getPlayers();
    players.remove(player);
    PlayerWriter.removePlayer(player);
    hBox.getChildren().clear();
    updatePlayers();
  }
}
