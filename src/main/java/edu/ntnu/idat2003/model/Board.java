package edu.ntnu.idat2003.model;

import java.util.HashMap;

public class Board {
  private String name;
  private HashMap<Integer, Tile> tiles;
  private HashMap<Integer, LadderAction> ladderActions;
  private HashMap<Integer, ExtraDiceAction> extraDiceActions;

  /**
   *  The constructor for the board class,
   *  the single string paramiter is used as the name of the bord.
   *  It also creates hashMaps for storing tile, ladderaction and
   *  extraDiceAction objects.
   * 
   *  @param name String representing the bord name
   */
  public Board(String name) {
    this.name = name;
    this.tiles = new HashMap<>();
    this.ladderActions = new HashMap<>();
    this.extraDiceActions = new HashMap<>();
  }

  /**
   *  Method for retrieving tile action from given tile position.
   *  Utilizes a method from the vector2 class to retrive tile number,
   *  then checks for tileAction corresponding to tile number.
   * 
   *  @param position position to be checked
   *  @return ladderAction or extraDiceAction if found, null if not
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
   *  Adds a new tile to the tiles hashMap.
   *  Utilizes a method from the vector2 class to retrive tile number,
   *  then puts tile parameter as value and tile number as key
   *  into tiles hashMap. 
   * 
   * @param position position of tile
   * @param tile tile to be added
   */
  public void addTile(Vector2 position, Tile tile) {
    int hash = position.hashCode();
    tiles.put(hash, tile);
  }


  /**
   *  Adds a new ladder action to the ladderAction hashMap.
   *  Retrives tile number throu super method in ladderaction
   *  that utilizes a method from the vector2 class to retrive tile number.
   *  Then puts the tile number as key and ladderAction parameter as value
   *  in to ladderAction hashMap. 
   * 
   *  @param action LadderAction to be added to board.
   */
  public void addLadderAction(LadderAction action) {
    int hash = action.getStart().hashCode();
    ladderActions.put(hash, action);
  }

  /**
   *  Adds a new DiceAction to the extraDiceAction hashMap.
   *  Retrives tile number throu super method in the ExtraDiceAction class
   *  that utilizes a method from the vector2 class to retrive tile number.
   *  Then puts the tile number as key and ExtraDiceAction parameter
   *  as value in to the extraDiceAction hashMap.
   * 
   *  @param action Extra Dice Action to be added to bord.
   */
  public void addExtraDiceAction(ExtraDiceAction action) {
    int hash = action.getStart().hashCode();
    extraDiceActions.put(hash, action);
  }

  /**
   *  Getter for retrieving a spesific tile form the bord.
   *  Utilizes a method from the vector2 class to retrive tile number,
   *  then retrieves tile object by utilizing the get method on the tiles hashmap
   *  with the tile numer as a parameter.
   * 
   *  @param position position of tile
   *  @return tile object from hashMap
   */
  public Tile getTile(Vector2 position) {
    int hash = position.hashCode();
    return tiles.get(hash);
  }

  /**
   *  Getter for the ladder action hashMap.
   *  HashMap containing all the ladder actions.
   * 
   *  @return the ladderAction hashMap
   */
  public HashMap<Integer, LadderAction> getLadderActions() {
    return ladderActions;
  }

  /**
   *  Getter for the extra dice action hashMap.
   *  HashMap containing all of the extraDiceActions.
   * 
   *  @return the extraDiceActions hashMap
   */
  public HashMap<Integer, ExtraDiceAction> getExtraDiceActions() {
    return extraDiceActions;
  }

  /**
   *  Getter for the tile hashMap,
   *  HashMap containing all of the tiles in the bord.
   * 
   *  @return the tiles hashMap
   */
  public HashMap<Integer, Tile> getTiles() {
    return tiles;
  }

  /**
   *  Getter for the bord name.
   *  A string value representing the game bord name.
   * 
   *  @return String representing bord name
   */
  public String getName() {
    return name;
  }
}
