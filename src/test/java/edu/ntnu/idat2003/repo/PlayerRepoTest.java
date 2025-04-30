package edu.ntnu.idat2003.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idat2003.model.player.Figure;
import edu.ntnu.idat2003.model.player.Player;

import java.util.HashSet;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class PlayerRepoTest {

  @BeforeAll
  public static void setup() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    Figure figure = new Figure("Test Figure");
    Player player = new Player("Test Player", figure);
    players.add(player);
    PlayerRepo.savePlayers(players);
  }

  @Test
  public void testGetPlayers() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    boolean exists = players.stream().anyMatch(player -> player.getName().equals("Test Player"));
    assertEquals(true, exists);
  }

  @AfterAll
  public static void clean() {
    HashSet<Player> players = PlayerRepo.getPlayers();
    int size = players.size();
    players.removeIf(player -> player.getName().equals("Test Player"));
    PlayerRepo.savePlayers(players);
    players = PlayerRepo.getPlayers();
    assertEquals(size - 1, players.size());
  }
}
