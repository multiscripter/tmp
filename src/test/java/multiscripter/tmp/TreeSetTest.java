package multiscripter.tmp;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import multiscripter.tmp.models.StorageTreeSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует StorageTreeSet.
 */
public class TreeSetTest {

  /**
   * Компаратор по имени.
   */
  private Comparator<User> compByName = Comparator.comparing(User::getName);

  /**
   * Количество потоков.
   */
  private final int size = 1000;

  /**
   * Хранилище пользователей.
   */
  private StorageTreeSet<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new StorageTreeSet<>(this.compByName);

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

  /**
   * Тестирует сравниватель по имени.
   */
  @Test
  public void testComparatorByName() {
    Iterator<User> iter = this.storage.iterator();
    User cur = iter.next();
    do {
      User next = iter.next();
      assertTrue(cur.getName().compareTo(next.getName()) < 0);
      cur = next;
    } while (iter.hasNext());
  }

  @Test
  public void testFirst() {
    assertEquals(new User("User-0-0", 0), this.storage.first());
  }
}
