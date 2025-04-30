package edu.ntnu.idat2003.model.player;

import edu.ntnu.idat2003.model.Vector2;

public class Player {
  private String name;
  private Figure figure;
  private Vector2 position;
  private boolean extraDice;

  public Player(String name, Figure figure) {
    this.name = name;
    this.figure = figure;
  }

  public String getName() {
    return name;
  }

  public Figure getFigure() {
    return figure;
  }

  public Vector2 getPosition() {
    return position;
  }

  public void setPosition(Vector2 position) {
    this.position = position;
  }

  public boolean hasExtraDice() {
    return extraDice;
  }
  
  public void setExtraDice(boolean extraDice) {
    this.extraDice = extraDice;
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
