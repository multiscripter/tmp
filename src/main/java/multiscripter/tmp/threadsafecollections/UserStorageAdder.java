package multiscripter.tmp.threadsafecollections;

import multiscripter.tmp.models.User;

/**
 * Реализует сущность Добавлятель пользователей.
 */
public class UserStorageAdder extends Thread {

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
  private AbstractTSafeCollection<User> storage;

  /**
   * Конструктор.
   *
   * @param id         идентификатор.
   * @param storage    хранилище.
   * @param iterations количество итераций.
   */
  public UserStorageAdder(
      final int id,
      final AbstractTSafeCollection<User> storage,
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
        this.storage.add(new User(this.name + "-" + a, a));
        sleep(1);
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
  }
}
