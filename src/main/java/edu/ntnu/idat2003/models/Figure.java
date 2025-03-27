package edu.ntnu.idat2003.models;

public class Figure {

  private String color;

  /**
   *  Constructor for figure class,
   *  defines the color of player figure.
   * 
   *  @param color
   */
  public Figure(String color) {
    this.color = color;
  }

  /**
   *  Getter for color string.
   * 
   *  @return string representing figure color
   */
  public String getColor() {
    return color;
  }
}
