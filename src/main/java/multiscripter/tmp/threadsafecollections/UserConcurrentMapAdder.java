package multiscripter.tmp.threadsafecollections;

import java.util.Map;
import multiscripter.tmp.models.User;

/**
 * Реализует сущность Добавлятель пользователей.
 * В качестве коллекции используются стандартные карты.
 */
public class UserConcurrentMapAdder extends Thread {

  /**
   * Имя добавлятеля.
   */
  private final String name;

  /**
   * Количество итераций.
   */
  private final int iterations;

  /**
   * Хранилище пользователей.
   */
  private Map<String, User> map;

  /**
   * Конструктор.
   *
   * @param id         идентификатор.
   * @param map        хранилище.
   * @param iterations количество итераций.
   */
  public UserConcurrentMapAdder(
      final int id,
      final Map<String, User> map,
      final int iterations) {
    this.name = "User-" + id;
    this.iterations = iterations;
    this.map = map;
  }

  /**
   * Запускает указанный код в отдельном трэде.
   */
  @Override
  public void run() {
    try {
      for (int a = 0; a < this.iterations; a++) {
        User u = new User(this.name + "-" + a, a);
        this.map.put(u.getName(), u);
        sleep(1);
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}
