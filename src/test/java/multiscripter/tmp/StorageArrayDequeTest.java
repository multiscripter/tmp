package multiscripter.tmp;

import multiscripter.tmp.models.StorageArrayDeque;
import multiscripter.tmp.models.User;
import multiscripter.tmp.models.UserStorageAdder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Тестирует StorageArrayDeque.
 */
public class StorageArrayDequeTest {

  /**
   * Количество потоков.
   */
  private final int size = 1000;

  /**
   * Хранилище пользователей.
   */
  private StorageArrayDeque<User> storage;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new StorageArrayDeque<>();
  }

  /**
   * Заполняет хранилище.
   */
  public void fillStorage() {
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
   * Тестирует public boolean add(final User user).
   */
  @Test
  public void testAdd() {
    User user = new User("test-user", 100);
    assertTrue(this.storage.add(user));
    assertEquals(1, this.storage.size());
  }

  /**
   * offerFirst(E e), offerLast(E e) никогда не возвращают false !!!!!!!!!!!!!!!
   */
  @Test
  public void testAddFirstAndGetFirst() {
    User user = new User("test-user", 100);
    this.storage.addFirst(user);
    assertEquals(user, this.storage.getFirst());
  }

  @Test(expected = NullPointerException.class)
  public void testAddFirstThrowsException() {
    this.storage.addFirst(null);
  }

  /**
   * Проверяет public boolean containsAll(Collection<? extends E> c).
   * contains(E element) на массиве имеет линейную сложность,
   * поэтому на 1000000 элементов это дорогая операция.
  @Test
  public void testContainsAll() {
    LinkedList<User> list = new LinkedList<>();
    for (int a = 0; a < this.size; a++) {
      for (int b = 0; b < this.size; b++) {
        list.add(new User(String.format("User-%d-%d", a, b), b));
      }
    }
    assertTrue(this.storage.containsAll(list));
  }*/

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }
}
