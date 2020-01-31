package multiscripter.tmp.models;

import java.util.Comparator;
import java.util.TreeSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе деревянного множества.
 */
@ThreadSafe
public class StorageTreeSet<E> extends AbstractStorage<E> {

  /**
   * Хранилище.
   */
  private TreeSet<E> storage;

  /**
   * Конструктор.
   *
   * @param comparator сравниватель.
   */
  public StorageTreeSet(final Comparator<E> comparator) {
    super(new TreeSet<>(comparator));
    this.storage = (TreeSet<E>) this.getStorage();
  }

  public E first() {
    synchronized (this.lock) {
      return this.storage.first();
    }
  }
}
