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

/*
 *  Class representing the Configuration screen.
 */
public class ConfigurationController {

  private final BorderPane root;
  private final HBox hBox;

  /**
   *  Constructor for the ConfigurationController Class.
   *  Takes an BoarderPane and an Hbox as parameters.
   * 
   *  @param root BorderPane representing the main canvas
   *  @param hBox HBox representing the Configuration screen
   */
  public ConfigurationController(BorderPane root, HBox hBox) {
    this.root = root;
    this.hBox = hBox;
  }

  /**
   *  Initializes the functionality of buttons,
   *  as well as method for displaying selected characters.
   *  Deffines the methods each button initializes.
   * 
   * @param returnButton Button returns to main screen
   * @param loadPlayersFromFile Button for inserting new player file
   * @param savePlayersToFileButton Button creates new player file
   * @param addPlayerButton Button takes player to playerSelection
   */
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

  /**
   *  Method for transporting user to main manue.
   *  Initializes the init() method in MainFrame.
   */
  public void onReturn() {
    MainFrame mainFrame = new MainFrame(root);
    mainFrame.init();
  }

  /**
   *  Method for creating new player file.
   *  Creates new file and saves player data
   *  with methods from the PlayerWriter class.
   * 
   */
  private void onSavePlayersToFile() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV files", "*.csv"));
    File file = fileChooser.showSaveDialog(root.getScene().getWindow());
    String path = file.getAbsolutePath();
    if (file != null) {
      PlayerWriter.savePlayersToFile(path);
    }
  }

  /**
   *  Method for inserting new players in to game.
   *  Creates new file with data from user selected file.
   *  Then loades new players based on new file.
   */
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

  /**
   *  Takes user to add player screen
   *  Initializes the init() method in PlaterCreation.
   */
  public void onAddPlayer() {
    PlayerCreation playerCreation = new PlayerCreation(root);
    playerCreation.init();
  }

  /**
   *  Creates a StackPane for a singel Plater display.
   *  Creates a stackpane that holds a VBox that
   *  holdes the Text player name and a button for deletion,
   *  and a image of the player figure.
   * 
   *  @param player Player object to be displayed
   *  @return StackPane that inclued player data
   */
  private StackPane createPlayerPane(Player player) {
    StackPane playerPane = new StackPane();
    playerPane.setAlignment(Pos.CENTER);
    playerPane.setMinSize(100, 50);

    VBox playerInfo = new VBox();
    playerInfo.setAlignment(Pos.CENTER);
    playerInfo.setSpacing(10);
    playerPane.getChildren().add(playerInfo);

    Text playerName = new Text(player.getName());
    playerName.setStyle("-fx-font-size: 20px; -fx-fill: BLACK;");
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

  /**
   *  Method for updating displayed players.
   *  Utilazes a for loop to go through each player
   *  and creates a display pane for them. 
   */
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

  /**
   *  Method for removing player from game.
   *  Fetches all players in an HasSet then removes 
   *  parameter player and updates displayed players.
   * 
   * @param player Player object to be removed
   */
  public void removePlayer(Player player) {
    HashSet<Player> players = PlayerReader.getPlayers();
    players.remove(player);
    PlayerWriter.removePlayer(player);
    hBox.getChildren().clear();
    updatePlayers();
  }
}
