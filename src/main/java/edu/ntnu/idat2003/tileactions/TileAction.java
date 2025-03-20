package edu.ntnu.idat2003.tileactions;

import edu.ntnu.idat2003.models.Player;

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
