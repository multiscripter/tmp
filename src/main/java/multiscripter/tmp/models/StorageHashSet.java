package multiscripter.tmp.models;

import java.util.HashSet;
import net.jcip.annotations.ThreadSafe;

/**
 * Реализует сущность Хранилище пользователей на основе хэшированного множества.
 */
@ThreadSafe
public class StorageHashSet<E> extends AbstractStorage<E> {

  public StorageHashSet() {
    super(new HashSet<>());
  }
}
