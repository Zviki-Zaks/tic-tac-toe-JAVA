package ttt.player;

import ttt.main.Board;
import ttt.main.Mark;

public interface Player {
  void playTurn(Board board, Mark playerMark);
}
