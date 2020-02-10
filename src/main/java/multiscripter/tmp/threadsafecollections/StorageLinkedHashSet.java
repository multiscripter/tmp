package multiscripter.tmp.threadsafecollections;

import java.util.LinkedHashSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Потокобезопасное хранилище пользователей
 * на основе связанного хэшированного множества.
 */
@ThreadSafe
public class StorageLinkedHashSet<E> extends AbstractStorage<E> {

  /**
   * Конструктор по умолчанию.
   */
  public StorageLinkedHashSet() {
    super(new LinkedHashSet<>());
  }
}
