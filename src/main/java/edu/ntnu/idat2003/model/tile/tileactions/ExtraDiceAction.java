package edu.ntnu.idat2003.model.tile.tileactions;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.player.Player;

public class ExtraDiceAction extends TileAction {

  public ExtraDiceAction(Vector2 start) {
    super(start);
  }

  @Override
  public void execute(Player player) {
    player.setExtraDice(true);
  }
}