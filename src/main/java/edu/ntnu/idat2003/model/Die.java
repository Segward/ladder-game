package edu.ntnu.idat2003.model;

/**
 *  Class representing a singel die,
 *  Incluedes method for rolling a single die.
 * 
 */
public class Die {

  private int sides;

  /**
   *  Constructor for the die class,
   *  utilizes the parameter to define the number of sides the die has.
   * 
   *  @param sides int representing die sides amount
   */
  public Die(int sides) {
    this.sides = sides;
  }

  /**
   *  Method for rolling a single die.
   *  Utilizes math.random to genereate a random int
   *  between 1-6, including 1 and 6.
   * 
   *  @return int representing die side
   */
  public int roll() {
    return (int) (Math.random() * sides) + 1;
  }

  /**
   *  Getter for die side amount,
   *  Retrives the amount of sides the singel die has. 
   * 
   *  @return int representing die side amount
   */
  public int getSides() {
    return sides;
  }
}
