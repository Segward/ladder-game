package edu.ntnu.idat2003.controller;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Orientation;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.repo.FigureRepo;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.repo.PlayerRepo;
import edu.ntnu.idat2003.component.PlayerSelection;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import java.util.HashSet;
import javafx.event.ActionEvent;
import edu.ntnu.idat2003.repo.RepoFactory;
import edu.ntnu.idat2003.model.Vector2;

public class FigureSelectionController {

  private Pane root;
  private FlowPane figures;
  private TextField playerName;

  public FigureSelectionController(Pane root, FlowPane figures, TextField playerName) {
    this.root = root;
    this.figures = figures;
    this.playerName = playerName;
  }

  public void init() {
    HashSet<Figure> figureSet = FigureRepo.getFigures();
    if (figureSet.isEmpty()) {
      RepoFactory.makeFigures();    
    }

    updateFigures(); 
  }

  private void onSelectClick(ActionEvent event, Figure figure) {
    String name = playerName.getText();
    if (name.isEmpty()) {
      System.out.println("Please enter a name.");
      return;
    }    

    Player player = new Player(name, figure);
    Vector2 position = new Vector2(0, 0);
    player.setPosition(position);
    PlayerRepo.addPlayer(player);
    PlayerSelection.init(root);
  }
  
  private FlowPane createFigurePane(Figure figure) {
    FlowPane figurePane = new FlowPane();
    figurePane.setOrientation(Orientation.HORIZONTAL);
    Text name = new Text(figure.getColor());

    Image buttonImage = new Image
      (getClass().getResource("/" + figure.getColor()+".png").toExternalForm());
    ImageView buttonView = new ImageView(buttonImage);
    buttonView.setFitHeight(50);
    buttonView.setPreserveRatio(true);

    Button select = new Button("Select");
    select.setGraphic(buttonView);

    select.setOnAction(e -> onSelectClick(e, figure));
    figurePane.getChildren().addAll(name, select);
    return figurePane;
  }

  private void updateFigures() {
    HashSet<Figure> figureSet = FigureRepo.getAvailableFigures();
    for (Figure figure : figureSet) {
      FlowPane figurePane = createFigurePane(figure);
      figures.getChildren().add(figurePane);
    }
  }  
}
