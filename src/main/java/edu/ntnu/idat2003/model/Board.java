package edu.ntnu.idat2003.models;

import edu.ntnu.idat2003.tileactions.TileAction;
import java.util.HashMap;

public class Board {
  private String name;
  private HashMap<Integer, Tile> tiles;

   /**
   *  Constructor for bord class
   *  Gives the bord a String name 
   *  and defines a new hashmap<Integer, tile>.
   */
  public Board(String name) {
    this.name = name;
    this.tiles = new HashMap<>();
  }

  /**
   *  Adds a given tile the bord HashMap.
   * 
   *  @param number int representing tile numb
   *  @param tile tile object to be added
   */
  public void addTile(int number, Tile tile) {
    tiles.put(number, tile);
  }

  /**
   *  Adds a new tile to the bord hashmap
   *  now with a specified tile action.
   * 
   *  @param number int representing tile numb
   *  @param action action to be given to new tile
   */
  public void setTileAction(int number, TileAction action) {
    tiles.put(number, new Tile(number, action));
  }

  /**
   *  Getter for spesific tile in bord hashmap.
   * 
   *  @param number int representing tile numb
   *  @return tile object
   */
  public Tile getTile(int number) {
    return tiles.get(number);
  }

  /**
   *  Getter for amount of tiles in bord hashmap.
   * 
   *  @return size of bord hashmap
   */
  public int getTileCount() {
    return tiles.size();
  }

  /**
   *  Getter for bord name.
   *
   *  @return String representing bord name
   */
  public String getName() {
    return name;
  }
}
