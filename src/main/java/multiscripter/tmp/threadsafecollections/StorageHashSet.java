package multiscripter.tmp.threadsafecollections;

import java.util.HashSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе хэшированного множества.
 */
@ThreadSafe
public class StorageHashSet<E> extends AbstractStorage<E> {

  /**
   * Конструктор по умолчанию.
   */
  public StorageHashSet() {
    super(new HashSet<>());
  }
}
