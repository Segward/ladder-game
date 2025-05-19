package edu.ntnu.idat2003.model;

/**
 *  Class representing player figure.
 *  Consists of methods that defines this object.
 */
public class Figure {

  private String name;
  private String path;

  public Figure(String name, String path) {
    this.name = name;
    this.path = path;
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

  public String getPath() {
    return path;
  }

  @Override
  public String toString() {
    return String.format("%s,%s", name, path);
  }

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
