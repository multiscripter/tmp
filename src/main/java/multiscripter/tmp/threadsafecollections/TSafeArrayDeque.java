package multiscripter.tmp.threadsafecollections;

import java.util.ArrayDeque;
import java.util.Collection;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе дэка на массиве.
 */
@ThreadSafe
public class TSafeArrayDeque<E> extends AbstractTSafeCollection<E> {

  protected ArrayDeque<E> collection;

  /**
   * Конструктор по умолчанию.
   */
  public TSafeArrayDeque() {
    super(new ArrayDeque<>());
    this.collection = (ArrayDeque<E>) this.getCollection();
  }

  public TSafeArrayDeque(Collection<? extends E> c) {
    super(new ArrayDeque<>(c));
    this.collection = (ArrayDeque<E>) this.getCollection();
  }

  /**
   * Конструктор. Создаёт деку с минимальной ёмкостью.
   *
   * @param minSize минимальная ёмкость деки.
   */
  public TSafeArrayDeque(int minSize) {
    super(new ArrayDeque<>(minSize));
    this.collection = (ArrayDeque<E>) this.getCollection();
  }

  public void addFirst(E e) {
    synchronized (this.lock) {
      this.collection.addFirst(e);
    }
  }

  public E getFirst() {
    synchronized (this.lock) {
      return this.collection.getFirst();
    }
  }
}
