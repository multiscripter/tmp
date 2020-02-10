package multiscripter.tmp.threadsafecollections;

import java.util.Comparator;
import java.util.TreeSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе деревянного множества.
 */
@ThreadSafe
public class TSafeTreeSet<E> extends AbstractTSafeCollection<E> {

  /**
   * Хранилище.
   */
  private TreeSet<E> collection;

  /**
   * Конструктор.
   *
   * @param comparator сравниватель.
   */
  public TSafeTreeSet(final Comparator<E> comparator) {
    super(new TreeSet<>(comparator));
    this.collection = (TreeSet<E>) this.getCollection();
  }

  public E first() {
    synchronized (this.lock) {
      return this.collection.first();
    }
  }
}
