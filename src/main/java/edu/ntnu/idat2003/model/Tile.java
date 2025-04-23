package edu.ntnu.idat2003.model;

public class Tile {
  private Vector2 position;
  private Vector2 nextPosition;

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
  
  public void setNextPosition(Vector2 nextPosition) {
    this.nextPosition = nextPosition;
  } 
}
