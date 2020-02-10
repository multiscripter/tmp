package multiscripter.tmp.threadsafecollections;

import multiscripter.tmp.models.User;

/**
 * Реализует сущность Добавлятель пользователей.
 * В качестве коллекции исповользуется карты.
 */
public class UserStorageMapAdder extends Thread {

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
  private AbstractTSafeMap<String, User> storage;

  /**
   * Конструктор.
   *
   * @param id         идентификатор.
   * @param storage    хранилище.
   * @param iterations количество итераций.
   */
  public UserStorageMapAdder(
      final int id,
      final AbstractTSafeMap<String, User> storage,
      final int iterations) {
    this.name = "User-" + id;
    this.iterations = iterations;
    this.storage = storage;
  }

  /**
   * Запускает указанный код в отдельном трэде.
   */
  @Override
  public void run() {
    try {
      for (int a = 0; a < this.iterations; a++) {
        User u = new User(this.name + "-" + a, a);
        this.storage.put(u.getName(), u);
        sleep(1);
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}
