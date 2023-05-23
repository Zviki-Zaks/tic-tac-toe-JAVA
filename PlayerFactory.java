public class PlayerFactory {
  public static Player buildPlayer(String arg) {
    Player player = null;
    switch (arg) {
      case "human":
        player = new HumanPlayer();
        break;
      case "whatever":
        player = new WhateverPlayer();
        break;
      default:
        break;
    }
    return player;
  }
}
