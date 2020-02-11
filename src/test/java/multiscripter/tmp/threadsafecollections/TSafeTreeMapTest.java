package multiscripter.tmp.threadsafecollections;

import java.util.Comparator;
import multiscripter.tmp.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует TSafeTreeMap.
 */
public class TSafeTreeMapTest extends AbstractTSafeMapTest {

  /**
   * Сравниватель по имени.
   */
  private Comparator<String> compByName = String::compareTo;

  /**
   * Хранилище пользователей.
   */
  private TSafeTreeMap<String, User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new TSafeTreeMap<>(this.compByName);
    this.setStorage(this.storage);
  }

  /**
   * Тестирует public boolean containsKey(Object key).
   */
  @Test
  public void testContainsKey() {
    this.fillStorage();
    for (int a = 0; a < this.size; a++) {
      for (int b = 0; b < this.size; b++) {
        User user = new User(String.format("User-%d-%d", a, b), b);
        assertTrue(this.storage.containsKey(user.getName()));
      }
    }
  }
}
