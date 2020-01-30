package multiscripter.tmp;

import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArraySet;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Тестирует StorageCopyOnWriteArraySet.
 */
public class StorageCopyOnWriteArraySetTest {

  /**
   * Количество потоков.
   */
  private final int size = 100;

  /**
   * Хранилище.
   * При каждой операции изменения создаётся новая копия коллекции.
   * Коллекция потокобезопасна.
   * Итераторы не поддерживают операцию удаления.
   */
  private CopyOnWriteArraySet<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new CopyOnWriteArraySet<>();

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
