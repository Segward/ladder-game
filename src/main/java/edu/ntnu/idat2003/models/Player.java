package edu.ntnu.idat2003.models;

public class Player {
  private String name;
  private Figure figure;
  private int position;

  /**
   *  Constructor for player class,
   *  defines name and color by given parameters
   *  and sets position to 0.
   *
   *  @param name String representing player name
   *  @param figure player figure object
   */
  public Player(String name, Figure figure) {
    this.name = name;
    this.figure = figure;
    this.position = 0;
  }

  /**
   *  Method for moving the player,
   *  based on int parameter.
   * 
   *  @param steps int representing player steps
   */
  public void move(int steps) {
    this.position += steps;
  }

  /**
   *  Setter for player position,
   *  setts player position based on int parameter.
   * 
   *  @param position int representing new placement
   */
  public void setPosition(int position) {
    this.position = position;
  }

  /**
   *  Getter for player name.
   * 
   *  @return String representing player name
   */
  public String getName() {
    return name;
  }

  /**
   *  Getter for player figure object.
   * 
   *  @return figure object
   */
  public Figure getFigure() {
    return figure;
  }

  /**
   *  Getter for player position on bord.
   * 
   *  @return int representing player position
   */
  public int getPosition() {
    return position;
  }

  /**
   *  Checks if a object is equal to player object,
   *  Checks first if object is a object,
   *  then if object is player object,
   *  then if object name equals player object name.
   * 
   *  @param object to be checked
   *  @return ture if object or object name equals player, false if not
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Player)) {
      return false;
    }
    
    Player player = (Player) obj;
    
    // Only compare the name of the player
    if (player.getName().equals(this.name)) {
      return true;
    }

    return false;
  }

  /**
   *  Getter for player name in hash code.
   * 
   *  @return int representing player name hash coded
   */
  @Override
  public int hashCode() {
    int result = 31 * name.hashCode();
    return result;
  }
}
