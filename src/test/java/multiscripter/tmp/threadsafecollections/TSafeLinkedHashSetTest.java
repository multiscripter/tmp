package multiscripter.tmp.threadsafecollections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import multiscripter.tmp.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует TSafeLinkedHashSet.
 */
public class TSafeLinkedHashSetTest extends AbstractTSafeTest {

  /**
   * Хранилище пользователей.
   */
  private TSafeLinkedHashSet<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new TSafeLinkedHashSet<>();
    this.setStorage(this.storage);
  }

  /**
   * Тестирует public boolean add(final User user).
   */
  @Test
  public void testAdd() {
    assertTrue(this.storage.add(new User("TestName", 99)));
    assertEquals(1, this.storage.size());
  }

  /**
   * Проверяет public boolean containsAll(Collection<? extends E> c).
   */
  @Test
  public void testContainsAll() {
    this.fillStorage();
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
