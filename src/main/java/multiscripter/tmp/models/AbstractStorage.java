package multiscripter.tmp.models;

import java.util.AbstractCollection;
import java.util.Iterator;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public abstract class AbstractStorage<E> extends AbstractCollection<E> {

  /**
   * Монитор.
   */
  protected final Object lock;

  /**
   * Хранилище.
   */
  protected AbstractCollection<E> storage;

  /**
   * Конструктор.
   *
   * @param storage хранилище.
   */
  public AbstractStorage(AbstractCollection<E> storage) {
    this.lock = this;
    this.storage = storage;
  }

  protected AbstractCollection<E> getStorage() {
    return this.storage;
  }

  @GuardedBy("lock")
  public boolean add(final E item) {
    synchronized (this.lock) {
      return this.storage.add(item);
    }
  }

  @GuardedBy("lock")
  public void clear() {
    synchronized (this.lock) {
      this.storage.clear();
    }
  }

  @GuardedBy("lock")
  public boolean contains(final Object item) {
    synchronized (this.lock) {
      return this.storage.contains(item);
    }
  }

  @GuardedBy("lock")
  public Iterator<E> iterator() {
    synchronized (this.lock) {
      return this.storage.iterator();
    }
  }

  @GuardedBy("lock")
  public int size() {
    synchronized (this.lock) {
      return this.storage.size();
    }
  }
}
