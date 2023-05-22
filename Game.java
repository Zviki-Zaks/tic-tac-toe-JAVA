public class Game {
  public Mark playerMark = Mark.X;

  private Board board = new Board();
  private Renderer renderer;
  private Player[] players = new Player[2];

  public static void main(String[] args) {
    Game game = new Game(new Player(), new Player(), new Renderer());
    game.run();
  }

  public Game(Player playerX, Player playerO, Renderer renderer) {
    this.players[0] = playerX;
    this.players[1] = playerO;
    this.renderer = renderer;
  }

  private Mark run() {
    renderer.renderBoard(board);
    for (int i = 0;; i++) {
      players[i % players.length].playTurn(board, playerMark);
      renderer.renderBoard(board);
      GameStatus gameStatus = board.getGameStatus();
      if (gameStatus == GameStatus.DRAW) {
        System.out.println(GameStatus.DRAW);
        return Mark.BLANK;
      } else if (gameStatus != GameStatus.IN_PROGRESS) {
        System.out.println("Player " + playerMark + " Wins!");
        return playerMark;
      }
      playerMark = playerMark == Mark.X ? Mark.O : Mark.X;
    }

  }
}
