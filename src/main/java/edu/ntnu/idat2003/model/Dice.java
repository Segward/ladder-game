package edu.ntnu.idat2003.model;

/**
 *  Class representing the amount of dice to be rolled.
 *  Includes a method for rolling the dice.
 */
public class Dice {
  private int die;

  /**
   *  Constructor for the dice class.
   *  Takes an int parameter and sets 
   *  that as the number of dice to be rolled.
   * 
   * @param die int representing dice amount
   */
  public Dice(int die) {
    this.die = die;
  }

  /**
   *  Method for rolling all of the dice.
   *  Creates a new die object with 6 sides and a result int.
   *  Utilizes a for loop that loops equal to this
   *  dice objects int die value. Adds each consecutive
   *  int result to the new result int. 
   * 
   *  @return int representing the dice side amount.
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
   *  Getter for the die amount.
   *  Retreves the an int representing the
   *  amount of dice to be rolled.
   * 
   *  @return int representing dice amount
   */
  public int getDie() {
    return die;
  }
}
