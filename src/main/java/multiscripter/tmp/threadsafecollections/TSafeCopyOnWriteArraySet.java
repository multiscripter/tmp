package multiscripter.tmp.threadsafecollections;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

public class TSafeCopyOnWriteArraySet<E> extends AbstractTSafeCollection<E> {

  protected CopyOnWriteArraySet<E> collection;

  /**
   * Конструктор по умолчанию.
   */
  public TSafeCopyOnWriteArraySet() {
    super(new CopyOnWriteArraySet<>());
    this.collection = (CopyOnWriteArraySet<E>) this.getCollection();
  }

  public boolean add(final E item) {
    return this.collection.add(item);
  }

  public boolean containsAll(final Collection<E> c) {
    return this.collection.containsAll(c);
  }
}
