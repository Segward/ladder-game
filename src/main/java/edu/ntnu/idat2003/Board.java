package edu.ntnu.idat2003;

import java.util.HashMap;

public class Board {
  private Tile startTile;
  private Tile endTile;
  private HashMap<Integer, Tile> tiles;

  public Board() {
    this.tiles = new HashMap<>();
  }

  public Tile getStartTile() {
    return startTile;
  }

  public Tile getEndTile() {
    return endTile;
  }

  public void addTile(int number, Tile tile) {
    tiles.put(number, tile);
  }

  public Tile getTile(int number) {
    return tiles.get(number);
  }
}
