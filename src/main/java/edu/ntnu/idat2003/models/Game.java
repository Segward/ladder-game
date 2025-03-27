package edu.ntnu.idat2003.models;

import java.util.ArrayList;

import edu.ntnu.idat2003.tileactions.LadderAction;

public class Game {

  private ArrayList<Player> players;
  private Board board;
  private Dice dice;
  private Player currentPlayer;
  private int currentPlayerIndex;

  /**
   *  Method that create a bord with a set amount of tiles,
   *  uses a for loop to construct a new bord
   *  and a set amount of tiles to be added to the bord.
   */
  public void createBoard() {
    board = new Board();
    for (int i = 0; i < 100; i++) {
      Tile tile = new Tile(i);
      board.addTile(i, tile);
    }
    board.setTileAction(1, new LadderAction("Climb the ladder to tile 38", 38));
    board.setTileAction(4, new LadderAction("Climb the ladder to tile 14", 14));
    board.setTileAction(9, new LadderAction("Climb the ladder to tile 31", 31));
    board.setTileAction(16, new LadderAction("Climb the ladder to tile 6", 6));
    board.setTileAction(28, new LadderAction("Climb the ladder to tile 84", 84));
    board.setTileAction(21, new LadderAction("Climb the ladder to tile 42", 42));
    board.setTileAction(36, new LadderAction("Climb the ladder to tile 44", 44));
    board.setTileAction(47, new LadderAction("Climb the ladder to tile 26", 26));
    board.setTileAction(49, new LadderAction("Climb the ladder to tile 11", 11));
    board.setTileAction(51, new LadderAction("Climb the ladder to tile 67", 67));
    board.setTileAction(56, new LadderAction("Climb the ladder to tile 53", 53));
    board.setTileAction(62, new LadderAction("Climb the ladder to tile 19", 19));
    board.setTileAction(64, new LadderAction("Climb the ladder to tile 60", 60));
    board.setTileAction(71, new LadderAction("Climb the ladder to tile 91", 91));
    board.setTileAction(80, new LadderAction("Climb the ladder to tile 100", 100));
  }

  /**
   *  Creates a new dice object to be thrown 6 times.
   */
  public void createDice() {
    dice = new Dice(6);
  }

  /**
   *  Adds a player to the game,
   *  checks if the player object is not null
   *  before adding to the game player array.
   * 
   *  @param player player to be added to the game
   */
  public void addPlayer(Player player) {
    if (players == null) {
      players = new ArrayList<>();
    }
    players.add(player);
  }

  /**
   *  Initializes the game,
   *  uses a loop to keep the game online as long as boolean is true.
   *  Circles thru player effecting methods for a singel player,
   *  before doing the same with next player.
   *  Loop ends when player goal is reached, and boolen is set to false.
   */
  public void startGame() {
    boolean gameIsRunning = true;
    currentPlayerIndex = 0;
    while (gameIsRunning) {
      currentPlayer = players.get(currentPlayerIndex);
      int steps = dice.roll();
      currentPlayer.move(steps);
      int position = currentPlayer.getPosition();
      System.out.println(currentPlayer.getName() + " is at position " + position);
      if (position >= board.getTileCount() - 1) {
        System.out.println(currentPlayer.getName() + " has won the game!");
        gameIsRunning = false;
      }
      Tile tile = board.getTile(position);
      if (tile != null && tile.hasAction()) {
        tile.getAction().execute(currentPlayer);
      }
      nextPlayer();
    }
  }

  /**
   *  Method for choosing player,
   *  if player choosen exceeds player amount
   *  player is set to player 1.
   */
  public void nextPlayer() {
    currentPlayerIndex++;
    if (currentPlayerIndex >= players.size()) {
      currentPlayerIndex = 0;
    }
  }

  /**
   *  Getter for object representing the game bord.
   * 
   *  @return bord object
   */
  public Board getBoard() {
    return board;
  }

  /**
   *  Getter for object representing dice throws.
   * 
   *  @return dice object
   */
  public Dice getDice() {
    return dice;
  }

  /**
   *  Getter for current player
   *  that is interacting with game.
   * 
   *  @return player interacting with game
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   *  Getter for current player number
   *  that is interacting with game.
   * 
   *  @return int representing player numb
   */
  public int getCurrentPlayerIndex() {
    return currentPlayerIndex;
  }

  /**
   *  Getter for accessing all the players,
   *  all player are located in a player array
   * 
   *  @return player array
   */
  public ArrayList<Player> getPlayers() {
    return players;
  }
}
