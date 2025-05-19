package edu.ntnu.idat2003.model;

public class Figure {

  private String name;
  private String path;

  public Figure(String name, String path) {
    this.name = name;
    this.path = path;
  }

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

  @Override
  public int hashCode() {
    int result = 31 * name.hashCode();
    return result;
  }
}
