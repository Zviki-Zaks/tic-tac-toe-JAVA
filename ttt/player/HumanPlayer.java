package ttt.player;

import java.util.Scanner;

import ttt.main.Board;
import ttt.main.Mark;

class HumanPlayer implements Player {
  Scanner scanner = new Scanner(System.in);

  @Override
  public void playTurn(Board board, Mark playerMark) {
    boolean isPutSUccess = false;
    while (!isPutSUccess) {
      int[] cord = getCordFromUser(playerMark);
      isPutSUccess = board.putMark(playerMark, cord[0], cord[1]);
      if (!isPutSUccess) {
        System.out.println("This position is full or not valid");
      }
    }
  }

  private int[] getCordFromUser(Mark playerMark) {
    int row = getRowAndColFromUser("row", playerMark);
    int col = getRowAndColFromUser("column", playerMark);
    return new int[] { row - 1, col - 1 };
  }

  private int getRowAndColFromUser(String state, Mark playerMark) {
    System.out.println("Turn of " + playerMark + ":");
    System.out.println("In what " + state + " put your mark (get number 1-3)");
    int res = scanner.nextInt();
    return res;
  }
}
