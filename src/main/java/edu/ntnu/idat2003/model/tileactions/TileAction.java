package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.player.Player;

public abstract class TileAction {
  protected Vector2 start;

  public TileAction(Vector2 start) {
    this.start = start;
  }

  public Vector2 getStart() {
    return start;
  }

  public abstract void execute(Player player);
}