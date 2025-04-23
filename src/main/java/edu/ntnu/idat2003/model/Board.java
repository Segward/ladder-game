package edu.ntnu.idat2003.model;

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
   *  @param position represents the position of the tile
   *  @param tile tile object to be added
   */
  public void addTile(Vector2 position, Tile tile) {
    int hash = position.hashCode();
    tiles.put(hash, tile);
  }

  /**
   *  Adds a new tile to the bord hashmap
   *  now with a specified tile action.
   * 
   *  @param position represents the position of the tile 
   *  @param action action to be given to new tile
   */
  public void setTileAction(Vector2 position, LadderAction action) {
    int hash = position.hashCode();
    Tile tile = tiles.get(hash);
    tile.setAction(action);
  }

  /**
   *  Getter for spesific tile in bord hashmap.
   * 
   *  @param position represents the position of the tile 
   *  @return tile object
   */
  public Tile getTile(Vector2 position) {
    int hash = position.hashCode();
    return tiles.get(hash);
  }

  /**
   *  Getter for all tiles in bord hashmap.
   * 
   *  @return HashMap with all tiles
   */
  public HashMap<Integer, Tile> getTiles() {
    return tiles;
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
