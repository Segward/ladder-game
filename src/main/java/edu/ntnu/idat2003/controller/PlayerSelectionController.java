package edu.ntnu.idat2003.controller;


import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.layout.FlowPane;
import edu.ntnu.idat2003.component.PlayerSelection;
import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.component.BoardSelection;
import javafx.geometry.Orientation;
import java.util.HashSet;
import edu.ntnu.idat2003.component.FigureSelection;
import edu.ntnu.idat2003.repo.PlayerRepo;

public class PlayerSelectionController {

  private Pane root;
  private FlowPane players;
  private Button add;
  private Button resume;  
  
  public PlayerSelectionController(Pane root, FlowPane players, Button add, Button resume) {
    this.root = root;
    this.players = players;
    this.add = add;
    this.resume = resume;
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

  private FlowPane createPlayerPane(Player player) {
    FlowPane playerPane = new FlowPane();
    playerPane.setOrientation(Orientation.HORIZONTAL);
    Text name = new Text(player.getName());
    Button delete = new Button("Delete");
    delete.setOnAction(e -> onDeleteClick(e, player));
    playerPane.getChildren().addAll(name, delete);
    return playerPane;
  }

  private void updatePlayers() {
    HashSet<Player> playerSet = PlayerRepo.getPlayers();
    for (Player player : playerSet) {
      FlowPane playerPane = createPlayerPane(player);
      players.getChildren().add(playerPane);
    }
  }
}
