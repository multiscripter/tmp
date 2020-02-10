package multiscripter.tmp.threadsafecollections;

import java.util.HashMap;
import multiscripter.tmp.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Тестирует TSafeHashMap.
 */
public class TSafeHashMapTest extends AbstractTSafeMapTest {

  /**
   * Хранилище пользователей.
   */
  private HashMap<String, User> collection;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new TSafeHashMap<>();
    this.setStorage(this.storage);
  }

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }
}
