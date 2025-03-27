package edu.ntnu.idat2003.models;

public class Dice {
  private int die;

  /**
   *  Constructor for dice class,
   *  uses given int to set times a dice will be rolled.
   * 
   *  @param die int representing amount of throws
   */
  public Dice(int die) {
    this.die = die;
  }

  /**
   *  Method for rolling a dice object,
   *  constructs a new dice with 6 sides
   *  and throws the amount of times die is defined.
   *  For each throw the side value is added to a total.
   * 
   *  @return int representing total of all throws.
   */
  public int roll() {
    int result = 0;
    Die die = new Die(6);
    for (int i = 0; i < this.die; i++) {
      result += die.roll();
    }
    return result;
  }

  /**
   *  Getter for dice throw amount.
   * 
   *  @return int representing amount of throws
   */
  public int getDie() {
    return die;
  }
}
