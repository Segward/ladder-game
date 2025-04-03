package edu.ntnu.idat2003.model;

public class LadderAction extends TileAction {
  private int destination;

  public LadderAction(String action, int destination) {
    super(action);
    this.destination = destination;
  }

  @Override
  public void execute(Player player) {
    player.setPosition(destination);
    System.out.println(player.getName() + " climbed the ladder to tile " + destination);
  }
}
