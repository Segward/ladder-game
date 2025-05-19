package edu.ntnu.idat2003.controller;

import edu.ntnu.idat2003.model.Board;
import edu.ntnu.idat2003.model.LadderGame;
import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.tileactions.TileAction;
import edu.ntnu.idat2003.observer.PartyGameObserver;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;

public class PartyGameController implements PartyGameObserver {
  private Pane root;
  private Board board;
  private Pane ladderPane;
  private GridPane boardGrid;
  private Button rollButton;
  private Button endButton;
  private LadderGame game;
  private MediaPlayer backgroundMediaPlayer;
  private MediaPlayer diceRollMediaPlayer;

  public PartyGameController(
      Pane root,
      Board board,
      Pane ladderPane,
      GridPane boardGrid,
      Button rollButton,
      Button endButton) {
    this.root = root;
    this.board = board;
    this.ladderPane = ladderPane;
    this.boardGrid = boardGrid;
    this.rollButton = rollButton;
    this.endButton = endButton;
  }

  public void init() {}

  @Override
  public void onPlayerMoved(Player player, int remainder) {}

  @Override
  public void onTileActionExecuted(Player player, TileAction action) {}

  @Override
  public void onPlayerWon(Player player) {}

  @Override
  public void onDiceRolled(int diceValue) {}

  @Override
  public void onMinigameStarted(Player player) {}
}
