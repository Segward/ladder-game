package edu.ntnu.idat2003.models;

import java.util.HashMap;

import edu.ntnu.idat2003.tileactions.TileAction;

public class Board {
  private HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public void addTile(int number, Tile tile) {
    tiles.put(number, tile);
  }

  public void setTileAction(int number, TileAction action) {
    tiles.put(number, new Tile(action));
  }

  public Tile getTile(int number) {
    return tiles.get(number);
  }

  public int getTileCount() {
    return tiles.size();
  }
}
