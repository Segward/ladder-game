package edu.ntnu.idat2003.model;

/**
 *  Class representing the figure icon of a player.
 *  Utilizing methods for retriving and setting figure data.
 */
public class Figure {

  private String color;

  /**
   *  Constructor for the Figure class,
   *  Utilizes String parameter to state figure color.
   *  Color variable represents the name of the figure.
   * 
   *  @param color String representing color of figure
   */
  public Figure(String color) {
    this.color = color;
  }

  /**
   *  Getter for color string,
   *  retrives the name of the color representing the figure.
   * 
   *  @return String representing color of figure.
   */
  public String getColor() {
    return color;
  }

  /**
   *  Method for checking if parameter object is equal to
   *  this object.
   * 
   * @param obj Object to be checked
   * @return true if object or object color is equal, false if not
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Figure)) {
      return false;
    }

    Figure figure = (Figure) obj;

    if (figure.getColor().equals(this.color)) {
      return true;
    }

    return false;
  }

  /**
   *  Method for giving this object an uniqe value
   * 
   *  @return uniqe hashed value
   */
  @Override
  public int hashCode() {
    int result = 31 * color.hashCode();
    return result;
  }
}
