package ttt.main;

import ttt.player.Player;
import ttt.renderer.Renderer;

class Game {
  Mark playerMark = Mark.X;

  private Board board = new Board();
  private Renderer renderer;
  private Player[] players = new Player[2];

  Game(Player playerX, Player playerO, Renderer renderer) {
    this.players[0] = playerX;
    this.players[1] = playerO;
    this.renderer = renderer;
  }

  Mark run() {
    renderer.renderBoard(board);
    for (int i = 0;; i++) {
      players[i % players.length].playTurn(board, playerMark);
      renderer.renderBoard(board);
      GameStatus gameStatus = board.getGameStatus();
      if (gameStatus == GameStatus.DRAW) {
        return Mark.BLANK;
      } else if (gameStatus != GameStatus.IN_PROGRESS) {
        return playerMark;
      }
      playerMark = playerMark == Mark.X ? Mark.O : Mark.X;
    }

  }
}
