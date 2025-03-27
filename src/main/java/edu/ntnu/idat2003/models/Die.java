package edu.ntnu.idat2003.models;

public class Die {

  private int sides;

  /**
   *  Constructor for die class,
   *  defines the number of sides for die.
   * 
   *  @param sides int representing number of sides
   */
  public Die(int sides) {
    this.sides = sides;
  }

  /**
   *  Method for rolling the die object,
   *  uses math random to get a number 
   *  between 1 and number of sides.
   *
   *  @return int representing die side
   */
  public int roll() {
    return (int) (Math.random() * sides) + 1;
  }

  /**
   *  Getter for number of sides of die.
   * 
   *  @return int representing number of sides
   */
  public int getSides() {
    return sides;
  }
}
