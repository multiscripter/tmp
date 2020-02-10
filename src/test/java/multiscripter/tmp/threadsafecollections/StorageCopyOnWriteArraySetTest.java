package multiscripter.tmp.threadsafecollections;

import java.util.LinkedList;
import multiscripter.tmp.models.User;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует StorageCopyOnWriteArraySet.
 */
public class StorageCopyOnWriteArraySetTest extends AbstractStorageTest {

  /**
   * Количество потоков.
   */
  protected int size = 100;

  /**
   * Хранилище.
   * При каждой операции изменения создаётся новая копия коллекции.
   * Коллекция потокобезопасна.
   * Итераторы не поддерживают операцию удаления.
   */
  private StorageCopyOnWriteArraySet<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    super.size = this.size;
    this.storage = new StorageCopyOnWriteArraySet<>();
    this.setStorage(this.storage);
    this.fillStorage();
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
