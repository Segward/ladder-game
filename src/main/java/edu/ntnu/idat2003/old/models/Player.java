package edu.ntnu.idat2003.old.models;

public class Player {
  private String name;
  private Figure figure;
  private int position;

  public Player(String name, Figure figure) {
    this.name = name;
    this.figure = figure;
    this.position = 0;
  }

  public void move(int steps) {
    this.position += steps;
  }

  public void setPosition(int position) {
    this.position = position;
  }

  public String getName() {
    return name;
  }

  public Figure getFigure() {
    return figure;
  }

  public int getPosition() {
    return position;
  }
}
