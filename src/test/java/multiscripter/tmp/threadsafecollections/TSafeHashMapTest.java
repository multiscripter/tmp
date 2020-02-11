package multiscripter.tmp.threadsafecollections;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
  private TSafeHashMap<String, User> storage;

  private ConcurrentHashMap<String, User> conHashMap;

  private Map<String, User> syncMap;

  /**
   * Действия перед тестом.
   */
  @Before
  public void beforeTest() {
    this.storage = new TSafeHashMap<>();
    this.setStorage(this.storage);
    this.conHashMap = new ConcurrentHashMap<>();
    this.syncMap = Collections.synchronizedMap(new HashMap<>());
  }

  @Test
  public void testSize() {
    this.fillStorage();
    assertEquals(size * size, this.storage.size());
  }

  /**
   * Тестирует сравнение производительности TSafeHashMap и ConcurrentHashMap.
   * Многопоточное заполнение 1000000 элементов 10 раз.
   * TSafeHashMap ~ 4 сек.
   * ConcurrentHashMap ~ 3,5 сек.
   * synchronizedMap ~ 3,75 сек.
   */
  @Test
  public void testPerformance() {
    int iterations = 10;
    for (int a = 0; a < iterations; a++) {
      this.fillStorage();
      this.storage.clear();
    }
    System.err.println("-----------------------------------------------------");
    for (int a = 0; a < iterations; a++) {
      this.fillConcurrentMap(this.conHashMap);
      this.conHashMap.clear();
    }
    System.err.println("-----------------------------------------------------");
    for (int a = 0; a < iterations; a++) {
      this.fillConcurrentMap(this.syncMap);
      this.syncMap.clear();
    }
  }

  /**
   * Заполняет хранилище пользователей.
   */
  public long fillConcurrentMap(Map<String, User> map) {
    // Многопоточное заполнение хранилища.
    Thread[] threads = new Thread[this.size];
    for (int a = 0; a < threads.length; a++) {
      threads[a] = new UserConcurrentMapAdder(a, map, this.size);
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
    long time = System.nanoTime() - startTime;
    System.err.println("Nanoseconds used: " + time);
    System.err.println("ConcurrentMap size: " + this.conHashMap.size());
    return time;
  }
}
