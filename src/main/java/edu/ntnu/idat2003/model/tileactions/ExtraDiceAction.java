package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;

public class ExtraDiceAction extends TileAction {

  public ExtraDiceAction(Vector2 start) {
    super(start);
  }

  @Override
  public void execute(Player player) {
    player.setExtraDice(true);
  }
}