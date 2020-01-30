package multiscripter.tmp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import multiscripter.tmp.models.StorageLinkedHashSet;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует StorageLinkedHashSet.
 */
public class StorageLinkedHashSetTest {

  /**
   * Количество потоков.
   */
  private final int size = 1000;

  /**
   * Хранилище пользователей.
   */
  private StorageLinkedHashSet<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new StorageLinkedHashSet<>();

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
    System.err.println("Seconds used: " + ((System.nanoTime() - startTime) / 1000000000d));
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

  @Test
  public void checkInsertionOrder() {
    this.storage.clear();
    ArrayList<User> list = new ArrayList<>();
    for (int a = this.size; a > 0; a--) {
      for (int b = this.size; b > 0; b--) {
        this.storage.add(new User(String.format("User-%d-%d", a, b), b));
        list.add(new User(String.format("User-%d-%d", a, b), b));
      }
    }
    Iterator<User> iter = this.storage.iterator();
    for (int a = 0; iter.hasNext(); a++) {
      User expected = list.get(a);
      User actual = iter.next();
      assertEquals(expected, actual);
    }
  }
}
