package edu.ntnu.idat2003.model;

import edu.ntnu.idat2003.model.tileactions.TileAction;

public class Tile {
  private Vector2 position;
  private Vector2 nextPosition;
  private TileAction action;
  private String text;

  public Tile(Vector2 position, String text) {
    this.position = position;
    this.text = text;
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

  public String getText() {
    return text;
  }

  public void setAction(TileAction action) {
    this.action = action;
  }
  
  public void setNextPosition(Vector2 nextPosition) {
    this.nextPosition = nextPosition;
  } 
}
