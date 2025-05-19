package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.io.FigureFactory;
import edu.ntnu.idat2003.io.FigureReader;
import edu.ntnu.idat2003.io.PlayerWriter;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.view.PlayerSelection;
import java.util.HashSet;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class FigureSelectionController {

  private Pane root;
  private HBox figures;
  private TextField playerName;

  public FigureSelectionController(Pane root, HBox figures, TextField playerName) {
    this.root = root;
    this.figures = figures;
    this.playerName = playerName;
  }

  public void init() {
    HashSet<Figure> figureSet = FigureReader.getFigures();
    if (figureSet.isEmpty()) {
      FigureFactory.makeFigures();
    }

    updateFigures();
  }

  private void onSelectClick(Figure figure) {
    String name = playerName.getText();
    if (name.isEmpty()) {
      System.out.println("Please enter a name.");
      return;
    }

    Player player = new Player(name, figure);
    Vector2 position = new Vector2(0, 0);
    player.setPosition(position);
    PlayerWriter.addPlayer(player);
    PlayerSelection.init(root);
  }

  private StackPane createFigurePane(Figure figure) {
    StackPane figurePane = new StackPane();
    figurePane.setAlignment(Pos.CENTER);
    figurePane.setMinSize(100, 50);

    VBox figureInfo = new VBox();
    figureInfo.setAlignment(Pos.CENTER);
    figureInfo.setSpacing(10);
    figurePane.getChildren().add(figureInfo);

    Text figureName = new Text(figure.getName());
    figureName.setStyle("-fx-font-size: 20px; -fx-fill: white;");
    figureInfo.getChildren().add(figureName);

    ImageView figureImage = new ImageView(figure.getPath());
    figureImage.setFitWidth(50);
    figureImage.setFitHeight(50);
    figureImage.setPreserveRatio(true);
    figureInfo.getChildren().add(figureImage);

    Button selectButton = new Button("Select");
    selectButton.setOnAction(e -> onSelectClick(figure));
    figureInfo.getChildren().add(selectButton);

    return figurePane;
  }

  private void updateFigures() {
    HashSet<Figure> figureSet = FigureReader.getAvailableFigures();
    int size = figureSet.size();
    for (Figure figure : figureSet) {
      StackPane figurePane = createFigurePane(figure);
      figurePane.prefWidthProperty().bind(figures.widthProperty().divide(size));
      figurePane.prefHeightProperty().bind(figures.heightProperty());
      figures.getChildren().add(figurePane);
    }
  }
}
