package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;

/**
 *  Class representing the Ladder action.
 *  Constists of methods for interacting with player position.
 * 
 */
public class LadderAction extends TileAction {
  private Vector2 destination;

  /**
   *  Constructor for the LadderAction class inherited from TileAction.
   *  Takes two sperate Vector2 objects as paramters,
   *  and states them as one as the start of the ladder
   *  and the other as the end. The end value is a local variabel
   *  by the name of destination.
   * 
   *  @param start Vector2 object representing start of ladder
   *  @param destination Vector2 object representing end of ladder
   */
  public LadderAction(Vector2 start, Vector2 destination) {
    super(start);
    this.destination = destination;
  }

  /**
   *  Method for changing player position.
   *  Takes a Player object as a parameter, and changes
   *  the players position to the local destination variabel
   *  saved in this class.
   * 
   *  @param player Player object to be moved
   */
  @Override
  public void execute(Player player) {
    player.setPosition(destination);
  }

  /**
   *  Getter for the destination vector.
   *  Retreves the Vector2 object saved locally as destination,
   *  representing the ladder destination.
   * 
   *  @return Vector2 object representing ladder destination
   */
  public Vector2 getDestination() {
    return destination;
  }

  /**
   *  Getter for ladder direction.
   *  Defines a new String to up,
   *  then checks if destination y cordinate is higher or lower
   *  then start y cordinate. If true change changes string to down.
   * 
   *  @return String representing ladder direction
   */
  public String getDirection() {
    String direction = "up";
    if (destination.getY() < start.getY()) {
      direction = "down";
    }

    return direction;
  }
}