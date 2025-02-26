package edu.ntnu.idat2003;

public class Tile {
  private TileAction action;

  public Tile(TileAction action) {
    this.action = action;
  }

  public Tile() {
    this.action = null;
  }

  public boolean hasAction() {
    return action != null;
  }

  public TileAction getAction() {
    return action;
  }
}
