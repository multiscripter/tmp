package multiscripter.tmp;

import java.util.LinkedList;
import multiscripter.tmp.models.StorageHashSet;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует StorageHashSet.
 */
public class StorageHashSetTest {

  /**
   * Количество потоков.
   */
  private final int size = 1000;

  /**
   * Хранилище пользователей.
   */
  private StorageHashSet<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new StorageHashSet<>();

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

  /**
   * Тестирует public boolean add(final User user).
   */
  @Test
  public void testAdd() {
    this.storage.clear();
    assertTrue(this.storage.add(new User("TestName", 99)));
    assertEquals(1, this.storage.size());
  }

  /**
   * Проверяет public boolean containsAll(Collection<? extends E> c).
   */
  @Test
  public void testContainsAll() {
    LinkedList<User> list = new LinkedList<>();
    for (int a = 0; a < this.size; a++) {
      for (int b = 0; b < this.size; b++) {
        list.add(new User(String.format("User-%d-%d", a, b), b));
      }
    }
    assertTrue(this.storage.containsAll(list));
  }
}
