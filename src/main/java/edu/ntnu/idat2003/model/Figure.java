package edu.ntnu.idat2003.model;

public class Figure {

  private String name;

  public Figure(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
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

  @Override
  public int hashCode() {
    int result = 31 * name.hashCode();
    return result;
  }
}
