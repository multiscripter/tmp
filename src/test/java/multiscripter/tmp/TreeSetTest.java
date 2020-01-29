package multiscripter.tmp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import multiscripter.tmp.models.UserStorageTreeSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует UserStorageTreeSet.
 */
public class TreeSetTest {

  /**
   * Компаратор по имени.
   */
  private Comparator<User> compByName = Comparator.comparing(User::getName);

  /**
   * Количество потоков.
   */
  private final int size = 100;

  /**
   * Хранилище пользователей.
   */
  private UserStorageTreeSet storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new UserStorageTreeSet(this.compByName);
    // Многопоточное заполнение хранилища.
    Thread[] threads = new Thread[this.size];
    for (int a = 0; a < threads.length; a++) {
      threads[a] = new UserStorageAdder(a, this.storage, this.size);
    }
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
  }

  /**
   * Тестирует public boolean add(final User user).
   */
  @Test
  public void testAdd() {
    assertTrue(this.storage.add(new User("TestName", 99)));
  }

  /**
   * Проверяет public boolean add(final User user).
   */
  @Test
  public void checkConcurrentAdd() {
    ArrayList<User> list = new ArrayList<>();
    for (int a = 0; a < this.size; a++) {
      for (int b = 0; b < this.size; b++) {
        list.add(new User(String.format("User-%d-%d", a, b), b));
      }
    }

    for (User user : list) {
      assertTrue(this.storage.contains(user));
    }
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
}
