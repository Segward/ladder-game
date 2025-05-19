package edu.ntnu.idat2003.model.player;

/**
 *  Class representing player figure.
 *  Consists of methods that defines this object.
 */
public class Figure {

  private String name;

  /**
   *  Constructor for the Figure class.
   *  Takes an String parameter and sets
   *  that as the Figures name.
   * 
   *  @param name String representing figure name
   */
  public Figure(String name) {
    this.name = name;
  }

  /**
   *  Getter for the Figure name.
   *  Retreves the name String variable value.
   * 
   * @return String representing figure name
   */
  public String getName() {
    return name;
  }

  /**
   *  Method for checking if an object is equal to
   *  this spesific Figure object. Checks if the 
   *  parameter object is the same as this object.
   *  Checks for both object value and if string name
   *  value is equal.
   * 
   *  @return true if objects are the same, false if not
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

    if (figure.getName().equals(this.name)) {
      return true;
    }

    return false;
  }

  /**
   *  Method for reducing collision with similar names.
   *  Uses a hash Code method on the String name value to 
   *  generate a hash code of the name, then multiplies that
   *  value wiht 31 to reduce the likelihood of collision.
   */
  @Override
  public int hashCode() {
    int result = 31 * name.hashCode();
    return result;
  }
}
