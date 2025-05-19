package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;

/**
 *  Class representing a tile action.
 *  Workes as a base class for different tile actions that 
 *  extends this class. Inclueds methods for defining
 *  tile action start cordinate. 
 */
public abstract class TileAction {
  protected Vector2 start;

  /**
   *  Constructor for TileAction class.
   *  Takes a Vector2 object as a parameter,
   *  and sets that as the start tile position
   *  for the action.
   * 
   *  @param start Vector2 object representing start position
   */
  public TileAction(Vector2 start) {
    this.start = start;
  }

  /**
   *  Getter for the start position.
   *  Retreves the Vector2 object saved localy to this class
   *  named start representing the tile action start position.
   * 
   * @return Vector2 object representing start position
   */
  public Vector2 getStart() {
    return start;
  }

  /**
   *  Abstract method for executing the tile action.
   * 
   * @param player Player object to be effected
   */
  public abstract void execute(Player player);
}