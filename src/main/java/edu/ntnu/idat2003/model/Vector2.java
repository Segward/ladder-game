package edu.ntnu.idat2003.model;

public class Vector2 {
  private int x;
  private int y;

  public Vector2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void set(Vector2 vector) {
    this.x = vector.x;
    this.y = vector.y;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public Vector2 add(Vector2 vector) {
    return new Vector2(this.x + vector.x, this.y + vector.y);
  }

  public Vector2 subtract(Vector2 vector) {
    return new Vector2(this.x - vector.x, this.y - vector.y);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Vector2)) {
      return false;
    }

    Vector2 other = (Vector2) obj;
    return this.x == other.x && this.y == other.y;
  }

  @Override
  public int hashCode() {
    int hash = x + y * 100;
    return hash;
  }
}
