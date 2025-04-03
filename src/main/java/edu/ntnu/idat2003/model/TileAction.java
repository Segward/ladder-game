package edu.ntnu.idat2003.model;

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
