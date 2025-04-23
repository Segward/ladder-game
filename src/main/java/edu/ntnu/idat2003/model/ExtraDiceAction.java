package edu.ntnu.idat2003.model;

public class ExtraDiceAction extends TileAction {

  public ExtraDiceAction(Vector2 start) {
    super(start);
  }

  @Override
  public void execute(Player player) {
    player.setTurns(player.getTurns() + 1);
  }
}