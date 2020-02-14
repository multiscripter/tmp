package multiscripter.tmp.patterns.structural;

/**
 * Адаптер (Adapter) — структурный шаблон проектирования.
 * Решает задачу унифицированный работы с классами,
 * имеющими несовместимый интерфейс.
 */

/**
 * Имеем 2 класса с несовместимыми интерфейсами.
 */
class Direct3D {
  String draw(int x, int y) {
    return String.format("Direct3D.Pixel{x: %d, y: %d}", x, y);
  }
}
class OpenGL {
  String paint(int a, int b) {
    return String.format("OpenGL.Pixel{x: %d, y: %d}", a, b);
  }
}

/**
 * Адаптер с унифицированным интерфейсом.
 */
interface IRenderer {

  String render(int cx, int cy);
}

/**
 * Адаптер для первого из классов с несовместимыми интерфейсами.
 */
class Direct3DAdapter extends Direct3D implements IRenderer {

  private Direct3D object = new Direct3D();

  @Override
  public String render(int cx, int cy) {
    return this.object.draw(cx, cy);
  }
}

/**
 * Адаптер для второго из классов с несовместимыми интерфейсами.
 */
class OpenGLAdapter extends Direct3D implements IRenderer {

  private OpenGL object = new OpenGL();

  @Override
  public String render(int cx, int cy) {
    return this.object.paint(cx, cy);
  }
}
