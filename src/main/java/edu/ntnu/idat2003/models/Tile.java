package edu.ntnu.idat2003.models;

import edu.ntnu.idat2003.tileactions.TileAction;

public class Tile {
  private TileAction action;
  private int position;

  /**
   *  Constructor for tile class,
   *  defines the tiles position on the bord
   *  and action based on parameters.
   * 
   *  @param position int representing tile position
   *  @param action tileaction object
   */
  public Tile(int position, TileAction action) {
    this.action = action;
    this.position = position;
  }

  /**
   *  Constructor for tile class,
   *  only defines tile position on bord
   *  based on parameter.
   * 
   *  @param position int representing tile position 
   */
  public Tile(int position) {
    this.action = null;
    this.position = position;
  }

  /**
   *  Getter for next tiles position.
   * 
   *  @return int representing next tiles position
   */
  public int getNextTile() {
    return position + 1;
  }

  /**
   *  Cheacks if tile object has a tile action.
   * 
   *  @return true if tileAction is not null, false if not
   */
  public boolean hasAction() {
    return action != null;
  }

  /**
   *  Getter for tile action,
   *  action that effects player on tile.
   * 
   *  @return tile action object
   */
  public TileAction getAction() {
    return action;
  }

  /**
   *  Getter for tile position on bord.
   * 
   *  @return int representing tile position
   */
  public int getPosition() {
    return position;
  }
}
