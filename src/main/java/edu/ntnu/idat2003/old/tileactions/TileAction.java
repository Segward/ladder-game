package edu.ntnu.idat2003.old.tileactions;

import edu.ntnu.idat2003.old.models.Player;

public abstract class TileAction {
  protected String action;

  public TileAction(String action) {
    this.action = action;
  }

  public String getAction() {
    return action;
  }

  public abstract void execute(Player player);
}
