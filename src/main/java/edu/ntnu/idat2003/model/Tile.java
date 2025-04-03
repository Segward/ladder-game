package edu.ntnu.idat2003.model;

public class Tile {
  private TileAction action;
  private int position;

  public Tile(int position, TileAction action) {
    this.action = action;
    this.position = position;
  }

  public Tile(int position) {
    this.action = null;
    this.position = position;
  }

  public int getNextTile() {
    return position + 1;
  }

  public boolean hasAction() {
    return action != null;
  }

  public TileAction getAction() {
    return action;
  }

  public int getPosition() {
    return position;
  }
}
