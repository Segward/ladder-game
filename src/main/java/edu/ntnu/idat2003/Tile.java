package edu.ntnu.idat2003;

import java.util.ArrayList;

public class Tile {
  private TileAction action;
  private Tile nextTile;
  private ArrayList<Player> players;

  public Tile(TileAction action, Tile nextTile) {
    this.action = action;
    this.nextTile = nextTile;
  }

  public void placePlayer(Player player) {
    players.add(player);
  }

  public void removePlayer(Player player) {
    players.remove(player);
  }

  public TileAction getAction() {
    return action;
  }

  public Tile getNextTile() {
    return nextTile;
  }
}
