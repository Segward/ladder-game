package edu.ntnu.idat2003;

public class Player {
  private String name;
  private Figure figure;
  private Tile currentTile;

  public Player(String name, Figure figure, Tile currentTile) {
    this.name = name;
    this.figure = figure;
    this.currentTile = currentTile;
  }

  public void move(int steps) {
    Tile nextTile = currentTile;
    for (int i = 0; i < steps; i++) {
      nextTile = nextTile.getNextTile();
    }
    currentTile.removePlayer(this);
    nextTile.placePlayer(this);
    currentTile = nextTile;
  }

  public void setCurrentTile(Tile currentTile) {
    this.currentTile = currentTile;
  }

  public String getName() {
    return name;
  }

  public Figure getFigure() {
    return figure;
  }

  public Tile getCurrentTile() {
    return currentTile;
  }
}
