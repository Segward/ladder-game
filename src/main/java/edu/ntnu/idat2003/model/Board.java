package edu.ntnu.idat2003.model;

import java.util.HashMap;

public class Board {
  private String name;
  private HashMap<Integer, Tile> tiles;
  private HashMap<Integer, LadderAction> ladderActions;
  private HashMap<Integer, ExtraDiceAction> extraDiceActions;

  /**
   * Constructor for bord class Gives the bord a String name and defines a new hashmap<Integer,
   * tile>.
   */
  public Board(String name) {
    this.name = name;
    this.tiles = new HashMap<>();
    this.ladderActions = new HashMap<>();
    this.extraDiceActions = new HashMap<>();
  }

  /**
   * Getter for spesific action in bord hashmap.
   *
   * @param position represents the position of the tile
   * @return action object
   */
  public TileAction getAction(Vector2 position) {
    int hash = position.hashCode();
    if (ladderActions.containsKey(hash)) {
      return ladderActions.get(hash);
    } else if (extraDiceActions.containsKey(hash)) {
      return extraDiceActions.get(hash);
    }
    return null;
  }

  /**
   * Adds a given tile the bord HashMap.
   *
   * @param position represents the position of the tile
   * @param tile tile object to be added
   */
  public void addTile(Vector2 position, Tile tile) {
    int hash = position.hashCode();
    tiles.put(hash, tile);
  }

  /**
   * Adds a new ladder action to the bord HashMap.
   *
   * @param action action to be added
   */
  public void addLadderAction(LadderAction action) {
    int hash = action.getStart().hashCode();
    ladderActions.put(hash, action);
  }

  /**
   * Adds a new extra dice action to the bord HashMap.
   *
   * @param action action to be added
   */
  public void addExtraDiceAction(ExtraDiceAction action) {
    int hash = action.getStart().hashCode();
    extraDiceActions.put(hash, action);
  }

  /**
   * Getter for spesific tile in bord hashmap.
   *
   * @param position represents the position of the tile
   * @return tile object
   */
  public Tile getTile(Vector2 position) {
    int hash = position.hashCode();
    return tiles.get(hash);
  }

  /**
   * Getter for the hashmap of ladder actions.
   *
   * @return HashMap with all ladder actions
   */
  public HashMap<Integer, LadderAction> getLadderActions() {
    return ladderActions;
  }

  /**
   * Getter for the hashmap of extra dice actions.
   *
   * @return HashMap with all extra dice actions
   */
  public HashMap<Integer, ExtraDiceAction> getExtraDiceActions() {
    return extraDiceActions;
  }

  /**
   * Getter for all tiles in bord hashmap.
   *
   * @return HashMap with all tiles
   */
  public HashMap<Integer, Tile> getTiles() {
    return tiles;
  }

  /**
   * Getter for bord name.
   *
   * @return String representing bord name
   */
  public String getName() {
    return name;
  }
}
