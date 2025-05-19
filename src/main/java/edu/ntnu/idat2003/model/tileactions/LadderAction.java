package edu.ntnu.idat2003.model.tileactions;

import edu.ntnu.idat2003.model.Player;
import edu.ntnu.idat2003.model.Vector2;

public class LadderAction extends TileAction {
  private Vector2 destination;

  public LadderAction(Vector2 start, Vector2 destination) {
    super(start);
    this.destination = destination;
  }

  @Override
  public void execute(Player player) {
    player.setPosition(destination);
  }

  public Vector2 getDestination() {
    return destination;
  }

  public String getDirection() {
    String direction = "up";
    if (destination.getY() < start.getY()) {
      direction = "down";
    }

    return direction;
  }
}