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

  public int getNumber() {
    if (y % 2 == 1) {
      return y * 10 + (9 - x) + 1;
    } else {
      return y * 10 + x + 1;
    }
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
    int result = getNumber();
    return result;
  }
}
