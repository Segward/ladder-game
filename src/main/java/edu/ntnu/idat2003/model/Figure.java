package edu.ntnu.idat2003.models;

public class Figure {

  private String color;

  public Figure(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
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

    if (figure.getColor().equals(this.color)) {
      return true;
    }

    return false;
  }

  @Override
  public int hashCode() {
    int result = 31 * color.hashCode();
    return result;
  }
}
