package edu.ntnu.idat2003;

public class Dice {

  private int die;

  public Dice(int die) {
    this.die = die;
  }

  public int roll() {
    return (int) (Math.random() * die) + 1;
  }
}
