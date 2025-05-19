package edu.ntnu.idat2003.model;

/**
 *  Class representing the position for objects within the game.
 *  Incluedes methods for interacting with coordinate values.
 */
public class Vector2 {
  private int x;
  private int y;
  private int number;

  /**
   *  Constructor for the Vector2 Class.
   *  Takes two int parameters representing
   *  the x and y coordinates.
   * 
   *  @param x int representing x coordinate
   *  @param y int representing y coordinate
   */
  public Vector2(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   *  Setter for the x coordinate.
   *  Takes an int parameter representing the x coordinate value.
   * 
   *  @param x int representing x coordinate
   */
  public void setX(int x) {
    this.x = x;
  }

   /**
   *  Setter for the y coordinate.
   *  Takes an int parameter representing the y coordinate value.
   * 
   *  @param y int representing y coordinate
   */
  public void setY(int y) {
    this.y = y;
  }

  /**
   *  Setter for both the x and y coordinate.
   *  Takes a Vector2 parameter, and sets both 
   *  x and y coordinate equal to the Vector2 
   *  x and y coordinate.
   * 
   *  @param vector Vector2 object for base x and y coordinates
   */
  public void set(Vector2 vector) {
    this.x = vector.x;
    this.y = vector.y;
  }

  /**
   *  Getter for the x coordinate.
   *  Retreves the int value of local variable x,
   *  representing a x coordinate.
   * 
   *  @return int representing x coordinate
   */
  public int getX() {
    return x;
  }

  /**
   *  Getter for the y coordinate.
   *  Retreves the int value of local variable y,
   *  representing a y coordinate.
   * 
   *  @return int representing x coordinate
   */
  public int getY() {
    return y;
  }

  /**
   *  Setter for vector number.
   *  Mainly used in board game as an eqaul
   *  representation of tile number.
   * 
   *  @param number int representing vector number
   */
  public void setNumber(int number) {
    this.number = number;
  }

  /**
   *  Getter for vector number.
   *  Mainly used in board game as an eqaul
   *  representation of tile number.
   * 
   *  @return int representing vector number
   */
  public int getNumber() {
    return number;
  }

  /**
   *  Method for checking if an object is equal to
   *  this spesific Vector2 object. Checks if the 
   *  parameter object is the same as this object.
   *  Checks for both object value and if x and y 
   *  coordinate are equal to parameter.
   * 
   *  @return true if objects are the same, false if not
   */
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

   /**
   *  Method for reducing collision with similar numbers.
   *  Takes the x and y coordinate and multiplies it with
   *  100 in order to reduce the likelihood of collision.
   */
  @Override
  public int hashCode() {
    int hash = x * 100 + y;
    return hash;
  }
}
