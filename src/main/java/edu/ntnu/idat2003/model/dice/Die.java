package edu.ntnu.idat2003.model.dice;

/**
 *  Class representing a singel die.
 *  Inclued a method for rolling die object.
 */
public class Die {

  private int sides;
  
  /**
   *  Constructor for die class.
   *  Takes an int parameter representing
   *  the number for sides this die object has.
   *
   *  @param sides int representing die side amount 
   */
  public Die(int sides) {
    this.sides = sides;
  }

  /**
   *  Method for rolling the die object.
   *  Utilizes the Math random method to generate a
   *  random number between 1-6 (inclueds 1 and 6).
   * 
   * @return random int value between 1-6
   */
  public int roll() {
    return (int) (Math.random() * sides) + 1;
  }
  
  /**
   *  Getter for this die side amount.
   *  Retreves the an int value representing
   *  this die objects number of sides.
   *
   *  @return int value representing die sie amount
   */
  public int getSides() {
    return sides;
  }
}
