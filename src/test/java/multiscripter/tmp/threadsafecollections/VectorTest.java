package multiscripter.tmp.threadsafecollections;

import multiscripter.tmp.models.User;
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
public class VectorTest extends AbstractStorageTest {

  /**
   * Хранилище пользователей.
   */
  private StorageVector<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new StorageVector<>();
    this.setStorage(this.storage);
  }

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }
}
