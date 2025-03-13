package edu.ntnu.idat2003.old.models;

public class Dice {
  private int die;

  public Dice(int die) {
    this.die = die;
  }

  public int roll() {
    int result = 0;
    Die die = new Die(6);
    for (int i = 0; i < this.die; i++) {
      result += die.roll();
    }
    return result;
  }

  public int getDie() {
    return die;
  }
}
