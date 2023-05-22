enum Mark {
  BLANK, X, O
}

enum GameStatus {
  DRAW, X_WIN, O_WIN, IN_PROGRESS
}

class Board {
  public static final int SIZE = 3;
  public static final int WIN_STREAK = 3;

  private static Mark[][] board = new Mark[SIZE][SIZE];
  private GameStatus gameStatus = GameStatus.IN_PROGRESS;
  private static int blankCounter = SIZE * SIZE;

  public Board() {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        board[i][j] = Mark.BLANK;
      }
    }
  }

  public boolean putMark(Mark mark, int row, int col) {
    if (isPositionValid(row, col)) {
      board[row][col] = mark;
      blankCounter--;
      checkGameStatus(mark, row, col);
      return true;
    } else {
      return false;
    }
  }

  public GameStatus getGameStatus() {
    return gameStatus;
  }

  public Mark getMark(int row, int col) {
    return board[row][col];
  }

  private boolean isPositionValid(int row, int col) {
    if (row < 0 || row >= Board.SIZE || col < 0 || col >= Board.SIZE) {
      return false;
    }
    Mark mark = getMark(row, col);
    if (mark != Mark.BLANK) {
      return false;
    }
    return true;
  }

  private void checkGameStatus(Mark mark, int row, int col) {
    if (countRow(row, col, mark) == WIN_STREAK ||
        countCol(row, col, mark) == WIN_STREAK ||
        countLrtDiagonal(row, col, mark) == WIN_STREAK ||
        countRtlDiagonal(row, col, mark) == WIN_STREAK) {
      setGameStatus(mark);
    } else if (blankCounter == 0) {
      this.gameStatus = GameStatus.DRAW;
    }
  }

  private void setGameStatus(Mark mark) {
    if (mark == Mark.X) {
      gameStatus = GameStatus.X_WIN;
    } else {
      gameStatus = GameStatus.O_WIN;
    }
  }

  private int countRow(int row, int col, Mark mark) {
    return countMarkInDirection(row, col, -1, 0, mark) + countMarkInDirection(row, col, +1, 0, mark) - 1;
  }

  private int countCol(int row, int col, Mark mark) {
    return countMarkInDirection(row, col, 0, -1, mark) + countMarkInDirection(row, col, 0, +1, mark) - 1;
  }

  private int countLrtDiagonal(int row, int col, Mark mark) {
    return countMarkInDirection(row, col, -1, -1, mark) + countMarkInDirection(row, col, +1, +1, mark) - 1;
  }

  private int countRtlDiagonal(int row, int col, Mark mark) {
    return countMarkInDirection(row, col, -1, +1, mark) + countMarkInDirection(row, col, +1, -1, mark) - 1;
  }

  private int countMarkInDirection(int row, int col, int rowDelta, int colDelta, Mark mark) {
    int count = 0;
    while (row < SIZE && row >= 0 && col < SIZE && col >= 0 && board[row][col] == mark) {
      count++;
      row += rowDelta;
      col += colDelta;
    }
    return count;
  }
}
