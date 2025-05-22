package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.FigureReader;
import edu.ntnu.idat2003.io.PlayerWriter;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.view.Configuration;
import java.util.HashSet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *  Class representing the playerCreation screen.
 *  Incueds medthod for interaction with visual logic.
 */
public class PlayerCreationController {

  private final BorderPane root;
  private final HBox hBox;
  private final TextField playerName;

  /**
   *  Constructor for the PlayerCreationController.
   *  Takes an BorderPane, HBox and TextFieald as Parameters.
   * 
   *  @param root BorderPane representing Main canvas
   *  @param hBox HBox representing the player creator screen
   *  @param playerName TextField where user creates player name
   */
  public PlayerCreationController(BorderPane root, HBox hBox, TextField playerName) {
    this.root = root;
    this.hBox = hBox;
    this.playerName = playerName;
  }

  /**
   *  Initilases screen visauls and deffines return button functionality.
   *  Takes Button as parameter, and deffinese onReturn() method as functionality,
   *  before displaying available figures.
   * 
   *  @param returnButton Button to return to config screen
   */
  public void init(Button returnButton) {
    returnButton.setOnAction(e -> onReturn());
    updateFigures();
  }

  /**
   *  Method for returning user to config screen.
   *  Creates new Configuration object and initilases objects init() method.
   */
  public void onReturn() {
    Configuration configuration = new Configuration(root);
    configuration.init();
  }

   /**
   *  Creates a StackPane for a singel figure display.
   *  Creates a stackpane that holds a VBox that
   *  holdes the Text player name and a button for selection,
   *  and a image of the figure.
   * 
   *  @param figure figure object to be displayed
   *  @return StackPane that inclued fiure data
   */
  private StackPane createFigurePane(Figure figure) {
    StackPane figurePane = new StackPane();
    figurePane.setAlignment(Pos.CENTER);
    figurePane.setMinSize(100, 50);

    VBox figureInfo = new VBox();
    figureInfo.setAlignment(Pos.CENTER);
    figureInfo.setSpacing(10);
    figurePane.getChildren().add(figureInfo);

    Text figureName = new Text(figure.getName());
    figureName.setStyle("-fx-font-size: 30px; -fx-fill: WHITE;");
    figureInfo.getChildren().add(figureName);

    ImageView figureImage = new ImageView(figure.getPath());
    figureImage.setFitWidth(50);
    figureImage.setFitHeight(50);
    figureImage.setPreserveRatio(true);
    figureInfo.getChildren().add(figureImage);

    Button selectButton = new Button("Select");
    selectButton.setOnAction(e -> addPlayer(figure));
    figureInfo.getChildren().add(selectButton);

    return figurePane;
  }

    /**
   *  Method for updating displayed figures.
   *  Utilazes a for loop to go through each remaining figure
   *  and creates a display pane for them. 
   */
  private void updateFigures() {
    hBox.getChildren().clear();
    HashSet<Figure> figureSet = FigureReader.getAvailableFigures();
    int size = figureSet.size();
    for (Figure figure : figureSet) {
      StackPane figurePane = createFigurePane(figure);
      figurePane.prefWidthProperty().bind(hBox.widthProperty().divide(size));
      figurePane.prefHeightProperty().bind(hBox.heightProperty());
      hBox.getChildren().add(figurePane);
    }
  }

  /**
   *  Method for adding player to game.
   *  Takes Figure as a parameter.
   *  Checks if TextField is empty, if it is informes the player that is can not.
   *  If not creates a new player with a vector2 object as position,
   *  then adds new player to save player file.
   *  Then creates new Configuration object and initilases objects init() method.
   * 
   *  @param figure
   */
  public void addPlayer(Figure figure) {
    String name = playerName.getText();
    if (name.isEmpty()) {
      System.out.println("Please enter a name.");
      return;
    }

    Player player = new Player(name, figure);
    Vector2 position = new Vector2(0, 0);
    player.setPosition(position);
    PlayerWriter.addPlayer(player);

    Configuration configuration = new Configuration(root);
    configuration.init();
  }
}
