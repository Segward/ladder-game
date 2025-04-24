package edu.ntnu.idat2003.observer;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.TileAction;

public interface LadderGameObserver {
  void onPlayerMoved(Player player, int remainder);

  void onTileActionExecuted(Player player, TileAction action);

  void onPlayerWon(Player player);

  void onDiceRolled(int diceValue);
}
