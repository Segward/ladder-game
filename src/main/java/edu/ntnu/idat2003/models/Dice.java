package edu.ntnu.idat2003.models;

public class Dice {

  private int sides;

  public Dice(int sides) {
    this.sides = sides;
  }

  public int roll() {
    return (int) (Math.random() * sides) + 1;
  }

  public int getSides() {
    return sides;
  }
}
