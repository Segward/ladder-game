package edu.ntnu.idat2003.model;

public class LadderAction {
  private Tile destination;

  public LadderAction(Tile destination) {
    this.destination = destination;
  }

  public Vector2 getDestination() {
    return destination.getPosition();
  }

  public Tile getDestinationTile() {
    return destination;
  }
}
