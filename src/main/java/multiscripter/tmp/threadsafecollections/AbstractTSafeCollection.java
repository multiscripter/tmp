package multiscripter.tmp.threadsafecollections;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public abstract class AbstractTSafeCollection<E> {

  /**
   * Монитор.
   */
  protected final Object lock;

  /**
   * Хранилище.
   */
  protected AbstractCollection<E> collection;

  /**
   * Конструктор.
   *
   * @param collection хранилище.
   */
  public AbstractTSafeCollection(AbstractCollection<E> collection) {
    this.lock = this;
    this.collection = collection;
  }

  public AbstractCollection<E> getCollection() {
    return this.collection;
  }

  @GuardedBy("lock")
  public boolean add(final E item) {
    synchronized (this.lock) {
      return this.collection.add(item);
    }
  }

  @GuardedBy("lock")
  public void clear() {
    synchronized (this.lock) {
      this.collection.clear();
    }
  }

  @GuardedBy("lock")
  public boolean contains(final Object item) {
    synchronized (this.lock) {
      return this.collection.contains(item);
    }
  }

  @GuardedBy("lock")
  public boolean containsAll(final Collection<E> c) {
    synchronized (this.lock) {
      return this.collection.containsAll(c);
    }
  }

  @GuardedBy("lock")
  public Iterator<E> iterator() {
    synchronized (this.lock) {
      return this.collection.iterator();
    }
  }

  @GuardedBy("lock")
  public int size() {
    synchronized (this.lock) {
      return this.collection.size();
    }
  }
}
