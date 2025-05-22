package edu.ntnu.idat2003.observer;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.tileactions.TileAction;

/**
 *  Interface representing the Ladder Game Observer
 */
public interface LadderGameObserver {
  void onPlayerMoved(Player player, int remainder);

  void onTileActionExecuted(Player player, TileAction action);

  void onPlayerWon(Player player);

  void onDiceRolled(int diceValue);
}