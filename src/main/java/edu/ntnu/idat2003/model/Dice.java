package edu.ntnu.idat2003.model;

public class Dice {
  private int die;

  /**
   *  Constructor for the dice class.
   *  Uses the int parameter to set the dice amount.
   * 
   *  @param die int representing the dice amount.
   */
  public Dice(int die) {
    this.die = die;
  }

  /**
   *  Method for rolling the dice.
   *  Creates a new die object with 6 sides and
   *  rolls the die corresponding to the die variable amount.
   * 
   *  @return result of all rolled dice
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
   *  Getter for dice amount.
   *  
   * @return int representing the dice amount
   */
  public int getDie() {
    return die;
  }
}
