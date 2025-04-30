package edu.ntnu.idat2003.model.board;

import edu.ntnu.idat2003.model.Vector2;
import edu.ntnu.idat2003.model.tileactions.TileAction;

public class Tile {
  private Vector2 position;
  private Vector2 nextPosition;
  private TileAction action;

  public Tile(Vector2 position, Vector2 nextPosition) {
    this.position = position;
    this.nextPosition = nextPosition;
  }
  
  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public Vector2 getNextPosition() {
    return nextPosition;
  }

  public TileAction getAction() {
    return action;
  }

  public void setAction(TileAction action) {
    this.action = action;
  }
  
  public void setNextPosition(Vector2 nextPosition) {
    this.nextPosition = nextPosition;
  } 
}
