package multiscripter.tmp.threadsafecollections;

import java.util.AbstractMap;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class AbstractTSafeMap<K, V> {

  /**
   * Монитор.
   */
  protected final Object lock;

  /**
   * Коллекция.
   */
  protected AbstractMap<K, V> collection;

  /**
   * Конструктор.
   *
   * @param collection коллекция.
   */
  public AbstractTSafeMap(AbstractMap<K, V> collection) {
    this.lock = this;
    this.collection = collection;
  }

  public AbstractMap<K, V> getCollection() {
    return this.collection;
  }

  @GuardedBy("lock")
  public void clear() {
    synchronized (this.lock) {
      this.collection.clear();
    }
  }

  @GuardedBy("lock")
  public boolean containsKey(K key) {
    synchronized (this.lock) {
      return this.collection.containsKey(key);
    }
  }

  @GuardedBy("lock")
  public boolean containsValue(V value) {
    synchronized (this.lock) {
      return this.collection.containsValue(value);
    }
  }

  @GuardedBy("lock")
  public V get(final K key) {
    synchronized (this.lock) {
      return this.collection.get(key);
    }
  }

  @GuardedBy("lock")
  public V put(final K key, final V value) {
    synchronized (this.lock) {
      return this.collection.put(key, value);
    }
  }

  @GuardedBy("lock")
  public int size() {
    synchronized (this.lock) {
      return this.collection.size();
    }
  }
}
