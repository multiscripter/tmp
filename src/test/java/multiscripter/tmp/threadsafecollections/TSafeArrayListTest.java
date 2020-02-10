package multiscripter.tmp.threadsafecollections;

import multiscripter.tmp.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Тестирует StorageArrayList.
 */
public class TSafeArrayListTest extends AbstractTSafeTest {

  /**
   * Хранилище пользователей.
   */
  private TSafeArrayList<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new TSafeArrayList<>();
    this.setStorage(this.storage);
  }

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }
}
