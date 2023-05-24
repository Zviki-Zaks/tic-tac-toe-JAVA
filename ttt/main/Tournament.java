package ttt.main;

import java.util.Scanner;

import ttt.player.Player;
import ttt.player.PlayerFactory;
import ttt.renderer.Renderer;
import ttt.renderer.RendererFactory;

class Tournament {
  private static final int ARGS_NUM = 3;
  private static final int ARG_PLAYER1_TYPE = 0;
  private static final int ARG_PLAYER2_TYPE = 1;
  private static final int ARG_RENDERER_TYPE = 2;

  private static Scanner scanner = new Scanner(System.in);

  private int rounds;
  private Player[] players = new Player[2];
  private Renderer renderer;

  public static void main(String[] args) {

    if (args.length < ARGS_NUM) {
      System.err.println("Enter tow types of player and type of renderer");
      return;
    }

    Player player1 = PlayerFactory.buildPlayer(args[ARG_PLAYER1_TYPE]);
    Player player2 = PlayerFactory.buildPlayer(args[ARG_PLAYER2_TYPE]);
    Renderer renderer = RendererFactory.buildRenderer(args[ARG_RENDERER_TYPE]);
    if (player1 == null || player2 == null || renderer == null) {
      System.err.println("Enter tow types of player and type of renderer");
      return;
    }

    int rounds = 0;
    while (rounds <= 0) {
      System.out.println("Enter rounds number");
      rounds = scanner.nextInt();
      if (rounds <= 0) {
        System.err.println("Enter positive number of rounds");
      }
    }

    Tournament tournament = new Tournament(rounds, player1, player2, renderer);
    int[] results = tournament.playTournament();
    System.out.printf("Player 1: %d Player 2: %d Draws: %d", results[0], results[1], results[2]);
  }

  Tournament(int rounds, Player player1, Player player2, Renderer renderer) {
    this.rounds = rounds;
    this.players[0] = player1;
    this.players[1] = player2;
    this.renderer = renderer;
  }

  int[] playTournament() {
    int[] results = new int[3];
    for (int i = 0; i < rounds; i++) {
      Game game = new Game(players[i % players.length], players[(i + 1) % players.length], renderer);
      Mark result = game.run();
      if (result == Mark.BLANK) {
        results[2] += 1;
      } else {
        results[i % players.length] += 1;
      }
      System.out.printf("Player 1: %d Player 2: %d Draws: %d\r", results[0], results[1], results[2]);
    }
    return results;
  }
}
