package multiscripter.tmp.threadsafecollections;

import multiscripter.tmp.models.User;

public abstract class AbstractTSafeMapTest {

  /**
   * Количество потоков.
   */
  protected int size = 1000;

  /**
   * Хранилище пользователей.
   */
  protected AbstractTSafeMap<String, User> storage;

  /**
   * Устанавливает хранилище пользователей.
   *
   * @param storage хранилище пользователей.
   */
  public void setStorage(final AbstractTSafeMap<String, User> storage) {
    this.storage = storage;
  }

  /**
   * Заполняет хранилище пользователей.
   */
  public long fillStorage() {
    // Многопоточное заполнение хранилища.
    Thread[] threads = new Thread[this.size];
    for (int a = 0; a < threads.length; a++) {
      threads[a] = new UserStorageMapAdder(a, this.storage, this.size);
    }
    long startTime = System.nanoTime();
    try {
      for (Thread thread : threads) {
        thread.start();
      }
      for (Thread thread : threads) {
        thread.join();
      }
    } catch (InterruptedException ex) {
      ex.printStackTrace();
    }
    long time = System.nanoTime() - startTime;
    System.err.println("Nanoseconds used: " + time);
    System.err.println("Storage size: " + this.storage.size());
    return time;
  }
}
