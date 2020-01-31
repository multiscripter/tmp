package multiscripter.tmp.models;

import java.util.ArrayDeque;
import java.util.Collection;

import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе дэка на массиве.
 */
@ThreadSafe
public class StorageArrayDeque<E> extends AbstractStorage<E> {

  protected ArrayDeque<E> storage;

  public StorageArrayDeque() {
    super(new ArrayDeque<>());
    this.storage = (ArrayDeque<E>) this.getStorage();
  }

  public StorageArrayDeque(Collection<? extends E> c) {
    super(new ArrayDeque<>(c));
    this.storage = (ArrayDeque<E>) this.getStorage();
  }

  /**
   * Конструктор. Создаёт деку с минимальной ёмкостью.
   *
   * @param minSize минимальная ёмкость деки.
   */
  public StorageArrayDeque(int minSize) {
    super(new ArrayDeque<>(minSize));
    this.storage = (ArrayDeque<E>) this.getStorage();
  }

  public void addFirst(E e) {
    synchronized (this.lock) {
      this.storage.addFirst(e);
    }
  }

  public E getFirst() {
    synchronized (this.lock) {
      return this.storage.getFirst();
    }
  }
}
