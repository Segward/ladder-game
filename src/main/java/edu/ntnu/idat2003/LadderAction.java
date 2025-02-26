package edu.ntnu.idat2003;

public class LadderAction extends TileAction {
  private Tile destination;

  public LadderAction(String action, Tile destination) {
    super(action);
    this.destination = destination;
  }

  @Override
  public void execute(Player player) {
    throw new UnsupportedOperationException("Not supported yet");
  }
}
