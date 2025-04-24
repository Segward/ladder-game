package edu.ntnu.idat2003.model;

/**
 *  Class representing the extra dice tile action.
 *  it inherits the TileAction class.
 *  
 */
public class ExtraDiceAction extends TileAction {

  /**
   *  Constrictor for the ExtraDiceAction class,
   *  Utilazes the Vector2 paramerter on a super method
   *  inhereted from the TileAction class to set the start tile.
   * 
   *  @param start Vector representing the start tile placement
   */
  public ExtraDiceAction(Vector2 start) {
    super(start);
  }

  /**
   *  Mehtod for declaring player has an extra dice.
   *  Sets the extraDice variable from the player class to true.
   * 
   *  @param player Player to recive an extra dice
   */
  @Override
  public void execute(Player player) {
    player.setExtraDice(true);
  }
}