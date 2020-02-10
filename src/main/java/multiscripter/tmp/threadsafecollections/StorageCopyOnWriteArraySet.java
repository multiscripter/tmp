package multiscripter.tmp.threadsafecollections;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArraySet;

public class StorageCopyOnWriteArraySet<E> extends AbstractStorage<E> {

  protected CopyOnWriteArraySet<E> storage;

  /**
   * Конструктор по умолчанию.
   */
  public StorageCopyOnWriteArraySet() {
    super(new CopyOnWriteArraySet<>());
    this.storage = (CopyOnWriteArraySet<E>) this.getStorage();
  }

  public boolean add(final E item) {
    return this.storage.add(item);
  }

  public boolean containsAll(final Collection<E> c) {
    return this.storage.containsAll(c);
  }
}
