package multiscripter.tmp.threadsafecollections;

import multiscripter.tmp.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Тестирует TSafeLinkedList.
 */
public class TSafeLinkedListTest extends AbstractTSafeTest {

  /**
   * Хранилище пользователей.
   */
  private TSafeLinkedList<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new TSafeLinkedList<>();
    this.setStorage(this.storage);
  }

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }
}
