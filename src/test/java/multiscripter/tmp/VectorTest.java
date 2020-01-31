package multiscripter.tmp;

import java.util.Vector;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Тестирует Vector.
 * Отличия от ArrayList:
 * Вектор синхронизирован.
 * Имеет методы работы с ёмкостью.
 * При достижении предела ёмкость увиличивается на 100% (у ArrayList на 50%).
 */
public class VectorTest {

  /**
   * Количество потоков.
   */
  private final int size = 1000;

  /**
   * Хранилище пользователей.
   */
  private Vector<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new Vector<>();
  }

  /**
   * Заполняет хранилище.
   */
  public void fillStorage() {
    // Многопоточное заполнение хранилища.
    Thread[] threads = new Thread[this.size];
    for (int a = 0; a < threads.length; a++) {
      threads[a] = new UserStorageAdder(a, this.storage, this.size);
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
    System.err.println("Nanoseconds used: " + (System.nanoTime() - startTime));
    System.err.println("Storage size: " + this.storage.size());
  }

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }
}
