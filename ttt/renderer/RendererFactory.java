package ttt.renderer;

public class RendererFactory {
  public static Renderer buildRenderer(String arg) {
    Renderer renderer = null;
    switch (arg) {
      case "console":
        renderer = new ConsoleRenderer();
        break;
      case "none":
        renderer = new VoidRenderer();
        break;
      default:
        break;
    }
    return renderer;
  }
}
