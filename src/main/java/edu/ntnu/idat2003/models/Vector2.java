package edu.ntnu.idat2003.models;

public class Vector2 {

  private double x;
  private double y;

  public Vector2(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public void add(Vector2 vector) {
    x += vector.getX();
    y += vector.getY();
  }

  public void subtract(Vector2 vector) {
    x -= vector.getX();
    y -= vector.getY();
  }
}
