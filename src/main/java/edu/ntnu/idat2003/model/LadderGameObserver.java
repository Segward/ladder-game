package edu.ntnu.idat2003.model;

import edu.ntnu.idat2003.model.player.Player;
import edu.ntnu.idat2003.model.tile.tileactions.TileAction;

public interface LadderGameObserver {
  void onPlayerMoved(Player player, int remainder);

  void onTileActionExecuted(Player player, TileAction action);

  void onPlayerWon(Player player);

  void onDiceRolled(int diceValue);
}
