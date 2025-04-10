package edu.ntnu.idat2003.controller;

import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.geometry.Orientation;
import edu.ntnu.idat2003.model.Figure;
import edu.ntnu.idat2003.repo.FigureRepository;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.repo.PlayerRepository;
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
    HashSet<Figure> figureSet = FigureRepository.getFigures();
    if (figureSet.isEmpty()) {
      RepoFactory.makeFigures();    
    }

    updateFigures(); 
  }

  private void onSelectClick(ActionEvent event, Figure figure) {
    String name = playerName.getText();
    Player player = new Player(name, figure);
    Vector2 position = new Vector2(0, 0);
    player.setPosition(position);
    PlayerRepository.addPlayer(player);
    PlayerSelection.init(root);
  }
  
  private FlowPane createFigurePane(Figure figure) {
    FlowPane figurePane = new FlowPane();
    figurePane.setOrientation(Orientation.HORIZONTAL);
    Text name = new Text(figure.getColor());
    Button select = new Button("Select");
    select.setOnAction(e -> onSelectClick(e, figure));
    figurePane.getChildren().addAll(name, select);
    return figurePane;
  }

  private void updateFigures() {
    HashSet<Figure> figureSet = FigureRepository.getAvailableFigures();
    for (Figure figure : figureSet) {
      FlowPane figurePane = createFigurePane(figure);
      figures.getChildren().add(figurePane);
    }
  }  
}
