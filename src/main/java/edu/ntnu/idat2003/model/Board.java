package edu.ntnu.idat2003.model;

import java.util.HashMap;
import java.util.HashSet;

import edu.ntnu.idat2003.model.tileactions.ExtraDiceAction;
import edu.ntnu.idat2003.model.tileactions.LadderAction;
import edu.ntnu.idat2003.model.tileactions.TileAction;

public class Board {
  private String name;
  private HashMap<Integer, Tile> tiles;

  /**
   * Constructor for bord class Gives the bord a String name and defines a new hashmap<Integer,
   * tile>.
   */
  public Board(String name) {
    this.name = name;
    this.tiles = new HashMap<>();
  }

  /**
   * Getter for spesific action in bord hashmap.
   *
   * @param position represents the position of the tile
   * @return action object
   */
  public TileAction getAction(Vector2 position) {
    int hash = position.hashCode();
    Tile tile = tiles.get(hash);
    return tile.getAction();
  }

  /**
   * Adds a tile action to the tile
   *
   * @param position represents the position of the tile
   * @param action tile action to be added
   */
  public void addAction(Vector2 position, TileAction action) {
    int hash = position.hashCode();
    tiles.get(hash).setAction(action);
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
   * Getter for all ladders in bord hashmap.
   *
   * @return HashSet with all ladders
   */
  public HashSet<LadderAction> getLadders() {
    HashSet<LadderAction> ladders = new HashSet<>();
    for (Tile tile : tiles.values()) {
      if (tile.getAction() instanceof LadderAction) {
        ladders.add((LadderAction) tile.getAction());
      }
    }
    return ladders;
  }

  /**
   * Getter for all extra dice in bord hashmap.
   *
   * @return HashSet with all extra dice
   */
  public HashSet<ExtraDiceAction> getExtraDice() {
    HashSet<ExtraDiceAction> extraDice = new HashSet<>();
    for (Tile tile : tiles.values()) {
      if (tile.getAction() instanceof ExtraDiceAction) {
        extraDice.add((ExtraDiceAction) tile.getAction());
      }
    }
    return extraDice;
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

  /**
   * Getter for all actions in bord hashmap.
   *
   * @return HashMap with all actions
   */
  public HashMap<Integer, Tile> getActions() {
    HashMap<Integer, Tile> actions = new HashMap<>();
    for (Tile tile : tiles.values()) {
      if (tile.getAction() != null) {
        actions.put(tile.getPosition().hashCode(), tile);
      }
    }
    return actions;
  }
}
