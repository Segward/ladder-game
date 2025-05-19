package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.player.Player;

/**
 *  Class representing the extra dice action.
 *  Incluedes method for defining if action is active.
 * 
 */
public class ExtraDiceAction extends TileAction {

  /**
   *  Constuctor for ExtraDiceAction inherited from TileAction.
   *  Takes a Vactor2 object and sets that as the tileAction position. 
   * 
   *  @param start Vector2 representing tileAction position
   */
  public ExtraDiceAction(Vector2 start) {
    super(start);
  }

  /**
   *  Method for setting player extraDice to true.
   *  Utilizes the setter method in the player class on the
   *  player object parameter to set the players extra 
   *  die value to true.
   * 
   *  @param player player object to recive an extra die.
   */
  @Override
  public void execute(Player player) {
    player.setExtraDice(true);
  }
}