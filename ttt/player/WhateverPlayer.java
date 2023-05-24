package ttt.player;

import java.util.Random;

import ttt.main.Board;
import ttt.main.Mark;

class WhateverPlayer implements Player {
  private Random random = new Random();

  @Override
  public void playTurn(Board board, Mark playerMark) {
    boolean isPutSUccess = false;
    while (!isPutSUccess) {
      isPutSUccess = board.putMark(playerMark, random.nextInt(Board.SIZE), random.nextInt(Board.SIZE));
    }
  }
}
