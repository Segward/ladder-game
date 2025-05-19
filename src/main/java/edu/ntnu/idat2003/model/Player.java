package edu.ntnu.idat2003.model;

/**
 *  Class representing the Player.
 *  Consists of methods of interacting for
 *  intraction with class variable values.
 * 
 */
public class Player {
  private String name;
  private Figure figure;
  private Vector2 position;
  private boolean extraDice;

  /**
   *  Constructor for the Player class.
   *  Takes both a String and a Figure object as
   *  parameters, and states them to Player name
   *  and figure variables. 
   * 
   *  @param name String representing player name
   *  @param figure Figure object representing player figure
   */
  public Player(String name, Figure figure) {
    this.name = name;
    this.figure = figure;
  }

  /**
   *  Getter for the player name value.
   *  Retreves a string value representing the 
   *  name of the player.
   * 
   *  @return String representing player name
   */
  public String getName() {
    return name;
  }

  /**
   *  Getter for the player figure.
   *  Retreves the Figure object representing the
   *  figure registered to the player.
   * 
   *  @return  Figure object representing player figure
   */
  public Figure getFigure() {
    return figure;
  }

  /**
   *  Getter for the player position.
   *  Retreves a Vector2 object representing the 
   *  position of the player on the game bord.
   * 
   *  @return Vector2 object representing player position
   */
  public Vector2 getPosition() {
    return position;
  }

  /**
   *  Setter for the player position.
   *  Takes a Vector2 object as a parameter
   *  and sets that as the players current position.
   * 
   *  @param position Vector2 object representing new player position
   */
  public void setPosition(Vector2 position) {
    this.position = position;
  }

  /**
   *  Getter for has extra die or not variable.
   *  Retreves the boolean value of extraDice,
   *  represents if player has an extra die to be rolled.
   * 
   * @return extraDice boolean value
   */
  public boolean hasExtraDice() {
    return extraDice;
  }
  public void setExtraDice(boolean extraDice) {
    this.extraDice = extraDice;
  }

  /**
   *  Method for checking if an object is equal to
   *  this spesific Player object. Checks if the 
   *  parameter object is the same as this object.
   *  Checks for both object value and if string name
   *  value is equal.
   * 
   *  @return true if objects are the same, false if not
   */
  @Override
  public String toString() {
    return String.format("%s,%s", name, figure.getName());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Player)) {
      return false;
    }

    Player player = (Player) obj;

    if (player.getName().equals(this.name)) {
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
