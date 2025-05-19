package edu.ntnu.idat2003.model.board;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.TileAction;

/**
 *  Class representing the tiles used in Ladder Game.
 *  Consists of methods for interacting with Vector2 objects 
 *  representing tile cordinates and
 *  tile actions.
 * 
 */
public class Tile {
  private Vector2 position;
  private Vector2 nextPosition;
  private TileAction action;

  /**
   *  Constructor for the Tile class.
   *  Utilizes the parameters to set the tiles
   *  position on the bord, and its next neighboring tile.
   * 
   *  @param position Vector2 object representing position
   *  @param nextPosition Vector2 object representing neighboring tile
   */
  public Tile(Vector2 position, Vector2 nextPosition) {
    this.position = position;
    this.nextPosition = nextPosition;
  }
  
  /**
   *  Getter for the tile position.
   *  Retreves the tiles position on the game bord,
   *  represented by a Vector2 object.
   *
   *  @return Vector2 object preresenting position
   */
  public Vector2 getPosition() {
    return position;
  }

  /**
   *  Setter for the tile position.
   *  Takes an Vector2 object as parameter and
   *  sets the that as the tiles position.
   * 
   *  @param position position to be set
   */
  public void setPosition(Vector2 position) {
    this.position = position;
  }

  /**
   *  Getter for the next tiles position.
   *  Retreves the neighboring tiles position
   *  represented by a Vector2 object.
   * 
   *  @return neighboring tile position as a Vector2 object
   */
  public Vector2 getNextPosition() {
    return nextPosition;
  }

  /**
   *  Getter for the tile action.
   *  Retreves the tile action registerd to this
   *  spesific object/tile.
   * 
   *  @return TileAction Object representing tile action
   */
  public TileAction getAction() {
    return action;
  }

  /**
   *  Setter for tile action.
   *  Takes an TileAction parameter and sets
   *  that as this tile/objects tile action.
   * 
   *  @param action TileAction Object representing new tile action
   */
  public void setAction(TileAction action) {
    this.action = action;
  }

  /**
   *  Setter for next tile position.
   *  Takes an Vector2 objects representing cordinates
   *  for a tile on the game board, and sets that as
   *  the next tiles position. 
   * 
   *  @param nextPosition new neighboring tile position as a Vector2 object
   */
  public void setNextPosition(Vector2 nextPosition) {
    this.nextPosition = nextPosition;
  } 
}
