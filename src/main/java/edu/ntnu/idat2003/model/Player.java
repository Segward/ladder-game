package edu.ntnu.idat2003.model;

public class Player {
  private String name;
  private Figure figure;
  private int position;

  public Player(String name, Figure figure) {
    this.name = name;
    this.figure = figure;
    this.position = 0;
  }

  public void move(int steps) {
    this.position += steps;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public Figure getFigure() {
    return figure;
  }

  public int getPosition() {
    return position;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Player)) {
      return false;
    }
    
    Player player = (Player) obj;
    
    // Only compare the name of the player
    if (player.getName().equals(this.name)) {
      return true;
    }

    return false;
  }

  @Override
  public int hashCode() {
    int result = 31 * name.hashCode();
    return result;
  }
}
